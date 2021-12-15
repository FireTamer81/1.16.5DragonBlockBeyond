package io.github.FireTamer.modules.timeChamberFeature;

import com.matyrobbrt.lib.annotation.RL;
import com.matyrobbrt.lib.module.IModule;
import com.matyrobbrt.lib.module.Module;
import com.matyrobbrt.lib.module.ModuleHelper;
import com.matyrobbrt.lib.registry.annotation.AutoBlockItem;
import com.matyrobbrt.lib.registry.annotation.RegisterBlock;
import com.matyrobbrt.lib.registry.annotation.RegisterTileEntityType;
import io.github.FireTamer.DBB_Main;
import io.github.FireTamer.modules.timeChamberFeature.blocks.TimeChamberDoor_Block;
import io.github.FireTamer.modules.timeChamberFeature.blocks.TimeChamberPortalBlock;
import io.github.FireTamer.modules.timeChamberFeature.client.TimeChamberDoor_Renderer;
import io.github.FireTamer.modules.timeChamberFeature.tiles.TimeChamberDoor_Tile;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Module(id = @RL(modid = DBB_Main.MOD_ID, path = "time_chamber_door_feature"))
public class TimeChamberModule extends ModuleHelper implements IModule {

    @AutoBlockItem
    @RegisterBlock("time_chamber_door")
    public static final TimeChamberDoor_Block TIME_CHAMBER_DOOR_BLOCK = new TimeChamberDoor_Block();

    @RegisterTileEntityType("time_chamber_door_tile")
    public static final TileEntityType<TimeChamberDoor_Tile> TIME_CHAMBER_DOOR_TILE = TileEntityType.Builder
            .of(TimeChamberDoor_Tile::new, TIME_CHAMBER_DOOR_BLOCK).build(null);



    @AutoBlockItem
    @RegisterBlock("time_chamber_floor_block")
    public static final Block TIME_CHAMBER_FLOOR_BLOCK = new Block(AbstractBlock.Properties.copy(Blocks.NETHERITE_BLOCK));

    @AutoBlockItem
    @RegisterBlock("time_chamber_portal_block")
    public static final TimeChamberPortalBlock TIME_CHAMBER_PORTAL_BLOCK = new TimeChamberPortalBlock();




    /******************************************************************************************************************/
    //Events
    /******************************************************************************************************************/

    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(TIME_CHAMBER_DOOR_TILE, TimeChamberDoor_Renderer::new);

        event.enqueueWork(() -> {
            setDimensionEffects();
        });
    }

    private static void setDimensionEffects() {
        ResourceLocation TIME_CHAMBER_ID = new ResourceLocation(DBB_Main.MOD_ID, "time_chamber_dimension");
        RegistryKey<World> TIME_CHAMBER_DIMENSION = RegistryKey.create(Registry.DIMENSION_REGISTRY, TIME_CHAMBER_ID);


        DimensionRenderInfo.EFFECTS.put(TIME_CHAMBER_DIMENSION.location(),
                new DimensionRenderInfo(Float.NaN, true, DimensionRenderInfo.FogType.NONE, false, true) {

                    @Override
                    public Vector3d getBrightnessDependentFogColor(Vector3d color, float brightness) {
                        return color.multiply(255, 255, 255);
                    }

                    @Override
                    public boolean isFoggyAt(int x, int z) {
                        return false;
                    }

                }
        );
    }
}
