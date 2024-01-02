package net.iscactus.archaicraft.entity.goals;

import net.iscactus.archaicraft.common.ModTags;
import net.iscactus.archaicraft.entity.custom.Golem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;

public class GolemPickCropGoal extends MoveToBlockGoal {
    private final Golem entity;

    @Nullable
    private BlockPos aboveFarmlandPos;
    private static final int WAIT_TICKS = 40;
    protected int ticksWaited;

    public GolemPickCropGoal(PathfinderMob mob, double speedModifier, int searchRange, int verticalSearchRange) {
        super(mob, speedModifier, searchRange, verticalSearchRange);
        entity = (Golem)mob;
    }

    public double acceptedDistance() {
        return 2.0D;
    }

    public boolean shouldRecalculatePath() {
        return this.tryTicks % 100 == 0;
    }

    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos);
        Block block = blockstate.getBlock();
        Block below = level.getBlockState(pos.below()).getBlock();
        return block instanceof CropBlock && ((CropBlock)block).isMaxAge(blockstate) || blockstate.isAir() && below instanceof FarmBlock;
    }

    public void tick() {
        if (this.isReachedTarget()) {
            if (this.ticksWaited >= 40) {
                this.onReachedTarget();
            } else {
                ++this.ticksWaited;
            }
        }

        super.tick();
    }

    protected void onReachedTarget() {
        if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(entity.level(), entity)) {
            if (this.aboveFarmlandPos != null && this.aboveFarmlandPos.closerToCenterThan(entity.position(), 1.0D)) {
                BlockState blockstate = entity.level().getBlockState(this.aboveFarmlandPos);
                Block block = blockstate.getBlock();
                Block below = entity.level().getBlockState(this.aboveFarmlandPos.below()).getBlock();

                if (block instanceof CropBlock && ((CropBlock)block).isMaxAge(blockstate)) {
                    this.pickCrop();
                } else if (blockstate.isAir() && below instanceof FarmBlock && entity.hasFarmSeeds()) {
                    this.plantCrop();
                }
            }
        }
    }

    private void plantCrop() {
        Level level = entity.level();
        ItemStack itemstack = entity.getItemInHand(InteractionHand.MAIN_HAND);
        if (itemstack.isEmpty() || !itemstack.is(ModTags.Items.SEEDS))
            entity.swapHands();

        boolean flag = false;
        if (!itemstack.isEmpty() && itemstack.is(ModTags.Items.SEEDS)) {
            Item item = itemstack.getItem();
            if (item instanceof BlockItem) {
                BlockItem blockitem = (BlockItem)item;
                BlockState cropBlock = blockitem.getBlock().defaultBlockState();
                level.setBlockAndUpdate(this.aboveFarmlandPos, cropBlock);
                level.gameEvent(GameEvent.BLOCK_PLACE, this.aboveFarmlandPos, GameEvent.Context.of(entity, cropBlock));
                flag = true;
            } else if (itemstack.getItem() instanceof net.minecraftforge.common.IPlantable) {
                if (((net.minecraftforge.common.IPlantable)itemstack.getItem()).getPlantType(level, aboveFarmlandPos) == net.minecraftforge.common.PlantType.CROP) {
                    level.setBlock(aboveFarmlandPos, ((net.minecraftforge.common.IPlantable)itemstack.getItem()).getPlant(level, aboveFarmlandPos), 3);
                    flag = true;
                }
            }
        }

        if (flag) {
            level.playSound((Player)null, (double)this.aboveFarmlandPos.getX(), (double)this.aboveFarmlandPos.getY(), (double)this.aboveFarmlandPos.getZ(), SoundEvents.CROP_PLANTED, SoundSource.BLOCKS, 1.0F, 1.0F);
            itemstack.shrink(1);
            if (itemstack.isEmpty()) {
                entity.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            }
        }
    }

    private void pickCrop() {
        entity.level().destroyBlock(this.aboveFarmlandPos, true, entity);
    }
}