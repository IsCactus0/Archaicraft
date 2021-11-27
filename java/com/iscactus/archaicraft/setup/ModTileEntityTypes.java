package com.iscactus.archaicraft.setup;

import com.iscactus.archaicraft.block.mortar.MortarTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModTileEntityTypes {

    public static final RegistryObject<TileEntityType<MortarTileEntity>> MORTAR = register("mortar", MortarTileEntity::new, ModBlocks.MORTAR);

    static void register() {}

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> factory, RegistryObject<? extends Block> block) {
        return Registration.TILE_ENTITIES.register(name, () -> {
            return TileEntityType.Builder.of(factory, block.get()).build(null);
        });
    }
}