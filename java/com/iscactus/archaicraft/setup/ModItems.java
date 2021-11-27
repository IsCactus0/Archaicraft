package com.iscactus.archaicraft.setup;

import com.iscactus.archaicraft.material.ModArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {
    public static final RegistryObject<Item> MOONSTONE = Registration.ITEMS.register("moonstone", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> CINNABAR = Registration.ITEMS.register("cinnabar", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> SULPHUR = Registration.ITEMS.register("sulphur", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> ARCTICITE = Registration.ITEMS.register("arcticite", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> MOONSTONE_POWDER = Registration.ITEMS.register("moonstone_powder", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> CINNABAR_POWDER = Registration.ITEMS.register("cinnabar_powder", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> SULPHUR_POWDER = Registration.ITEMS.register("sulphur_powder", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> ARCTICITE_POWDER = Registration.ITEMS.register("arcticite_powder", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> COAL_POWDER = Registration.ITEMS.register("coal_powder", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> CHARCOAL_POWDER = Registration.ITEMS.register("charcoal_powder", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> LAPIS_POWDER = Registration.ITEMS.register("lapis_powder", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> IRON_POWDER = Registration.ITEMS.register("iron_powder", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> GOLD_POWDER = Registration.ITEMS.register("gold_powder", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));

    public static final RegistryObject<Item> DRAGON_SCALE = Registration.ITEMS.register("dragon_scale", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> CHITIN = Registration.ITEMS.register("chitin", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> FABRIC = Registration.ITEMS.register("fabric", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> SCRAP_NUT = Registration.ITEMS.register("scrap_nut", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> SCRAP_SPRING = Registration.ITEMS.register("scrap_spring", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> SCRAP_COG = Registration.ITEMS.register("scrap_cog", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> SCRAP_PIPE = Registration.ITEMS.register("scrap_pipe", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> SCRAP_INGOT = Registration.ITEMS.register("scrap_ingot", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> ASH_BRICK = Registration.ITEMS.register("ash_brick", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> MUD_BRICK = Registration.ITEMS.register("mud_brick", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> MUD_BALL = Registration.ITEMS.register("mud_ball", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> MERCURY = Registration.ITEMS.register("mercury", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> PEARL = Registration.ITEMS.register("pearl", () -> new Item((new Item.Properties()).tab(ModItemGroup.TAB_ARCHAICRAFT)));

    public static final RegistryObject<Item> FABRIC_HAT = Registration.ITEMS.register("fabric_helmet", () -> new ArmorItem(ModArmorMaterial.FABRIC, EquipmentSlotType.HEAD, new Item.Properties().tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> FABRIC_SHIRT = Registration.ITEMS.register("fabric_chestplate", () -> new ArmorItem(ModArmorMaterial.FABRIC, EquipmentSlotType.CHEST, new Item.Properties().tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> FABRIC_PANTS = Registration.ITEMS.register("fabric_leggings", () -> new ArmorItem(ModArmorMaterial.FABRIC, EquipmentSlotType.LEGS, new Item.Properties().tab(ModItemGroup.TAB_ARCHAICRAFT)));
    public static final RegistryObject<Item> FABRIC_SHOES = Registration.ITEMS.register("fabric_boots", () -> new ArmorItem(ModArmorMaterial.FABRIC, EquipmentSlotType.FEET, new Item.Properties().tab(ModItemGroup.TAB_ARCHAICRAFT)));

    static void register() {};
}
