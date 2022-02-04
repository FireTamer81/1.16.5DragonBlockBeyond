package io.github.firetamer.dbb.modules.machines.paint_mixer.tiles;

import io.github.firetamer.dbb.modules.machines.MachinesModule;
import io.github.firetamer.dbb.modules.strong_block.StrongBlockModule;
import io.github.firetamer.dbb.modules.machines.paint_mixer.blocks.PaintMixer;
import io.github.firetamer.dbb.modules.machines.paint_mixer.util.PaintMixerAnimationEnum;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.*;

public class PaintMixerTile extends TileEntity implements IAnimatable{
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    public boolean isMixing = false;

    public PaintMixerTile() {
        super(MachinesModule.PAINT_MIXER_TILE);
    }

    public void mixNewPaintCan() {
        boolean isPaintCanInSlot = this.itemHandler.getStackInSlot(0).getCount() > 0
                && this.itemHandler.getStackInSlot(0).getItem() == Items.DIRT;

        if(isPaintCanInSlot) {
            //this.itemHandler.getStackInSlot(0).shrink(1);
            this.isMixing = true;

            Timer timer1 = new Timer(6000, actionEvent -> this.replaceItem());
            timer1.setRepeats(false);
            timer1.start();

            //this.itemHandler.insertItem(0, new ItemStack(Items.FEATHER), false);
        }
    }

    //Used in the ShapeFiller BLock use method for the front two blocks (meant to simulate the ability to insert items directly into the machine)
    public void addPaintCanToSlot0() {
        this.itemHandler.insertItem(0, new ItemStack(Items.DIRT), false);
        this.isMixing = false;
    }

    public void replaceItem() {
        this.itemHandler.getStackInSlot(0).shrink(1);
        this.itemHandler.insertItem(0, new ItemStack(Items.FEATHER), false);
        this.isMixing = false;
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2) { //Controls the amount of slots
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                //Limits the possible input items to the slot
                //If you wanted to go by slot, use a switch statement with "slot" and int cases that return similar statements as below
                return (stack.getItem() == Items.DIRT) || (stack.getItem() == Items.FEATHER);
            }

            @Override
            protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    default:
                        return 64;
                    case 0:
                        return 1;
                    case 1:
                        return 64;
                }
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }



    /******************************************************************************************************************/
    //Shrug Stuff
    /******************************************************************************************************************/

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        nbt.put("inv", itemHandler.serializeNBT());
        return super.save(nbt);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        //This cna be used to make a machine block sided (certain items only go through one side)
        //But it was not shown in the tutorial and I do not need it for this machine.
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }



    /******************************************************************************************************************/
    //Animation Stuff
    /******************************************************************************************************************/


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

    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() { return factory; }
}
