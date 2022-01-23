package io.github.firetamer.dbb.item;

import io.github.firetamer.dbb.util.VeinMineUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TestPickaxe extends PickaxeItem {

	@SubscribeEvent
	public static void onBlockMined(BlockEvent.BreakEvent event) {
		if (event.getWorld().isClientSide() || event.getPlayer() instanceof net.minecraftforge.common.util.FakePlayer)
			return;
		ServerPlayerEntity player = (ServerPlayerEntity)event.getPlayer();
		if (player.getItemInHand(player.getUsedItemHand()).getItem() instanceof  TestPickaxe) {
			ServerWorld world = (ServerWorld)event.getWorld();
			BlockPos pos = event.getPos();
			BlockState blockState = world.getBlockState(pos);
			if (VeinMineUtils.captureVeinMining(player, world, blockState.getBlock(), pos, 12))
				event.setCanceled(true);
		}
	}

	public TestPickaxe(IItemTier tier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties props) {
		super(tier, pAttackDamageModifier, pAttackSpeedModifier, props);
	}
}
