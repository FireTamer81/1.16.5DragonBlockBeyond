package io.github.FireTamer.commonInit;

import com.matyrobbrt.lib.registry.annotation.RegistryHolder;
import io.github.FireTamer.DBB_Main;

@RegistryHolder(modid = DBB_Main.MOD_ID)
public class TileEntityTypesInit {

    /**
     * @RegisterTileEntityType("rocket_tile")
     *     public static final TileEntityType<RocketTile> ROCKET_TILE = TileEntityType.Builder
     *             .of(RocketTile::new, ROCKET_BLOCK).build(null);
     */
}
