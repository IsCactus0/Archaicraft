package net.iscactus.archaicraft.item;

import net.iscactus.archaicraft.Archaicraft;
import net.iscactus.archaicraft.block.ModBlocks;
import net.iscactus.archaicraft.entity.ModEntities;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create( ForgeRegistries.ITEMS, Archaicraft.MODID);

    public static final RegistryObject<Item> ALOE_LEAF = ITEMS.register("aloe_leaf", () -> new Item(new Item.Properties().food(ModFoods.ALOE_LEAF)));
    public static final RegistryObject<Item> ALOE_SALVE = ITEMS.register("aloe_salve", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMBROSIA = ITEMS.register("ambrosia", () -> new Item(new Item.Properties().food(ModFoods.AMBROSIA)));
    public static final RegistryObject<Item> CITRON = ITEMS.register("citron", () -> new Item(new Item.Properties().food(ModFoods.CITRON)));
    public static final RegistryObject<Item> COTTON = ITEMS.register("cotton", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COTTON_SEEDS = ITEMS.register("cotton_seeds", () -> new ItemNameBlockItem(ModBlocks.COTTON_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> JAY_EGG = ITEMS.register("jay_egg", () -> new Item(new Item.Properties().food(ModFoods.JAY_EGG)));
    public static final RegistryObject<Item> BITTERSWEET_BOAT = ITEMS.register("bittersweet_boat", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BITTERSWEET_CHEST_BOAT = ITEMS.register("bittersweet_chest_boat", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BITTERSWEET_SIGN = ITEMS.register("bittersweet_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), ModBlocks.BITTERSWEET_SIGN.get(), ModBlocks.BITTERSWEET_WALL_SIGN.get()));
    public static final RegistryObject<Item> BITTERSWEET_HANGING_SIGN = ITEMS.register("bittersweet_hanging_sign", () -> new HangingSignItem(ModBlocks.BITTERSWEET_HANGING_SIGN.get(), ModBlocks.BITTERSWEET_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
    public static final RegistryObject<Item> GHOSTCAP_BOAT = ITEMS.register("ghostcap_boat", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GHOSTCAP_CHEST_BOAT = ITEMS.register("ghostcap_chest_boat", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GHOSTCAP_SIGN = ITEMS.register("ghostcap_sign", () -> new SignItem((new Item.Properties()).stacksTo(16), ModBlocks.GHOSTCAP_SIGN.get(), ModBlocks.GHOSTCAP_WALL_SIGN.get()));
    public static final RegistryObject<Item> GHOSTCAP_HANGING_SIGN = ITEMS.register("ghostcap_hanging_sign", () -> new HangingSignItem(ModBlocks.GHOSTCAP_HANGING_SIGN.get(), ModBlocks.GHOSTCAP_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
    public static final RegistryObject<Item> RAW_MYTHRIL = ITEMS.register("raw_mythril", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_INGOT = ITEMS.register("mythril_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_NUGGET = ITEMS.register("mythril_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_SWORD = ITEMS.register("mythril_sword", () ->  new SwordItem(ModTiers.MITHRIL, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_SHOVEL = ITEMS.register("mythril_shovel", () ->  new ShovelItem(ModTiers.MITHRIL, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_PICKAXE = ITEMS.register("mythril_pickaxe", () ->  new PickaxeItem(ModTiers.MITHRIL, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_AXE = ITEMS.register("mythril_axe", () ->  new AxeItem(ModTiers.MITHRIL, 6.0F, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_HOE = ITEMS.register("mythril_hoe", () ->  new HoeItem(ModTiers.MITHRIL, -2, -1.0F, new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_HELMET = ITEMS.register("mythril_helmet", () ->  new ArmorItem(ModArmourMaterials.MITHRIL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_CHESTPLATE = ITEMS.register("mythril_chestplate", () ->  new ArmorItem(ModArmourMaterials.MITHRIL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_LEGGINGS = ITEMS.register("mythril_leggings", () ->  new ArmorItem(ModArmourMaterials.MITHRIL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_BOOTS = ITEMS.register("mythril_boots", () ->  new ArmorItem(ModArmourMaterials.MITHRIL, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> MYTHRIL_HORSE_ARMOR = ITEMS.register("mythril_horse_armour", () ->  new HorseArmorItem(17, new ResourceLocation(Archaicraft.MODID, "textures/entity/horse/armour/horse_armour_mythril.png"), (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> GOLEM_SPAWN_EGG = ITEMS.register("golem_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.GOLEM, 9394240, 16162420, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}