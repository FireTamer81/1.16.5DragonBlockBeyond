package io.github.FireTamer.modules.strongBlockFeature;

import com.matyrobbrt.lib.annotation.RL;
import com.matyrobbrt.lib.module.IModule;
import com.matyrobbrt.lib.module.Module;
import com.matyrobbrt.lib.module.ModuleHelper;
import io.github.FireTamer.DBB_Main;

@Module(id = @RL(modid = DBB_Main.MOD_ID, path = "strong_block"))
public class StrongBlockModule extends ModuleHelper implements IModule {


    /**
     * Just example stuff below fo what module classes are used for
     * They are basically there so you don't have one massive BlockInit or Tile Init for your entire mod and can keep certain things together
     * The general BlockInit, ItemInit, etc. are for outliers that don't really belong to any one feature.
     * Also, small events related directly to a feature's function can go here to
     */

    /**
    @AutoBlockItem
    @RegisterBlock("rocket")
    public static final Block ROCKET_BLOCK = new RocketBlock();

    @RegisterTileEntityType("rocket_tile")
    public static final TileEntityType<RocketTile> ROCKET_TILE = TileEntityType.Builder
            .of(RocketTile::new, ROCKET_BLOCK).build(null);

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        cutout(RocketModule.ROCKET_BLOCK);
        ClientRegistry.bindTileEntityRenderer(RocketModule.ROCKET_TILE, RocketTileRenderer::new);
    }
    **/
}
