package com.iscactus.archaicraft.setup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModItemGroup extends ItemGroup {
    public static final ModItemGroup TAB_ARCHAICRAFT = new ModItemGroup("archaicraft", true) {
        @OnlyIn(Dist.CLIENT) public ItemStack makeIcon() { return new ItemStack(ModBlocks.MORTAR.get()); }};

    public ModItemGroup(String label, boolean isSearch) {
        super(label);
        this.setBackgroundSuffix(isSearch ? "item_search.png" : "items.png");
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack makeIcon() { return null; }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}
