package com.iscactus.archaicraft.block;

import com.iscactus.archaicraft.setup.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class DriedTallGrassBlock extends TallGrassBlock {
    public DriedTallGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader reader, BlockPos pos) {
        return  state.is(Blocks.DIRT) ||
                state.is(Blocks.COARSE_DIRT) ||
                state.is(Blocks.SAND) ||
                state.is(ModBlocks.DRIED_GRASS_BLOCK.get()) ||
                state.is(ModBlocks.ASH.get());
    }
}