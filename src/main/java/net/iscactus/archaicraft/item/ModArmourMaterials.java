package net.iscactus.archaicraft.item;

import java.util.EnumMap;
import java.util.function.Supplier;

import net.iscactus.archaicraft.Archaicraft;
import net.iscactus.archaicraft.common.ModTags;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmourMaterials implements StringRepresentable, ArmorMaterial {
    MITHRIL("mithril", 26, Util.make(new EnumMap<>(ArmorItem.Type.class), (armour) -> {
        armour.put(ArmorItem.Type.BOOTS, 4);
        armour.put(ArmorItem.Type.LEGGINGS, 7);
        armour.put(ArmorItem.Type.CHESTPLATE, 9);
        armour.put(ArmorItem.Type.HELMET, 4);
    }), 25, SoundEvents.ARMOR_EQUIP_CHAIN, 4f, 0.2F, () -> {
        return Ingredient.of(ModItems.MYTHRIL_INGOT.get());
    }),
    WINTER("winter", 11, Util.make(new EnumMap<>(ArmorItem.Type.class), (armour) -> {
        armour.put(ArmorItem.Type.BOOTS, 2);
        armour.put(ArmorItem.Type.LEGGINGS, 4);
        armour.put(ArmorItem.Type.CHESTPLATE, 3);
        armour.put(ArmorItem.Type.HELMET, 1);
    }), 17, SoundEvents.ARMOR_EQUIP_LEATHER, 0f, 0F, () -> {
        return Ingredient.of(ModTags.Items.INSULATORS);
    });

    public static final StringRepresentable.EnumCodec<ArmorMaterials> CODEC = StringRepresentable.fromEnum(ArmorMaterials::values);
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (armour) -> {
        armour.put(ArmorItem.Type.BOOTS, 13);
        armour.put(ArmorItem.Type.LEGGINGS, 15);
        armour.put(ArmorItem.Type.CHESTPLATE, 16);
        armour.put(ArmorItem.Type.HELMET, 11);
    });

    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModArmourMaterials(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> protectionFunctionForType, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionFunctionForType = protectionFunctionForType;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getDurabilityForType(ArmorItem.Type type) {
        return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionFunctionForType.get(type);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return Archaicraft.MODID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public String getSerializedName() {
        return this.name;
    }
}