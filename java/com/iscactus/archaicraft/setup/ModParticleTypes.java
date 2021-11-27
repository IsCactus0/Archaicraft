package com.iscactus.archaicraft.setup;

import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModParticleTypes {
    static void register() {}
    private static <T extends BasicParticleType> RegistryObject<T> register(String name, Supplier<T> particle) {
        return Registration.PARTICLE_TYPES.register(name, particle);
    }
}