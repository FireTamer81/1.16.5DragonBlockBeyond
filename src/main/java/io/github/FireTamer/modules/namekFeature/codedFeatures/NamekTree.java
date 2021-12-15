package io.github.FireTamer.modules.namekFeature.codedFeatures;

import io.github.FireTamer.modules.namekFeature.NamekModule;
import net.minecraft.block.BlockState;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class NamekTree extends Tree {

    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random rand, boolean bool) {
        return Features.NAMEK_TREE;
    }
}
