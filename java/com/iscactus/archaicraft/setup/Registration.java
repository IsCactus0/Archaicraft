package com.iscactus.archaicraft.setup;

import com.iscactus.archaicraft.Archaicraft;
import com.iscactus.archaicraft.entity.passive.MothEntity;
import com.iscactus.archaicraft.world.gen.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.WoodType;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.particles.ParticleType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class Registration {
    // Game objects
    public static final DeferredRegister<Block> BLOCKS = create(ForgeRegistries.BLOCKS);
    public static final DeferredRegister<Fluid> FLUIDS = create(ForgeRegistries.FLUIDS);
    public static final DeferredRegister<Item> ITEMS = create(ForgeRegistries.ITEMS);
    public static final DeferredRegister<EntityType<?>> ENTITIES = create(ForgeRegistries.ENTITIES);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = create(ForgeRegistries.TILE_ENTITIES);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = create(ForgeRegistries.PARTICLE_TYPES);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = create(ForgeRegistries.CONTAINERS);
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = create(ForgeRegistries.RECIPE_SERIALIZERS);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = create(ForgeRegistries.SOUND_EVENTS);

    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);

        ITEMS.register(modEventBus);
        ENTITIES.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        PARTICLE_TYPES.register(modEventBus);
        CONTAINERS.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        SOUND_EVENTS.register(modEventBus);

        ModBlocks.register();
        ModItems.register();
        ModEntities.register();
        ModContainerTypes.register();
        ModTileEntityTypes.register();
        ModRecipes.register();
        ModSoundEvents.register();
        ModParticleTypes.register();

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
    }

    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntities.MOTH.get(), MothEntity.createAttributes().build());
        });
    }

    private static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> create(IForgeRegistry<T> registry) {
        return DeferredRegister.create(registry, Archaicraft.MOD_ID);
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Archaicraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Client {
        private Client() {}

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModContainerTypes.registerScreens(event);

            RenderTypeLookup.setRenderLayer(ModBlocks.ASPEN_SAPLING.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.ASPEN_DOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.ASPEN_TRAPDOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_ASPEN_SAPLING.get(), RenderType.cutoutMipped());

            RenderTypeLookup.setRenderLayer(ModBlocks.BEECH_SAPLING.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.BEECH_DOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.BEECH_TRAPDOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_BEECH_SAPLING.get(), RenderType.cutoutMipped());

            RenderTypeLookup.setRenderLayer(ModBlocks.CITRON_SAPLING.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.CITRON_DOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.CITRON_TRAPDOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_CITRON_SAPLING.get(), RenderType.cutoutMipped());

            RenderTypeLookup.setRenderLayer(ModBlocks.EUCALYPTUS_SAPLING.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.EUCALYPTUS_DOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.EUCALYPTUS_TRAPDOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_EUCALYPTUS_SAPLING.get(), RenderType.cutoutMipped());

            RenderTypeLookup.setRenderLayer(ModBlocks.MANGROVE_AERIAL_ROOTS.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.MANGROVE_STEM.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.MANGROVE_STEM_PLANT.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.MANGROVE_SAPLING.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.MANGROVE_DOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.MANGROVE_TRAPDOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_MANGROVE_SAPLING.get(), RenderType.cutoutMipped());

            RenderTypeLookup.setRenderLayer(ModBlocks.MAPLE_SAPLING.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.MAPLE_DOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.MAPLE_TRAPDOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_MAPLE_SAPLING.get(), RenderType.cutoutMipped());

            RenderTypeLookup.setRenderLayer(ModBlocks.REDWOOD_SAPLING.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.REDWOOD_DOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.REDWOOD_TRAPDOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.POTTED_REDWOOD_SAPLING.get(), RenderType.cutoutMipped());

            RenderTypeLookup.setRenderLayer(ModBlocks.DEAD_DOOR.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.DEAD_TRAPDOOR.get(), RenderType.cutoutMipped());

            RenderTypeLookup.setRenderLayer(ModBlocks.CHARRED_GRASS.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.DRIED_GRASS.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.WOLFSBANE.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.CATTAIL.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.CLOVER.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.AZURECAP.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.BITTERSWEET.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.CHALKGILL.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.MIDNIGHT_GOBLET.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.OCRE_AGARIC.get(), RenderType.cutoutMipped());
            RenderTypeLookup.setRenderLayer(ModBlocks.WIZARDS_SPIRE.get(), RenderType.cutoutMipped());

            WoodType.create("aspen");
            Atlases.addWoodType(ModWoodType.ASPEN);
            WoodType.create("beech");
            Atlases.addWoodType(ModWoodType.BEECH);
            WoodType.create("maple");
            Atlases.addWoodType(ModWoodType.MAPLE);
            WoodType.create("citrus");
            Atlases.addWoodType(ModWoodType.CITRON);
            WoodType.create("mangrove");
            Atlases.addWoodType(ModWoodType.MANGROVE);
            WoodType.create("eucalyptus");
            Atlases.addWoodType(ModWoodType.EUCALYPTUS);
            WoodType.create("redwood");
            Atlases.addWoodType(ModWoodType.REDWOOD);
            WoodType.create("dead");
            Atlases.addWoodType(ModWoodType.DEAD);
        }
    }
}