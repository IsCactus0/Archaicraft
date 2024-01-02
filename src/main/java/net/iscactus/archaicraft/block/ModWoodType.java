package net.iscactus.archaicraft.block;

import net.iscactus.archaicraft.Archaicraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType {
    public static WoodType BITTERSWEET = WoodType.register(new WoodType(new ResourceLocation(Archaicraft.MODID, "bittersweet").toString(), ModBlockSetType.BITTERSWEET));
    public static WoodType GHOSTCAP = WoodType.register(new WoodType(new ResourceLocation(Archaicraft.MODID, "ghostcap").toString(), ModBlockSetType.GHOSTCAP));
}
