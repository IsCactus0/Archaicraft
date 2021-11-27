package com.iscactus.archaicraft.block;

import com.iscactus.archaicraft.setup.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;

import java.util.Iterator;

public class CattailBlock extends DoublePlantBlock {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public CattailBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public boolean canBeReplaced(BlockState p_196253_1_, BlockItemUseContext p_196253_2_) {
        return false;
    }

    public OffsetType getOffsetType() {
        return OffsetType.XYZ;
    }

    public boolean canSurvive(BlockState state, IWorldReader reader, BlockPos pos) {
        BlockState soil = reader.getBlockState(pos.below());
        if (soil.canSustainPlant(reader, pos.below(), Direction.UP, this)) {
            return true;
        } else {
            BlockState blockstate = reader.getBlockState(pos.below());
            if (blockstate.getBlock() == this) {
                return true;
            } else if (blockstate.is(Blocks.GRASS_BLOCK)
                    || blockstate.is(Blocks.DIRT)
                    || blockstate.is(Blocks.COARSE_DIRT)
                    || blockstate.is(Blocks.PODZOL)
                    || blockstate.is(ModBlocks.MUD.get())
                    || blockstate.is(ModBlocks.CLOVER_BLOCK.get())
                    || blockstate.is(ModBlocks.LITTERFALL.get())
            ) {
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

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState state1, boolean b) {
        super.onPlace(state, world, pos.below(), state1, b);
    }

    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return PlantType.BEACH;
    }

    public BlockState getPlant(IBlockReader world, BlockPos pos) {
        return this.defaultBlockState();
    }
}