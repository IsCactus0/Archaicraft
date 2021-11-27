package com.iscactus.archaicraft.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Iterator;
import java.util.Random;

public class AzurecapBlock extends BushBlock implements IGrowable {
    protected static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 10.0D, 12.0D);

    public AzurecapBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(25) == 0) {
            int i = 5;
            Iterator var7 = BlockPos.betweenClosed(pos.offset(-4, -1, -4), pos.offset(4, 1, 4)).iterator();

            while(var7.hasNext()) {
                BlockPos blockpos = (BlockPos)var7.next();
                if (world.getBlockState(blockpos).is(this)) {
                    --i;
                    if (i <= 0) {
                        return;
                    }
                }
            }

            BlockPos blockpos1 = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);

            for(int k = 0; k < 4; ++k) {
                if (world.isEmptyBlock(blockpos1) && state.canSurvive(world, blockpos1)) {
                    pos = blockpos1;
                }

                blockpos1 = pos.offset(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
            }

            if (world.isEmptyBlock(blockpos1) && state.canSurvive(world, blockpos1)) {
                world.setBlock(blockpos1, state, 2);
            }
        }
    }

    protected boolean mayPlaceOn(BlockState state, IBlockReader reader, BlockPos pos) {
        return state.isSolidRender(reader, pos);
    }

    public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) {
        BlockState soil = reader.getBlockState(pos.below());
        if (soil.canSustainPlant(reader, pos.below(), Direction.UP, this)) {
            return true;
        } else {
            BlockState blockstate = reader.getBlockState(pos.below());
            if (blockstate.getBlock() == this) {
                return true;
            } else if (blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
                BlockPos blockpos = pos.below();
                Iterator var7 = Direction.Plane.HORIZONTAL.iterator();

                BlockState blockstate1;
                FluidState fluidstate;
                do {
                    if (!var7.hasNext()) {
                        return false;
                    }

                    Direction direction = (Direction)var7.next();
                    blockstate1 = reader.getBlockState(blockpos.relative(direction));
                    fluidstate = reader.getFluidState(blockpos.relative(direction));
                } while(!fluidstate.is(FluidTags.WATER) && !blockstate1.is(Blocks.FROSTED_ICE));

                return true;
            } else {
                return false;
            }
        }
    }

    public boolean isValidBonemealTarget(IBlockReader p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
        return false;
    }

    @Override
    public boolean isBonemealSuccess(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
        return false;
    }

    @Override
    public void performBonemeal(ServerWorld p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_) { }
}
