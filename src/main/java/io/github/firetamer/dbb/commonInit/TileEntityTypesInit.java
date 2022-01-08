package io.github.firetamer.dbb.commonInit;

import com.matyrobbrt.lib.registry.annotation.RegistryHolder;

import io.github.firetamer.dbb.DragonBlockBeyond;

@RegistryHolder(modid = DragonBlockBeyond.MOD_ID)
public class TileEntityTypesInit {

    /**
     * @RegisterTileEntityType("rocket_tile")
     *     public static final TileEntityType<RocketTile> ROCKET_TILE = TileEntityType.Builder
     *             .of(RocketTile::new, ROCKET_BLOCK).build(null);
     */
}
