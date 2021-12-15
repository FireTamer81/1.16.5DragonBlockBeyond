package io.github.FireTamer.modules.timeChamberFeature.tiles;

import io.github.FireTamer.modules.timeChamberFeature.TimeChamberModule;
import io.github.FireTamer.modules.timeChamberFeature.blocks.TimeChamberDoor_Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class TimeChamberDoor_Tile extends TileEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    @SuppressWarnings("unchecked")
    private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        BlockPos thisPos = event.getAnimatable().getBlockPos();
        AnimationController contr = event.getController();
        contr.transitionLengthTicks = 0;

        if (event.getAnimatable().getLevel().getBlockState(thisPos).getValue(TimeChamberDoor_Block.IS_OPEN) == false) {
            contr.setAnimation(new AnimationBuilder()
                    .addAnimation("Door.anim.close", false)
                    .addAnimation("Door.anim.remain_closed", true));
        } else {
            contr.setAnimation(new AnimationBuilder()
                    .addAnimation("Door.anim.open", false)
                    .addAnimation("Door.anim.remain_open", true));
        }

        return PlayState.CONTINUE;
    }

    public TimeChamberDoor_Tile() {
        super(TimeChamberModule.TIME_CHAMBER_DOOR_TILE);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() { return factory; }
}
