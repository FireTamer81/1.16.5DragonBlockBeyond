package io.github.firetamer.dbb.modules.strong_block;

import com.matyrobbrt.lib.compat.top.ITOPDriver;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import io.github.firetamer.dbb.modules.strong_block.blocks.StrongBlockTile;
import io.github.firetamer.dbb.util.helper.WorldHelper;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;

public class WarenaiTOPDriver implements ITOPDriver {

    private WarenaiTOPDriver() {
    }

    public static final WarenaiTOPDriver DRIVER = new WarenaiTOPDriver();

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo probeInfo, PlayerEntity player, World level,
                             BlockState blockState, IProbeHitData probeData) {
        StrongBlockTile tile = WorldHelper.getTileEntity(StrongBlockTile.class, level, probeData.getPos(), true);
        if (tile == null) {
            return;
        }
        probeInfo.text("Health: " + tile.getHealth());
    }

}
