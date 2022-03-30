package io.github.firetamer.dbb.modules.strong_block.tiles;

import io.github.firetamer.dbb.modules.strong_block.blocks.full_block.WarenaiBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import io.github.firetamer.dbb.modules.strong_block.StrongBlockModule;
import io.github.firetamer.dbb.modules.strong_block.util.WarenaiBlockCondition;

public class StrongBlockTile extends TileEntity implements ITickableTileEntity
{
    /**
     * This is just the initial value. If you look at the "polishBlock" method you will see that the absolute maximum is 3000
     * You can just tweak the values and such how you want, you can even set the value below to any starting number you choose
     */
    private int strongBlockHealth = 3000;

    public StrongBlockTile() {
        super(StrongBlockModule.STRONG_BLOCK_TILE);
    }


    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~**/


    public void settingBlockValue(WarenaiBlockCondition condition) {
        Block thisBlock = this.level.getBlockState(this.worldPosition).getBlock();
        BlockState thisBlockState = this.level.getBlockState(this.worldPosition);

        if (thisBlock instanceof WarenaiBlock) {
            this.level.setBlock(this.worldPosition, thisBlockState.setValue(WarenaiBlock.BLOCK_CONDITION, condition), 3);
        }
        /**
        if (thisBlock instanceof WarenaiBlockStairs) {
            this.level.setBlock(this.worldPosition, thisBlockState.setValue(WarenaiBlockStairs.BLOCK_CONDITION, condition), 3);
        }
        if (thisBlock instanceof WarenaiBlockSlab) {
            this.level.setBlock(this.worldPosition, thisBlockState.setValue(WarenaiBlockSlab.BLOCK_CONDITION, condition), 3);
        }
        if (thisBlock instanceof WarenaiBlockFence) {
            this.level.setBlock(this.worldPosition, thisBlockState.setValue(WarenaiBlockFence.BLOCK_CONDITION, condition), 3);
        }
        if (thisBlock instanceof WarenaiBlockWall) {
            this.level.setBlock(this.worldPosition, thisBlockState.setValue(WarenaiBlockWall.CRACKED_DIRTY_CLEAN_POLISHED, CDCPValue).setValue(WarenaiBlockWall.CRACKED_LEVEL, crackedValue), 3);
        }
        **/
    }

    @Override
    public void tick() {
        if (!this.level.isClientSide()) {
            int currentHealth = getHealth();

            if (currentHealth >= 3001 && currentHealth <= 3100) {
                settingBlockValue(WarenaiBlockCondition.POLISHED);
            }
            if (currentHealth >= 1601 && currentHealth <= 3000) {
                settingBlockValue(WarenaiBlockCondition.NORMAL);
            }
            if (currentHealth >= 801 && currentHealth <= 1600) {
                settingBlockValue(WarenaiBlockCondition.SCUFFED);
            }
            if (currentHealth >= 631 && currentHealth <= 800) {
                settingBlockValue(WarenaiBlockCondition.CRACKED1);
            }
            if (currentHealth >= 461 && currentHealth <= 630) {
                settingBlockValue(WarenaiBlockCondition.CRACKED2);
            }
            if (currentHealth >= 291 && currentHealth <= 460) {
                settingBlockValue(WarenaiBlockCondition.CRACKED3);
            }
            if (currentHealth >= 120 && currentHealth <= 290) {
                settingBlockValue(WarenaiBlockCondition.CRACKED4);
            }
            if (currentHealth == 0) {
                this.level.setBlockAndUpdate(this.worldPosition, Blocks.AIR.defaultBlockState());
            }
        }
    }

    public int getHealth() { return this.strongBlockHealth; }

    public void polishBlock(int polishAmount) {
        final int maximumHealthValueForPolish = 3100;
        int currentMaxPolishValue = maximumHealthValueForPolish - getHealth();
        int currentHealth = getHealth();

        if (currentHealth >= 3000) {
            if (polishAmount > currentMaxPolishValue) {
                this.strongBlockHealth = maximumHealthValueForPolish;
            } else {
                this.strongBlockHealth = getHealth() + polishAmount;
            }
        }

        this.setChanged();
    }

    public void repairBlock(int repairAmount) {
        final int maximumHealthValueForRepair = 3000;
        int currentMaxRepairValue = maximumHealthValueForRepair - getHealth();

        if (getHealth() >= 3001) {
            this.strongBlockHealth = getHealth();
        } else if (repairAmount > currentMaxRepairValue) {
            this.strongBlockHealth = maximumHealthValueForRepair;
        } else {
            this.strongBlockHealth = getHealth() + repairAmount;
        }

        this.setChanged();
    }

    public void damageBlock(int damageAmount) {
        final int minimumHealth = 0;
        int currentMaxDamageValue = getHealth();

        if (damageAmount > currentMaxDamageValue) {
            this.strongBlockHealth = minimumHealth;
        } else {
            this.strongBlockHealth = getHealth() - damageAmount;
        }

        this.setChanged();
    }



    /**
     * Overrides
     */

    @Override
    public CompoundNBT save(CompoundNBT tags) {
        tags.putInt("BlockHealth", this.strongBlockHealth);
        return super.save(tags);
    }

    @Override
    public void load(BlockState state, CompoundNBT tags) {
        if (tags.contains("BlockHealth")) this.strongBlockHealth = tags.getInt("BlockHealth");
        super.load(state, tags);
    }
}
