package com.iscactus.archaicraft.setup;

import net.minecraft.block.WoodType;

public class ModWoodType extends WoodType {
    public static final WoodType ASPEN = register(new ModWoodType("aspen"));
    public static final WoodType BEECH = register(new ModWoodType("beech"));
    public static final WoodType MAPLE = register(new ModWoodType("maple"));
    public static final WoodType CITRON = register(new ModWoodType("citron"));
    public static final WoodType MANGROVE = register(new ModWoodType("mangrove"));
    public static final WoodType EUCALYPTUS = register(new ModWoodType("eucalyptus"));
    public static final WoodType REDWOOD = register(new ModWoodType("redwood"));
    public static final WoodType DEAD = register(new ModWoodType("dead"));

    protected ModWoodType(String name) {
        super(name);
    }
}
