package com.iscactus.archaicraft.block;

import com.iscactus.archaicraft.setup.ModBlocks;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IWorld;

import java.util.Random;

public class MangroveStemTopBlock extends AbstractTopPlantBlock {
    public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public MangroveStemTopBlock(Properties p_i241191_1_) {
        super(p_i241191_1_, Direction.UP, SHAPE, false, 0.1D);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 23));
    }

    @Override
    public BlockState getStateForPlacement(IWorld world) {
        return this.defaultBlockState().setValue(AGE, (23 + world.getRandom().nextInt(3)));
    }

    protected int getBlocksToGrowWhenBonemealed(Random p_230332_1_) {
        return PlantBlockHelper.getBlocksToGrowWhenBonemealed(p_230332_1_);
    }

    protected Block getBodyBlock() {
        return ModBlocks.MANGROVE_STEM_PLANT.get();
    }

    protected boolean canGrowInto(BlockState p_230334_1_) {
        return PlantBlockHelper.isValidGrowthState(p_230334_1_);
    }
}