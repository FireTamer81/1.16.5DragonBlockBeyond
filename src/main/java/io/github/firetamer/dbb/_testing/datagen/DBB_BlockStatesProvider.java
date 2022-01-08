package io.github.firetamer.dbb._testing.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IDataProvider;

import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.commonInit.BlockInit;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DBB_BlockStatesProvider extends BlockStateProvider implements IDataProvider {
    public DBB_BlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, DragonBlockBeyond.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //Gets the actual instance of the block object, and then sets up the texture pattern where it gets the registry name to look for the texture
        //simpleBlock(BlockInit.TEST_BLOCK.get(), cubeAll(BlockInit.TEST_BLOCK.get()));

        //Full Blocks
        simpleBlock(BlockInit.DIRTY_STONE, cubeAll(BlockInit.DIRTY_STONE));

        //Stairs
        //stairsBlock(WarenaiBlocksInit.WARENAI_BLOCK_BLACK_STAIRS.get(), modLoc("block/warenai_block_black"));

        //Slabs
        //slabBlock(WarenaiBlocksInit.WARENAI_BLOCK_BLACK_SLAB.get(), modLoc("block/warenai_block_black"), modLoc("block/warenai_block_black"));

        //Fences
        //fenceBlock(WarenaiBlocksInit.WARENAI_BLOCK_BLACK_FENCE.get(), modLoc("block/warenai_block_black"));

        //Walls
        //wallBlock(WarenaiBlocksInit.WARENAI_BLOCK_BLACK_WALL.get(), modLoc("block/warenai_block_black"));
    }
}
