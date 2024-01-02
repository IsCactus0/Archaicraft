package net.iscactus.archaicraft.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties ALOE_LEAF = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).fast().build();
    public static final FoodProperties AMBROSIA = (new FoodProperties.Builder()).nutrition(1).saturationMod(1.3F).alwaysEat().effect(new MobEffectInstance(MobEffects.REGENERATION, 128, 1), 1.0F).fast().build();
    public static final FoodProperties CITRON = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties JAY_EGG = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.8F).build();
}