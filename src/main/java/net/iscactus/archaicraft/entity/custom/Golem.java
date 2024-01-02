package net.iscactus.archaicraft.entity.custom;

import net.iscactus.archaicraft.entity.goals.GolemAttackGoal;
import net.iscactus.archaicraft.entity.goals.GolemPickCropGoal;
import net.iscactus.archaicraft.entity.goals.GolemPickupItemsGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class Golem extends AbstractGolem {
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(Golem.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    public int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public static final Predicate<ItemEntity> ALLOWED_ITEMS = (itemEntity) -> {
        return !itemEntity.hasPickUpDelay() && itemEntity.isAlive();
    };

    public Golem(EntityType<? extends AbstractGolem> entityType, Level level) {
        super(entityType, level);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new GolemAttackGoal(this, 1.5D, true));
        this.goalSelector.addGoal(2, new GolemPickCropGoal(this, 1.0D, 8, 4));
        this.goalSelector.addGoal(2, new GolemPickupItemsGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, true, (entity) -> entity instanceof Silverfish));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Creeper.class, 6.0F, 1.0D, 1.2D));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.25F)
                .add(Attributes.ARMOR_TOUGHNESS, (double)0.4F)
                .add(Attributes.KNOCKBACK_RESISTANCE, (double)0.5F)
                .add(Attributes.ATTACK_DAMAGE, (double)2F);
    }

    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0.0F) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.getAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 17;
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.getAttacking()) {
            attackAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float partialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(partialTick * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.walkAnimation.update(f, 0.2F);
    }

    public void aiStep() {
        super.aiStep();
    }

    public boolean doHurtTarget(Entity entity) {
        boolean flag = entity.hurt(this.damageSources().mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) { this.doEnchantDamageEffects(this, entity); }
        return flag;
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    protected float getStandingEyeHeight(Pose pos, EntityDimensions size) {
        return 0.9F;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.PACKED_MUD_HIT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.PACKED_MUD_BREAK;
    }

    public boolean hasFarmSeeds() {
        return this.getOffhandItem().is(ItemTags.VILLAGER_PLANTABLE_SEEDS) ||
                this.getMainHandItem().is(ItemTags.VILLAGER_PLANTABLE_SEEDS);
    }

    public void swapHands() {
        ItemStack temp = Golem.this.getOffhandItem();
        Golem.this.setItemInHand(InteractionHand.OFF_HAND, Golem.this.getMainHandItem());
        Golem.this.setItemInHand(InteractionHand.MAIN_HAND, temp);
    }
}
