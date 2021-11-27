package com.iscactus.archaicraft.setup;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModSoundEvents {
    public static final RegistryObject<SoundEvent> OIL_AMBIENT = register("block.oil.ambient", () -> new SoundEvent(new ResourceLocation("block.oil.ambient")));

    static void register() {}

    private static <T extends SoundEvent> RegistryObject<T> register(String name, Supplier<T> soundEvent) {
        return Registration.SOUND_EVENTS.register(name, soundEvent);
    }
}
