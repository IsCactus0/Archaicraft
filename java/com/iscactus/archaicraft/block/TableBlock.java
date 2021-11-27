package com.iscactus.archaicraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FourWayBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class TableBlock extends FourWayBlock {
    private static final VoxelShape TOP = box(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape LEG_NORTH = box(11.0D, 0.0D, 1.0D, 15.0D, 16.0D, 5.0D);
    private static final VoxelShape LEG_EAST =  box(11.0D, 0.0D, 11.0D, 15.0D, 16.0D, 15.0D);
    private static final VoxelShape LEG_SOUTH = box(1.0D, 0.0D, 11.0D, 5.0D, 16.0D, 15.0D);
    private static final VoxelShape LEG_WEST =  box(1.0D, 0.0D, 1.0D, 5.0D, 16.0D, 5.0D);
    private final VoxelShape[] occlusionByIndex;

    public TableBlock(Properties properties) {
        super(2.0F, 2.0F, 16.0F, 16.0F, 24.0F, properties);
        this.registerDefaultState(
                (BlockState)(
                        (BlockState)(
                                (BlockState)(
                                        (BlockState)(
                                                (BlockState)(
                                                        (BlockState)this.stateDefinition.any()
                                                ).setValue(NORTH, false)
                                        ).setValue(EAST, false)
                                ).setValue(SOUTH, false)
                        ).setValue(WEST, false)
                ).setValue(WATERLOGGED, false)
        );
        this.occlusionByIndex = this.makeShapes(0,0,0,0,0);
    }

    @Override
    protected VoxelShape[] makeShapes(float p_196408_1_, float p_196408_2_, float p_196408_3_, float p_196408_4_, float p_196408_5_) {
        VoxelShape voxelshape5 = VoxelShapes.or(LEG_NORTH, LEG_EAST);
        VoxelShape voxelshape6 = VoxelShapes.or(LEG_SOUTH, LEG_WEST);
        VoxelShape[] avoxelshape = new VoxelShape[] {
                VoxelShapes.or(voxelshape6, voxelshape5),
                VoxelShapes.or(LEG_WEST, LEG_NORTH),
                voxelshape5,
                LEG_NORTH,
                VoxelShapes.or(LEG_SOUTH, LEG_EAST),
                VoxelShapes.empty(),
                LEG_EAST,
                VoxelShapes.empty(),
                voxelshape6,
                LEG_WEST,
                VoxelShapes.empty(),
                VoxelShapes.empty(),
                LEG_SOUTH,
                VoxelShapes.empty(),
                VoxelShapes.empty(),
                VoxelShapes.empty()};

        for(int i = 0; i < 16; ++i) {
            avoxelshape[i] = VoxelShapes.or(TOP, avoxelshape[i]);
        }

        return avoxelshape;
    }

    public VoxelShape getOcclusionShape(BlockState state, IBlockReader reader, BlockPos pos) {
        return this.occlusionByIndex[this.getAABBIndex(state)];
    }

    public VoxelShape getVisualShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext selectionContext) {
        return this.getShape(state, reader, pos, selectionContext);
    }

    public boolean isPathfindable(BlockState state, IBlockReader reader, BlockPos pos, PathType type) {
        return true;
    }

    public boolean connectsTo(BlockState state, boolean b, Direction direction) {
        Block block = state.getBlock();
        return block.is(this);
    }
    public BlockState getStateForPlacement(BlockItemUseContext itemUseContext) {
        IBlockReader iblockreader = itemUseContext.getLevel();
        BlockPos blockpos = itemUseContext.getClickedPos();
        FluidState fluidstate = itemUseContext.getLevel().getFluidState(itemUseContext.getClickedPos());
        BlockPos blockpos1 = blockpos.north();
        BlockPos blockpos2 = blockpos.east();
        BlockPos blockpos3 = blockpos.south();
        BlockPos blockpos4 = blockpos.west();
        BlockState blockstate = iblockreader.getBlockState(blockpos1);
        BlockState blockstate1 = iblockreader.getBlockState(blockpos2);
        BlockState blockstate2 = iblockreader.getBlockState(blockpos3);
        BlockState blockstate3 = iblockreader.getBlockState(blockpos4);
        return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)super.getStateForPlacement(itemUseContext).setValue(NORTH, this.connectsTo(blockstate, blockstate.isFaceSturdy(iblockreader, blockpos1, Direction.SOUTH), Direction.SOUTH))).setValue(EAST, this.connectsTo(blockstate1, blockstate1.isFaceSturdy(iblockreader, blockpos2, Direction.WEST), Direction.WEST))).setValue(SOUTH, this.connectsTo(blockstate2, blockstate2.isFaceSturdy(iblockreader, blockpos3, Direction.NORTH), Direction.NORTH))).setValue(WEST, this.connectsTo(blockstate3, blockstate3.isFaceSturdy(iblockreader, blockpos4, Direction.EAST), Direction.EAST))).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if ((Boolean)p_196271_1_.getValue(WATERLOGGED)) {
            p_196271_4_.getLiquidTicks().scheduleTick(p_196271_5_, Fluids.WATER, Fluids.WATER.getTickDelay(p_196271_4_));
        }

        return p_196271_2_.getAxis().getPlane() == Direction.Plane.HORIZONTAL ? (BlockState)p_196271_1_.setValue((Property)PROPERTY_BY_DIRECTION.get(p_196271_2_), this.connectsTo(p_196271_3_, p_196271_3_.isFaceSturdy(p_196271_4_, p_196271_6_, p_196271_2_.getOpposite()), p_196271_2_.getOpposite())) : super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
    }

    public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        BlockPos blockpos = p_196260_3_.below();
        BlockState blockstate = p_196260_2_.getBlockState(blockpos);
        return blockstate.isFaceSturdy(p_196260_2_, blockpos, Direction.UP);
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(NORTH, EAST, WEST, SOUTH, WATERLOGGED);
    }
}
