package io.github.FireTamer.modules.namekFeature.codedFeatures;

import io.github.FireTamer.modules.namekFeature.NamekModule;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class Features {

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }


    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> NAMEK_TREE = register("namek_tree", Feature.TREE.configured(
            (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(States.NAMEK_TREE_LOG),
                    new SimpleBlockStateProvider(States.NAMEK_TREE_LEAVES),
                    new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),
                    new StraightTrunkPlacer(12, 6, 0),
                    new TwoLayerFeature(1, 0, 1))).ignoreVines().build())
    );

    public static final class States
    {
        protected static final BlockState NAMEK_TREE_LOG = NamekModule.NAMEK_LOG.defaultBlockState();
        protected static final BlockState NAMEK_TREE_LEAVES = NamekModule.NAMEK_LEAVES.defaultBlockState();
    }
}
