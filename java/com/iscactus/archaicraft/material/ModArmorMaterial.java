package com.iscactus.archaicraft.material;

import com.iscactus.archaicraft.setup.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmorMaterial implements IArmorMaterial {

    SCALEHIDE("scalehide",53, new int[] { 4, 7, 9, 4 }, 7, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.5F, 0.2F, () -> { return Ingredient.of(ModItems.DRAGON_SCALE.get()); }),
    SCRAP(    "scrap",    25, new int[] { 2, 5, 6, 3 }, 9, SoundEvents.ARMOR_EQUIP_NETHERITE, 1.0F, 0.0F, () -> { return Ingredient.of(ModItems.SCRAP_INGOT.get()); }),
    FABRIC("fabric", 5, new int[] { 1, 2, 3, 1 }, 12, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> { return Ingredient.of(ModItems.FABRIC.get()); });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;

    private ModArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyValue<>(repairIngredient);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType equipmentSlotType) {
        return HEALTH_PER_SLOT[equipmentSlotType.getIndex()] * this.durabilityMultiplier;
    }
    @Override
    public int getDefenseForSlot(EquipmentSlotType equipmentSlotType) {
        return this.slotProtections[equipmentSlotType.getIndex()];
    }
    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }
    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }
    @Override
    public float getToughness() {
        return this.toughness;
    }
    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
