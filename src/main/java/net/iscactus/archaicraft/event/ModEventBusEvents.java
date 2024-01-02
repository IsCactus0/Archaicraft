package net.iscactus.archaicraft.event;

import net.iscactus.archaicraft.Archaicraft;
import net.iscactus.archaicraft.entity.ModEntities;
import net.iscactus.archaicraft.entity.client.GolemModel;
import net.iscactus.archaicraft.entity.client.ModModelLayers;
import net.iscactus.archaicraft.entity.custom.Golem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Archaicraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GOLEM.get(), Golem.createAttributes().build());
    }
}