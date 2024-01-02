package net.iscactus.archaicraft.item;

import net.iscactus.archaicraft.Archaicraft;
import net.iscactus.archaicraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Archaicraft.MODID);

    public static final RegistryObject<CreativeModeTab> ARCHAIC_TAB =
            CREATIVE_MODE_TABS.register("", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + Archaicraft.MODID + ".archaic"))
                    .icon(() -> new ItemStack(ModBlocks.BITTERSWEET.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.SILTSTONE.get());
                        output.accept(ModBlocks.SILTSTONE_STAIRS.get());
                        output.accept(ModBlocks.SILTSTONE_SLAB.get());
                        output.accept(ModBlocks.SILTSTONE_WALL.get());
                        output.accept(ModBlocks.SILTSTONE_BRICKS.get());
                        output.accept(ModBlocks.SILTSTONE_BRICK_STAIRS.get());
                        output.accept(ModBlocks.SILTSTONE_BRICK_SLAB.get());
                        output.accept(ModBlocks.SILTSTONE_BRICK_WALL.get());
                        output.accept(ModBlocks.SILTSTONE_MOSAIC.get());
                        output.accept(ModBlocks.SILTSTONE_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.SILTSTONE_MOSAIC_SLAB.get());
                        output.accept(ModBlocks.SILT.get());
                        output.accept(ModBlocks.DRY_GRASS_BLOCK.get());
                        output.accept(ModBlocks.DRY_GRASS.get());
                        output.accept(ModBlocks.BITTERSWEET_STEM.get());
                        output.accept(ModBlocks.BITTERSWEET_WOOD.get());
                        output.accept(ModBlocks.BITTERSWEET_PLANKS.get());
                        output.accept(ModBlocks.BITTERSWEET_STAIRS.get());
                        output.accept(ModBlocks.BITTERSWEET_SLAB.get());
                        output.accept(ModBlocks.BITTERSWEET_FENCE.get());
                        output.accept(ModBlocks.BITTERSWEET_FENCE_GATE.get());
                        output.accept(ModBlocks.BITTERSWEET_DOOR.get());
                        output.accept(ModBlocks.BITTERSWEET_TRAPDOOR.get());
                        output.accept(ModBlocks.BITTERSWEET_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.BITTERSWEET_BUTTON.get());
                        output.accept(ModBlocks.BITTERSWEET_GILLS.get());
                        output.accept(ModBlocks.BITTERSWEET_PILEUS.get());
                        output.accept(ModBlocks.BITTERSWEET.get());
                        output.accept(ModItems.BITTERSWEET_SIGN.get());
                        output.accept(ModItems.BITTERSWEET_HANGING_SIGN.get());
                        output.accept(ModItems.BITTERSWEET_BOAT.get());
                        output.accept(ModItems.BITTERSWEET_CHEST_BOAT.get());
                        output.accept(ModBlocks.GHOSTCAP_STEM.get());
                        output.accept(ModBlocks.GHOSTCAP_ROOTS.get());
                        output.accept(ModBlocks.GHOSTCAP_PLANKS.get());
                        output.accept(ModBlocks.GHOSTCAP_STAIRS.get());
                        output.accept(ModBlocks.GHOSTCAP_SLAB.get());
                        output.accept(ModBlocks.GHOSTCAP_FENCE.get());
                        output.accept(ModBlocks.GHOSTCAP_FENCE_GATE.get());
                        output.accept(ModBlocks.GHOSTCAP_DOOR.get());
                        output.accept(ModBlocks.GHOSTCAP_TRAPDOOR.get());
                        output.accept(ModBlocks.GHOSTCAP_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.GHOSTCAP_BUTTON.get());
                        output.accept(ModBlocks.GHOSTCAP_PILEUS.get());
                        output.accept(ModBlocks.GHOSTCAP.get());
                        output.accept(ModItems.GHOSTCAP_SIGN.get());
                        output.accept(ModItems.GHOSTCAP_HANGING_SIGN.get());
                        output.accept(ModItems.GHOSTCAP_BOAT.get());
                        output.accept(ModItems.GHOSTCAP_CHEST_BOAT.get());
                        output.accept(ModBlocks.ALCHEMY_TABLE.get());
                        output.accept(ModBlocks.MORTAR.get());
                        output.accept(ModBlocks.KILN.get());
                        output.accept(ModItems.ALOE_SALVE.get());
                        output.accept(ModBlocks.ALOE_PLANT.get());
                        output.accept(ModItems.ALOE_LEAF.get());
                        output.accept(ModBlocks.IVY.get());
                        output.accept(ModItems.AMBROSIA.get());
                        output.accept(ModItems.COTTON.get());
                        output.accept(ModItems.COTTON_SEEDS.get());
                        output.accept(ModItems.JAY_EGG.get());
                        output.accept(ModItems.CITRON.get());
                        output.accept(ModBlocks.MYTHRIL_BLOCK.get());
                        output.accept(ModBlocks.MYTHRIL_ORE.get());
                        output.accept(ModBlocks.RAW_MYTHRIL_BLOCK.get());
                        output.accept(ModItems.MYTHRIL_SHOVEL.get());
                        output.accept(ModItems.MYTHRIL_PICKAXE.get());
                        output.accept(ModItems.MYTHRIL_AXE.get());
                        output.accept(ModItems.MYTHRIL_HOE.get());
                        output.accept(ModItems.MYTHRIL_SWORD.get());
                        output.accept(ModItems.MYTHRIL_HELMET.get());
                        output.accept(ModItems.MYTHRIL_CHESTPLATE.get());
                        output.accept(ModItems.MYTHRIL_LEGGINGS.get());
                        output.accept(ModItems.MYTHRIL_BOOTS.get());
                        output.accept(ModItems.MYTHRIL_HORSE_ARMOR.get());
                        output.accept(ModItems.RAW_MYTHRIL.get());
                        output.accept(ModItems.MYTHRIL_NUGGET.get());
                        output.accept(ModItems.MYTHRIL_INGOT.get());
                        output.accept(ModItems.GOLEM_SPAWN_EGG.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
