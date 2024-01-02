package net.iscactus.archaicraft.block.custom;

import net.iscactus.archaicraft.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LightEngine;

import java.util.List;
import java.util.Optional;

public class DryGrassBlock extends SnowyDirtBlock implements BonemealableBlock {
    public DryGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean p_256630_) {
        return level.getBlockState(pos.above()).isAir();
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos above = pos.above();
        BlockState grassState = ModBlocks.DRY_GRASS.get().defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> grassFeature = level.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(VegetationPlacements.GRASS_BONEMEAL);

        grassPlacement:
        for(int i = 0; i < 128; ++i) {
            BlockPos offset = above;

            for(int j = 0; j < i / 16; ++j) {
                offset = offset.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!level.getBlockState(offset.below()).is(this) || level.getBlockState(offset).isCollisionShapeFullBlock(level, offset)) {
                    continue grassPlacement;
                }
            }

            BlockState offsetState = level.getBlockState(offset);
            if (offsetState.is(grassState.getBlock()) && random.nextInt(10) == 0) {
                ((BonemealableBlock)grassState.getBlock()).performBonemeal(level, random, offset, offsetState);
            }

            if (offsetState.isAir()) {
                Holder grassHolder;
                if (random.nextInt(8) == 0) {
                    List<ConfiguredFeature<?, ?>> flowerFeatures = level.getBiome(offset).value().getGenerationSettings().getFlowerFeatures();
                    if (flowerFeatures.isEmpty()) {
                        continue;
                    }

                    grassHolder = ((RandomPatchConfiguration) flowerFeatures.get(0).config()).feature();
                } else {
                    if (grassFeature.isEmpty()) {
                        continue;
                    }

                    grassHolder = grassFeature.get();
                }

                ((PlacedFeature)grassHolder.value()).place(level, level.getChunkSource().getGenerator(), random, offset);
            }
        }

    }

    private static boolean canBeGrass(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        BlockState aboveState = level.getBlockState(above);
        if (aboveState.is(Blocks.SNOW) && aboveState.getValue(SnowLayerBlock.LAYERS) == 1) {
            return true;
        } else if (aboveState.getFluidState().getAmount() == 8) {
            return false;
        } else {
            int i = LightEngine.getLightBlockInto(level, state, pos, aboveState, above, Direction.UP, aboveState.getLightBlock(level, above));
            return i < level.getMaxLightLevel();
        }
    }

    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER);
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canBeGrass(state, level, pos)) {
            if (!level.isAreaLoaded(pos, 1)) {
                return;
            }

            level.setBlockAndUpdate(pos, ModBlocks.SILT.get().defaultBlockState());
        } else {
            if (!level.isAreaLoaded(pos, 3)) {
                return;
            }

            if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
                BlockState blockstate = this.defaultBlockState();

                for(int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (level.getBlockState(blockpos).is(ModBlocks.SILT.get()) && canPropagate(blockstate, level, blockpos)) {
                        level.setBlockAndUpdate(blockpos, blockstate.setValue(SNOWY, level.getBlockState(blockpos.above()).is(Blocks.SNOW)));
                    }
                }
            }
        }

    }
}