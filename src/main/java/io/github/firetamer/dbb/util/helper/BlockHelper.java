package io.github.firetamer.dbb.util.helper;

import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class BlockHelper {

	public static boolean breakBlock(ServerWorld world, ServerPlayerEntity player, BlockPos pos, BlockState stateBroken, ItemStack heldItem, boolean breakBlock, boolean ignoreHarvestRestrictions) {
		ItemStack original = player.getItemInHand(Hand.MAIN_HAND);
		try {
			player.setItemInHand(Hand.MAIN_HAND, heldItem);
			return doNativeBreakBlock(world, player, pos, stateBroken, heldItem, breakBlock, ignoreHarvestRestrictions);
		} finally {
			player.setItemInHand(Hand.MAIN_HAND, original);
		}
	}

	private static boolean doNativeBreakBlock(ServerWorld world, ServerPlayerEntity player, BlockPos pos, BlockState stateBroken, ItemStack heldItem, boolean breakBlock, boolean ignoreHarvestRestrictions) {
		int xp;
		try {
			boolean preCancelEvent = !heldItem.isEmpty() && !heldItem.getItem().canAttackBlock(stateBroken, world, pos, player);
			BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(world, pos, stateBroken, player);
			event.setCanceled(preCancelEvent);
			MinecraftForge.EVENT_BUS.post(event);
			if (event.isCanceled())
				return false;
			xp = event.getExpToDrop();
		} catch (Exception exc) {
			return false;
		}
		if (xp == -1)
			return false;
		if (heldItem.onBlockStartBreak(pos, player))
			return false;
		boolean harvestable = true;
		try {
			if (!ignoreHarvestRestrictions)
				harvestable = stateBroken.canHarvestBlock(world, pos, player);
		} catch (Exception exc) {
			return false;
		}
		try {
			heldItem.copy().mineBlock(world, stateBroken, pos, player);
		} catch (Exception exc) {
			return false;
		}
		boolean wasCapturingStates = world.captureBlockSnapshots;
		List<BlockSnapshot> previousCapturedStates = new ArrayList<>(world.capturedBlockSnapshots);
		TileEntity tileEntity = world.getBlockEntity(pos);
		world.captureBlockSnapshots = true;
		try {
			if (breakBlock) {
				if (!stateBroken.removedByPlayer(world, pos, player, harvestable, Fluids.EMPTY.defaultFluidState())) {
					restoreWorldState(world, wasCapturingStates, previousCapturedStates);
					return false;
				}
			} else {
				stateBroken.getBlock().playerWillDestroy(world, pos, stateBroken, player);
			}
		} catch (Exception exc) {
			restoreWorldState(world, wasCapturingStates, previousCapturedStates);
			return false;
		}
		stateBroken.getBlock().destroy(world, pos, stateBroken);
		if (harvestable)
			try {
				stateBroken.getBlock().playerDestroy(world, player, pos, stateBroken, tileEntity, heldItem.copy());
			} catch (Exception exc) {
				restoreWorldState(world, wasCapturingStates, previousCapturedStates);
				return false;
			}
		if (xp > 0)
			stateBroken.getBlock().popExperience(world, pos, xp);
		BlockDropCaptureHelper.startCapturing();
		try {
			world.captureBlockSnapshots = false;
			world.restoringBlockSnapshots = true;
			world.capturedBlockSnapshots.forEach(s -> {
				world.sendBlockUpdated(s.getPos(), s.getReplacedBlock(), s.getCurrentBlock(), s.getFlag());
				s.getCurrentBlock().updateNeighbourShapes(world, s.getPos(), 11);
			});
			world.restoringBlockSnapshots = false;
		} finally {
			BlockDropCaptureHelper.getCapturedStacksAndStop();
			world.capturedBlockSnapshots.clear();
			world.captureBlockSnapshots = wasCapturingStates;
			world.capturedBlockSnapshots.addAll(previousCapturedStates);
		}
		return true;
	}

	private static void restoreWorldState(World world, boolean prevCaptureFlag, List<BlockSnapshot> prevSnapshots) {
		world.captureBlockSnapshots = false;
		world.restoringBlockSnapshots = true;
		world.capturedBlockSnapshots.forEach(s -> s.restore(true));
		world.restoringBlockSnapshots = false;
		world.capturedBlockSnapshots.clear();
		world.captureBlockSnapshots = prevCaptureFlag;
		world.capturedBlockSnapshots.addAll(prevSnapshots);
	}

	@Mod.EventBusSubscriber
	public static final class BlockDropCaptureHelper {

		private static final Stack<List<ItemEntity>> capturing = new Stack<>();

		@SubscribeEvent
		public static void onDrop(EntityJoinWorldEvent event) {
			if (event.getWorld() instanceof net.minecraft.world.server.ServerWorld && event.getEntity() instanceof ItemEntity) {
				ItemStack itemStack = ((ItemEntity) event.getEntity()).getItem();
				if (!capturing.isEmpty()) {
					event.setCanceled(true);
					if (!itemStack.isEmpty() &&
							!capturing.isEmpty())
						capturing.peek().add((ItemEntity) event.getEntity());
					event.getEntity().remove();
				}
			}
		}

		public static void startCapturing() {
			capturing.push(new ArrayList<>());
		}

		public static List<ItemEntity> getCapturedStacksAndStop() {
			return capturing.pop();
		}

	}

}
