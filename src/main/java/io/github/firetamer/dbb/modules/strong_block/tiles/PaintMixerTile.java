package io.github.firetamer.dbb.modules.strong_block.tiles;

import io.github.firetamer.dbb.modules.strong_block.StrongBlockModule;
import io.github.firetamer.dbb.modules.strong_block.blocks.PaintMixer;
import io.github.firetamer.dbb.modules.strong_block.util.PaintMixerAnimationEnum;
import io.github.firetamer.dbb.modules.time_chamber.blocks.TimeChamberDoorBlock;
import io.github.firetamer.dbb.modules.time_chamber.tiles.TimeChamberDoorTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PaintMixerTile extends TileEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        BlockPos thisPos = event.getAnimatable().getBlockPos();
        AnimationController contr = event.getController();
        contr.transitionLengthTicks = 0;

        PaintMixerAnimationEnum currentAnimEnum = event.getAnimatable().getLevel().getBlockState(thisPos).getValue(PaintMixer.ANIMATION_ENUM);

        if (currentAnimEnum == PaintMixerAnimationEnum.OFF) {
            contr.setAnimation(new AnimationBuilder().addAnimation("animation.model.off", false));
        }

        return PlayState.CONTINUE;
    }

    public PaintMixerTile() { super(StrongBlockModule.PAINT_MIXER_TILE); }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() { return factory; }
}
