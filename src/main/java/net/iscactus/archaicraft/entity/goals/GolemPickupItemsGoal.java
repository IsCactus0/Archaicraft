package net.iscactus.archaicraft.entity.goals;

import net.iscactus.archaicraft.entity.custom.Golem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.List;

public class GolemPickupItemsGoal extends Goal {
    private final Golem entity;

    public GolemPickupItemsGoal(PathfinderMob mob) {
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        entity = (Golem)mob;
    }

    public boolean canUse() {
        if (!entity.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
            return false;
        } else if (entity.getTarget() == null && entity.getLastHurtByMob() == null) {
            if (entity.getRandom().nextInt(reducedTickDelay(10)) != 0) {
                return false;
            } else {
                List<ItemEntity> list = entity.level().getEntitiesOfClass(ItemEntity.class, entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Golem.ALLOWED_ITEMS);
                return !list.isEmpty() && entity.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
            }
        } else {
            return false;
        }
    }

    public void tick() {
        List<ItemEntity> list = entity.level().getEntitiesOfClass(ItemEntity.class, entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Golem.ALLOWED_ITEMS);
        ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.MAINHAND);
        if (itemstack.isEmpty() && !list.isEmpty()) {
            entity.getNavigation().moveTo(list.get(0), (double)1.2F);
        }
    }

    public void start() {
        List<ItemEntity> list = entity.level().getEntitiesOfClass(ItemEntity.class, entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Golem.ALLOWED_ITEMS);
        if (!list.isEmpty()) {
            entity.getNavigation().moveTo(list.get(0), (double)1.2F);
        }
    }
}