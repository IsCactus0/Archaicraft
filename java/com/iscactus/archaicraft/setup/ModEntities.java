package com.iscactus.archaicraft.setup;

import com.iscactus.archaicraft.Archaicraft;
import com.iscactus.archaicraft.entity.passive.MothEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModEntities {
    public static final RegistryObject<EntityType<MothEntity>> MOTH = register("moth", () -> EntityType.Builder.of (MothEntity::new, EntityClassification.CREATURE)
            .sized(0.2F, 0.2F)
            .build(new ResourceLocation(Archaicraft.MOD_ID, "moth").toString()));

    static void register() {}

    private static <T extends EntityType<?>> RegistryObject<T> register(String name, Supplier<T> entity) {
        return Registration.ENTITIES.register(name, entity);
    }
}
