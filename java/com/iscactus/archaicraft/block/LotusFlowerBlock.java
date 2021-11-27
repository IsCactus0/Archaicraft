package com.iscactus.archaicraft.block;

import com.iscactus.archaicraft.setup.ModBlocks;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.PlantType;

public class LotusFlowerBlock extends LilyPadBlock {
    public LotusFlowerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        if (this == ModBlocks.LOTUS_FLOWER.get()) return PlantType.WATER;
        return PlantType.PLAINS;
    }
}
