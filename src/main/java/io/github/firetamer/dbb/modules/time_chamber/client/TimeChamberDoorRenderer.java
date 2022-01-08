package io.github.firetamer.dbb.modules.time_chamber.client;

import javax.annotation.Nullable;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;

import io.github.firetamer.dbb.modules.time_chamber.tiles.TimeChamberDoorTile;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class TimeChamberDoorRenderer extends GeoBlockRenderer<TimeChamberDoorTile> {

    public TimeChamberDoorRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new TimeChamberDoorModel());
    }

    @Override
    public RenderType getRenderType(TimeChamberDoorTile animatable, float partialTicks, MatrixStack stack, @Nullable IRenderTypeBuffer renderTypeBuffer, @Nullable IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
