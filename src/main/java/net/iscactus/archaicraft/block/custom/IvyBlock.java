package net.iscactus.archaicraft.block.custom;

import net.iscactus.archaicraft.block.ModBlocks;
import net.iscactus.archaicraft.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.MultifaceSpreader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class IvyBlock extends MultifaceBlock implements BonemealableBlock {
    public static final BooleanProperty LEAFED = BooleanProperty.create("leafed");
    public static final BooleanProperty FRUIT = BooleanProperty.create("fruit");
    private final MultifaceSpreader spreader = new MultifaceSpreader(this);

    public IvyBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.defaultBlockState()
                        .setValue(LEAFED, Boolean.FALSE)
                        .setValue(FRUIT, Boolean.FALSE));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemstack = player.getItemInHand(hand);
        boolean leaves = state.getValue(LEAFED);
        boolean fruit = state.getValue(FRUIT);
        if (fruit) {
            popResource(level, pos, new ItemStack(ModItems.AMBROSIA.get(), 1));
            level.playSound(null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            BlockState blockstate = state.setValue(FRUIT, Boolean.FALSE);
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockstate));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        if (leaves) {
            Item item = itemstack.getItem();
            if (itemstack.canPerformAction(net.minecraftforge.common.ToolActions.SHEARS_HARVEST)) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.SMALL_DRIPLEAF_BREAK, SoundSource.NEUTRAL, 1.0F, 1.0F);
                level.gameEvent(player, GameEvent.SHEAR, pos);
                level.setBlock(pos, state.setValue(LEAFED, Boolean.FALSE), 3);
                popResource(level, pos, new ItemStack(ModBlocks.IVY.get(), 1));

                itemstack.hurtAndBreak(1, player, (playerEntity) -> {
                    playerEntity.broadcastBreakEvent(hand);
                });

                if (!level.isClientSide()) {
                    player.awardStat(Stats.ITEM_USED.get(item));
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LEAFED);
        builder.add(FRUIT);
    }

    @Override
    public Object getRenderPropertiesInternal() {
        return super.getRenderPropertiesInternal();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.random.nextInt(12) == 0 && level.isAreaLoaded(pos, 4)) {
            ChunkPos chunkPos = level.getChunk(pos).getPos();
            BlockGetter getter = level.getChunkForCollisions(chunkPos.x, chunkPos.z);

            if (canGrowNatural(level, getter, pos, state))  {
                if (level.random.nextInt(12) == 0 && canGrowFruit(level, pos, state)) {
                    growFruit(level, pos, state);
                } else {
                    growIvy(level, random, pos, state);
                }
            }
        }
    }

    private boolean canGrowFruit(ServerLevel level, BlockPos pos, BlockState state) {
        return level.getRawBrightness(pos, 0) == 13 && !state.getValue(FRUIT);
    }

    private boolean canGrowNatural(LevelReader level, BlockGetter getter, BlockPos pos, BlockState state) {
        if (level.getRawBrightness(pos, 0) <= 9 || level.canSeeSky(pos))
            return false;
        if (!state.getValue(LEAFED))
            return true;

        return canSpread(getter, pos, state);
    }

    private boolean canSpread(BlockGetter getter, BlockPos pos, BlockState state) {
        return Direction.stream().anyMatch((direction) -> {
            return this.spreader.canSpreadInAnyDirection(state, getter, pos, direction.getOpposite());
        });
    }

    private void growIvy(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        if (state.getValue(LEAFED)) {
            this.spreader.spreadFromRandomFaceTowardRandomDirection(state, level, pos, random);
        } else {
            level.setBlock(pos, state.setValue(LEAFED, Boolean.TRUE), 3);
        }
    }

    private void growFruit(ServerLevel level, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(FRUIT, Boolean.TRUE), 3);
    }

    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return !context.getItemInHand().is(ModBlocks.IVY.get().asItem()) || super.canBeReplaced(state, context);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        ChunkPos chunkPos = level.getChunk(pos).getPos();
        Boolean growIvy = !state.getValue(LEAFED) || canSpread(level.getChunkForCollisions(chunkPos.x, chunkPos.z), pos, state);
        Boolean growFruit = !growIvy && !state.getValue(FRUIT);

        return growIvy || growFruit;
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        ChunkPos chunkPos = level.getChunk(pos).getPos();
        boolean growIvy = !state.getValue(LEAFED) || canSpread(level.getChunkForCollisions(chunkPos.x, chunkPos.z), pos, state);
        boolean growFruit = !growIvy && !state.getValue(FRUIT);

        if (growIvy) {
            growIvy(level, random, pos, state);
        } else if (growFruit) {
            growFruit(level, pos, state);
        }
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return Boolean.TRUE;
    }

    public @NotNull MultifaceSpreader getSpreader() {
        return this.spreader;
    }
}