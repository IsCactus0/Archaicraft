package com.iscactus.archaicraft.util;

import com.iscactus.archaicraft.Archaicraft;
import com.iscactus.archaicraft.setup.ModParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Archaicraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleHandler {
    private ParticleHandler() {}

    @SubscribeEvent
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
    }
}
