package net.iscactus.archaicraft.common;

import net.iscactus.archaicraft.Archaicraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> BITTERSWEET_STEMS = tag("bittersweet_stems");
        public static final TagKey<Block> GHOSTCAP_STEMS = tag("ghostcap_stems");
        public static final TagKey<Block> NEEDS_MITHRIL_TOOL = tag("needs_mythril_tool");
        public static final TagKey<Block> PILEUS_BLOCKS = tag("pileus_blocks");
        public static final TagKey<Block> CROPS = forgeTag("pileus_blocks");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Archaicraft.MODID, name));
        }
        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> BITTERSWEET_STEMS = tag("bittersweet_stems");
        public static final TagKey<Item> GHOSTCAP_STEMS = tag("ghostcap_stems");
        public static final TagKey<Item> INSULATORS = tag("insulators");
        public static final TagKey<Item> PILEUS_BLOCKS = tag("pileus_blocks");
        public static final TagKey<Item> SEEDS = forgeTag("seeds");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Archaicraft.MODID, name));
        }
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}