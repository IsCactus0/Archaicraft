package net.iscactus.archaicraft.entity.goals;

import net.iscactus.archaicraft.entity.custom.Golem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class GolemAttackGoal extends MeleeAttackGoal {
    private final Golem entity;
    private int attackDelay = 5;
    private int ticksUntilNextAttack = 12;
    private boolean shouldCountTillNextAttack = false;

    public GolemAttackGoal(PathfinderMob mob, double speedModifier, boolean targetEvenIfNotSeen) {
        super(mob, speedModifier, targetEvenIfNotSeen);
        entity = (Golem)mob;
    }

    @Override
    public void tick() {
        super.tick();
        if (shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    @Override
    public void start() {
        super.start();
        attackDelay = 5;
        ticksUntilNextAttack = 12;
    }

    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity entity, double distToEntitySqr) {
        if (isEnemyWithinAttackDistance(entity, distToEntitySqr)) {
            shouldCountTillNextAttack = true;

            if(isTimeToStartAttackAnimation()) {
                this.entity.setAttacking(true);
            }

            if(isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(entity.getX(), entity.getEyeY(), entity.getZ());
                performAttack(entity);
            }
        } else {
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            this.entity.setAttacking(false);
            this.entity.attackAnimationTimeout = 0;
        }
    }

    private boolean isEnemyWithinAttackDistance(LivingEntity entity, double distToEntitySqr) {
        return distToEntitySqr <= this.getAttackReachSqr(entity);
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(17);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    protected void performAttack(LivingEntity entity) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(entity);
    }
}