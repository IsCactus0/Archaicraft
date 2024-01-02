package net.iscactus.archaicraft.entity;

import net.iscactus.archaicraft.Archaicraft;
import net.iscactus.archaicraft.entity.custom.Golem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Archaicraft.MODID);

    public static final RegistryObject<EntityType<Golem>> GOLEM =
            ENTITY_TYPES.register("golem", () -> EntityType.Builder.of(Golem::new, MobCategory.MISC)
                    .sized(0.6f, 0.6f).clientTrackingRange(8).build("golem"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}