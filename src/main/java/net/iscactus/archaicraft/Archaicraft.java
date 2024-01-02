package net.iscactus.archaicraft;

import net.iscactus.archaicraft.block.ModBlocks;
import net.iscactus.archaicraft.block.entity.ModBlockEntities;
import net.iscactus.archaicraft.entity.ModEntities;
import net.iscactus.archaicraft.entity.client.GolemRenderer;
import net.iscactus.archaicraft.item.ModCreativeModeTabs;
import net.iscactus.archaicraft.item.ModItems;
import net.iscactus.archaicraft.recipe.ModRecipes;
import net.iscactus.archaicraft.screen.AlchemyTableScreen;
import net.iscactus.archaicraft.screen.ModMenuTypes;
import net.iscactus.archaicraft.screen.MortarScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.swing.text.html.parser.Entity;

@Mod(Archaicraft.MODID)
public class Archaicraft {
    public static final String MODID = "archaicraft";

    public Archaicraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.GOLEM.get(), GolemRenderer::new);

            MenuScreens.register(ModMenuTypes.ALCHEMY_TABLE_MENU.get(), AlchemyTableScreen::new);
            MenuScreens.register(ModMenuTypes.MORTAR_MENU.get(), MortarScreen::new);
        }
    }
}