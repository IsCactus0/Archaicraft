package com.iscactus.archaicraft.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class ModLogBlock extends RotatedPillarBlock {
    private final BlockState STRIPPED;
    public ModLogBlock(BlockState state, Properties properties) {
        super(properties);
        this.STRIPPED = state;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        return toolType == ToolType.AXE ? STRIPPED.setValue(AXIS , world.getBlockState(pos).getValue(AXIS))  : null;
    }
}