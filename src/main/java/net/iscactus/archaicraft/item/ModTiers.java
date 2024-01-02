package net.iscactus.archaicraft.item;

import net.iscactus.archaicraft.Archaicraft;
import net.iscactus.archaicraft.common.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModTiers {
    public static final Tier MITHRIL = TierSortingRegistry.registerTier(
            new ForgeTier(5, 3742, 11.0F, 5.0F, 21,
                    ModTags.Blocks.NEEDS_MITHRIL_TOOL, () -> Ingredient.of(ModItems.MYTHRIL_INGOT.get())),
            new ResourceLocation(Archaicraft.MODID, "mithril"), List.of(Tiers.NETHERITE), List.of());
}
