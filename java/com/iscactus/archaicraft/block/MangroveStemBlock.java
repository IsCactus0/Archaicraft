package com.iscactus.archaicraft.block;

import com.iscactus.archaicraft.setup.ModBlocks;
import net.minecraft.block.AbstractBodyPlantBlock;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;

public class MangroveStemBlock extends AbstractBodyPlantBlock {
    public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);

    public MangroveStemBlock(Properties p_i241192_1_) {
        super(p_i241192_1_, Direction.UP, SHAPE, false);
    }

    protected AbstractTopPlantBlock getHeadBlock() {
        return (AbstractTopPlantBlock) ModBlocks.MANGROVE_STEM.get();
    }
}