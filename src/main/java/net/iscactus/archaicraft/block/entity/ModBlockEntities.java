package net.iscactus.archaicraft.block.entity;

import net.iscactus.archaicraft.Archaicraft;
import net.iscactus.archaicraft.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Archaicraft.MODID);

    public static final RegistryObject<BlockEntityType<MortarBlockEntity>> MORTAR =
            BLOCK_ENTITIES.register("mortar", () ->
                    BlockEntityType.Builder.of(MortarBlockEntity::new,
                            ModBlocks.MORTAR.get()).build(null));

    public static final RegistryObject<BlockEntityType<AlchemyTableBlockEntity>> ALCHEMY_TABLE =
            BLOCK_ENTITIES.register("alchemy_table", () ->
                    BlockEntityType.Builder.of(AlchemyTableBlockEntity::new,
                            ModBlocks.ALCHEMY_TABLE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
