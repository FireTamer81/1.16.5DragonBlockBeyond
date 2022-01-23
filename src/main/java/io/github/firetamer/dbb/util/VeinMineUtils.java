package io.github.firetamer.dbb.util;

import io.github.firetamer.dbb.util.helper.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public final class VeinMineUtils {

	public static boolean captureVeinMining(ServerPlayerEntity player, ServerWorld world, Block targetBlock, BlockPos pos, int limit) {
		BlockHelper.BlockDropCaptureHelper.startCapturing();
		try {
			return doVeinMine(player, world, targetBlock, pos, limit);
		} finally {
			BlockHelper.BlockDropCaptureHelper.getCapturedStacksAndStop()
					.forEach(entity -> Block.popResource(world, entity.blockPosition(), entity.getItem()));
		}
	}

	public static boolean doVeinMine(ServerPlayerEntity player, ServerWorld world, Block targetBlock, BlockPos pos, int limit) {
		ItemStack heldItem = player.getItemInHand(Hand.MAIN_HAND);
		if (heldItem.isDamageableItem()) {
			int usesLeft = heldItem.getMaxDamage() - heldItem.getDamageValue();
			if (usesLeft <= 1)
				return false;
		}
		Set<BlockPos> traversedBlocks = new HashSet<>();
		Queue<BlockPos> positionQueue = new LinkedList<>();
		positionQueue.add(pos);
		while (!positionQueue.isEmpty()) {
			BlockPos headPos = positionQueue.poll();
			BlockPos.withinManhattanStream(headPos, 1, 1, 1).forEach(offset -> {
				if (traversedBlocks.size() >= limit) {
					positionQueue.clear();
					return;
				}
				if (traversedBlocks.contains(offset))
					return;
				BlockState at = world.getBlockState(offset);
				if (at.getBlock().isAir(at, world, offset) || at.getBlock() != targetBlock)
					return;
				if (doDestroy(world, offset, player)) {
					damageMiningItem(heldItem, player, 1);
				}
				positionQueue.add(offset.immutable());
				traversedBlocks.add(offset.immutable());
			});
		}
		return true;
	}

	private static boolean doDestroy(ServerWorld world, BlockPos pos, ServerPlayerEntity player) {
		ItemStack miningStack = player.getItemInHand(Hand.MAIN_HAND);
		return true;
	}

	public static void damageMiningItem(ItemStack heldItem, PlayerEntity player, int amount) {
		heldItem.hurtAndBreak(amount, player, playerEntity -> {});
	}
}
