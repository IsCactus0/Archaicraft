package com.iscactus.archaicraft.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class CharredLogBlock extends RotatedPillarBlock {
    private final BlockState STRIPPED;
    public CharredLogBlock(BlockState state, Properties properties) {
        super(properties);
        this.STRIPPED = state;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        float rx = world.random.nextFloat() + 0.5F;
        float ry = world.random.nextFloat();
        float rz = world.random.nextFloat() + 0.5F;

        ItemStack itemstack = new ItemStack(Items.CHARCOAL, (int)world.random.nextFloat() * 4);

        ItemEntity entityItem = new ItemEntity(world,
                pos.getX() + rx, pos.getY() + ry, pos.getZ() + rz,
                Items.CHARCOAL.getDefaultInstance());

        world.addFreshEntity(entityItem);
        return toolType == ToolType.AXE ? STRIPPED.setValue(AXIS , world.getBlockState(pos).getValue(AXIS))  : null;
    }
}
