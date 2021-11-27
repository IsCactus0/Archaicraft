package com.iscactus.archaicraft.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class ArcticiteOreBlock extends RotatedPillarBlock {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public ArcticiteOreBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, Boolean.FALSE));
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y));
    }

    public void attack(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        interact(state, world, pos);
        super.attack(state, world, pos, player);
    }

    public void stepOn(World world, BlockPos pos, Entity entity) {
        interact(world.getBlockState(pos), world, pos);
        super.stepOn(world, pos, entity);
    }

    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (world.isClientSide) {
            spawnParticles(world, pos);
        } else {
            interact(state, world, pos);
        }

        ItemStack itemstack = player.getItemInHand(hand);
        return itemstack.getItem() instanceof BlockItem && (new BlockItemUseContext(player, hand, itemstack, rayTraceResult)).canPlace() ? ActionResultType.PASS : ActionResultType.SUCCESS;
    }

    private static void interact(BlockState state, World world, BlockPos pos) {
        spawnParticles(world, pos);
        if (!state.getValue(LIT)) {
            world.setBlock(pos, state.setValue(LIT, Boolean.TRUE), 3);
        }
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(LIT);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.getValue(LIT)) {
            world.setBlock(pos, state.setValue(LIT, Boolean.FALSE), 3);
        }
    }

    public void spawnAfterBreak(BlockState state, ServerWorld world, BlockPos pos, ItemStack itemStack) {
        super.spawnAfterBreak(state, world, pos, itemStack);
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? 2 + RANDOM.nextInt(7) : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.getValue(LIT)) {
            spawnParticles(world, pos);
        }

    }

    private static void spawnParticles(World world, BlockPos pos) {
        double d0 = 0.5625D;
        Random random = world.random;

        if (random.nextInt(1) == 0) {
            for(Direction direction : Direction.values()) {
                BlockPos blockpos = pos.relative(direction);
                if (!world.getBlockState(blockpos).isSolidRender(world, blockpos)) {
                    Direction.Axis direction$axis = direction.getAxis();
                    double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)random.nextFloat();
                    double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)random.nextFloat();
                    double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)random.nextFloat();
                    world.addParticle(ParticleTypes.ENCHANT, (double)pos.getX() + d1, (double)pos.getY() + d2, (double)pos.getZ() + d3, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(LIT, AXIS);
    }

    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        return this.defaultBlockState().setValue(AXIS, p_196258_1_.getClickedFace().getAxis());
    }
}
