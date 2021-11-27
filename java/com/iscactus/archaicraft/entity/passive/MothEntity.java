package com.iscactus.archaicraft.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.fluid.Fluid;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.ITag;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class MothEntity extends CreatureEntity implements IFlyingAnimal {

    public MothEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.moveControl = new FlyingMovementController(this, 20, true);
        this.setPathfindingMalus(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
        this.setPathfindingMalus(PathNodeType.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(PathNodeType.FENCE, -1.0F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
//        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1D));
//        this.goalSelector.addGoal(3, new MoveToBlockGoal(this, 1D, 10, 10) {
//            @Override
//            protected boolean isValidTarget(IWorldReader worldReader, BlockPos pos) {
//                return worldReader.getLightEmission(pos) > 10;
//            }
//        });
//        this.goalSelector.addGoal(4, new MothEntity.WanderGoal());
    }



    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.FLYING_SPEED, (double)0.6F)
                .add(Attributes.MOVEMENT_SPEED, (double)0.3F);
    }

    protected PathNavigator createNavigation(World world) {
        FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, world) {
            public boolean isStableDestination(BlockPos p_188555_1_) {
                return !this.level.getBlockState(p_188555_1_.below()).isAir();
            }

            public void tick() {
                super.tick();
            }
        };
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanFloat(false);
        flyingpathnavigator.setCanPassDoors(true);
        return flyingpathnavigator;
    }

    protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_) {
    }

    protected SoundEvent getAmbientSound() {
        return null;
    }

    public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
        return false;
    }

    protected void checkFallDamage(double p_184231_1_, boolean p_184231_3_, BlockState p_184231_4_, BlockPos p_184231_5_) {
    }

    protected boolean makeFlySound() {
        return true;
    }

    public CreatureAttribute getMobType() {
        return CreatureAttribute.ARTHROPOD;
    }

    protected void jumpInLiquid(ITag<Fluid> p_180466_1_) {
        this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.01D, 0.0D));
    }

    class WanderGoal extends Goal {
        WanderGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return MothEntity.this.navigation.isDone() && MothEntity.this.random.nextInt(10) == 0;
        }

        public boolean canContinueToUse() {
            return MothEntity.this.navigation.isInProgress();
        }

        public void start() {
            Vector3d vector3d = this.findPos();
            if (vector3d != null) {
                MothEntity.this.navigation.moveTo(MothEntity.this.navigation.createPath(new BlockPos(vector3d), 1), 1.0D);
            }

        }

        @Nullable
        private Vector3d findPos() {
            Vector3d vector3d = MothEntity.this.getViewVector(0.0F);
            int i = 8;
            Vector3d vector3d2 = RandomPositionGenerator.getAboveLandPos(MothEntity.this, 8, 7, vector3d, ((float)Math.PI / 2F), 2, 1);
            return RandomPositionGenerator.getAirPos(MothEntity.this, 8, 4, -2, vector3d, (double)((float)Math.PI / 2F));
        }
    }
}