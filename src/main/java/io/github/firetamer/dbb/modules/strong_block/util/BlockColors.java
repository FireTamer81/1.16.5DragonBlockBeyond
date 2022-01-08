package io.github.firetamer.dbb.modules.strong_block.util;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

import io.github.firetamer.dbb.modules.strong_block.StrongBlockModule;
import io.github.firetamer.dbb.modules.strong_block.blocks.WarenaiBlock;

public class BlockColors implements IBlockColor {

    public static final IBlockColor INSTANCE = new BlockColors();
    public static Random rand = new Random();

    @Override
    public int getColor(BlockState state, @Nullable IBlockDisplayReader displayReader, @Nullable BlockPos pos, int p_getColor_4_) {
        return determineColorApplied(state);
    }

    public static void registerBlockColors()
    {
        System.out.println("Registering block color handler");
        Minecraft.getInstance().getBlockColors().register(INSTANCE, StrongBlockModule.WARENAI_STRONG_BLOCK);
    }



    private int determineColorApplied(BlockState state) {
        switch(state.getValue(WarenaiBlock.BLOCK_COLOR)) {
            default: //WHITE
                return 0xd7d7d7;
            case BLACK:
                return 0x232227;
            case BROWN:
                return 0x895a37;
            case BLUE:
                return 0x444cb4;
            case CYAN:
                return 0x1ca7a8;
            case GRAY:
                return 0x51585a;
            case GREEN:
                return 0x68891a;
            case LIGHTBLUE:
                return 0x41bbe2;
            case LIGHTGRAY:
                return 0xa7a8a4;
            case LIME:
                return 0x8dd52b;
            case MAGENTA:
                return 0xd761cf;
            case ORANGE:
                return 0xfa8d34;
            case PURPLE:
                return 0x9a42c9;
            case PINK:
                return 0xf6a0b9;
            case RED:
                return 0xc54038;
            case YELLOW:
                return 0xfcdd61;
        }
    }
}
