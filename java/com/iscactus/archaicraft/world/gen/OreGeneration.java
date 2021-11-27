package com.iscactus.archaicraft.world.gen;

import com.iscactus.archaicraft.setup.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration {
    public static void generateOres(final BiomeLoadingEvent event) {
        // Overworld Ores
        if (!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))) {
            generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.CINNABAR_ORE.get().defaultBlockState(), 5, 15, 30, 10);
            generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.SULPHUR_ORE.get().defaultBlockState(), 5, 15, 30, 10);
            generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.MOONSTONE_ORE.get().defaultBlockState(), 5, 15, 30, 10);
        }
        // Nether Ores
        if (event.getCategory().equals(Biome.Category.NETHER)) {
            generateOre(event.getGeneration(), new BlockMatchRuleTest(Blocks.BASALT), ModBlocks.ARCTICITE_ORE.get().defaultBlockState(), 5, 15, 30, 10);
        }

        // End Ores
        if (event.getCategory().equals(Biome.Category.THEEND)) {
            generateOre(event.getGeneration(), new BlockMatchRuleTest(Blocks.END_STONE), ModBlocks.CINNABAR_ORE.get().defaultBlockState(), 5, 15, 30, 10);
        }
    }

    private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state,
                                    int veinSize, int minHeight, int maxHeight, int amount) {
        settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(new OreFeatureConfig(fillerType, state, veinSize))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                        .squared().count(amount));
    }
}
