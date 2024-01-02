package net.iscactus.archaicraft.block;

import net.iscactus.archaicraft.Archaicraft;
import net.iscactus.archaicraft.block.custom.*;
import net.iscactus.archaicraft.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Archaicraft.MODID);

    public static final RegistryObject<Block> IVY = registerBlock("ivy", () -> new IvyBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).replaceable().noCollission().randomTicks().strength(0.2F).sound(SoundType.GLOW_LICHEN).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> ALOE_PLANT = registerBlock("aloe_plant", () -> new AloePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().sound(SoundType.SWEET_BERRY_BUSH).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> POTTED_ALOE_PLANT = registerBlock("potted_aloe_plant", () -> flowerPot(ALOE_PLANT.get()));
    public static final RegistryObject<Block> COTTON_CROP = registerBlock("cotton_crop", () -> new CottonBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> DRY_GRASS = registerBlock("dry_grass", () -> new TallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).replaceable().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> DRY_GRASS_BLOCK = registerBlock("dry_grass_block", () -> new DryGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).randomTicks().strength(0.5F).sound(SoundType.GRASS).ignitedByLava()));
    public static final RegistryObject<Block> SILT = registerBlock("silt", () -> new SandBlock(5986116, BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(0.4F).sound(SoundType.SAND)));
    public static final RegistryObject<Block> SILTSTONE = registerBlock("siltstone", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));
    public static final RegistryObject<Block> SILTSTONE_STAIRS = registerBlock("siltstone_stairs", () -> new StairBlock(SILTSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(SILTSTONE.get())));
    public static final RegistryObject<Block> SILTSTONE_SLAB = registerBlock("siltstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> SILTSTONE_WALL = registerBlock("siltstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SILTSTONE.get()).forceSolidOn()));
    public static final RegistryObject<Block> SILTSTONE_BRICKS = registerBlock("siltstone_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));
    public static final RegistryObject<Block> SILTSTONE_BRICK_STAIRS = registerBlock("siltstone_brick_stairs", () -> new StairBlock(SILTSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(SILTSTONE_BRICKS.get())));
    public static final RegistryObject<Block> SILTSTONE_BRICK_SLAB = registerBlock("siltstone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> SILTSTONE_BRICK_WALL = registerBlock("siltstone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(SILTSTONE_BRICKS.get()).forceSolidOn()));
    public static final RegistryObject<Block> SILTSTONE_MOSAIC = registerBlock("siltstone_mosaic", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(0.8F)));
    public static final RegistryObject<Block> SILTSTONE_MOSAIC_STAIRS = registerBlock("siltstone_mosaic_stairs", () -> new StairBlock(SILTSTONE_MOSAIC.get().defaultBlockState(), BlockBehaviour.Properties.copy(SILTSTONE_MOSAIC.get())));
    public static final RegistryObject<Block> SILTSTONE_MOSAIC_SLAB = registerBlock("siltstone_mosaic_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> BITTERSWEET_PLANKS = registerBlock("bittersweet_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<Block> BITTERSWEET = registerBlock("bittersweet", () -> new MushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).noCollission().randomTicks().instabreak().sound(SoundType.FUNGUS).hasPostProcess(ModBlocks::always).pushReaction(PushReaction.DESTROY), TreeFeatures.HUGE_RED_MUSHROOM));
    public static final RegistryObject<Block> BITTERSWEET_STEM = registerBlock("bittersweet_stem", () -> log(MapColor.DIRT, MapColor.PODZOL));
    public static final RegistryObject<Block> BITTERSWEET_WOOD = registerBlock("bittersweet_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<Block> BITTERSWEET_PILEUS = registerBlock("bittersweet_pileus", () -> new PileusBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.WART_BLOCK).ignitedByLava()));
    public static final RegistryObject<Block> BITTERSWEET_GILLS = registerBlock("bittersweet_gills", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(0.6F).sound(SoundType.WART_BLOCK).ignitedByLava()));
    public static final RegistryObject<Block> BITTERSWEET_SIGN = BLOCKS.register("bittersweet_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), ModWoodType.BITTERSWEET));
    public static final RegistryObject<Block> BITTERSWEET_WALL_SIGN = BLOCKS.register("bittersweet_wall_sign", () -> new WallSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).dropsLike(BITTERSWEET_SIGN.get()).ignitedByLava(), ModWoodType.BITTERSWEET));
    public static final RegistryObject<Block> BITTERSWEET_HANGING_SIGN = BLOCKS.register("bittersweet_hanging_sign", () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(BITTERSWEET_STEM.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), ModWoodType.BITTERSWEET));
    public static final RegistryObject<Block> BITTERSWEET_WALL_HANGING_SIGN = BLOCKS.register("bittersweet_wall_hanging_sign", () -> new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(BITTERSWEET_STEM.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava().dropsLike(BITTERSWEET_HANGING_SIGN.get()), ModWoodType.BITTERSWEET));
    public static final RegistryObject<Block> BITTERSWEET_PRESSURE_PLATE = registerBlock("bittersweet_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(BITTERSWEET_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), ModBlockSetType.BITTERSWEET));
    public static final RegistryObject<Block> BITTERSWEET_TRAPDOOR = registerBlock("bittersweet_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never).ignitedByLava(), ModBlockSetType.BITTERSWEET));
    public static final RegistryObject<Block> BITTERSWEET_STAIRS = registerBlock("bittersweet_stairs", () -> new StairBlock(BITTERSWEET_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(BITTERSWEET_PLANKS.get())));
    public static final RegistryObject<Block> POTTED_BITTERSWEET = registerBlock("potted_bittersweet", () -> flowerPot(BITTERSWEET.get()));
    public static final RegistryObject<Block> BITTERSWEET_BUTTON = registerBlock("bittersweet_button", () -> woodenButton(ModBlockSetType.BITTERSWEET));
    public static final RegistryObject<Block> BITTERSWEET_SLAB = registerBlock("bittersweet_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<Block> BITTERSWEET_FENCE_GATE = registerBlock("bittersweet_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(BITTERSWEET_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava(), ModWoodType.BITTERSWEET));
    public static final RegistryObject<Block> BITTERSWEET_FENCE = registerBlock("bittersweet_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(BITTERSWEET_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava().sound(SoundType.WOOD)));
    public static final RegistryObject<Block> BITTERSWEET_DOOR = registerBlock("bittersweet_door", () -> new DoorBlock(BlockBehaviour.Properties.of().mapColor(BITTERSWEET_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY), ModBlockSetType.BITTERSWEET));
    public static final RegistryObject<Block> GHOSTCAP_PLANKS = registerBlock("ghostcap_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> GHOSTCAP = registerBlock("ghostcap", () -> new MushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).noCollission().randomTicks().instabreak().sound(SoundType.FUNGUS).hasPostProcess(ModBlocks::always).pushReaction(PushReaction.DESTROY), TreeFeatures.HUGE_BROWN_MUSHROOM));
    public static final RegistryObject<Block> GHOSTCAP_STEM = registerBlock("ghostcap_stem", () -> log(MapColor.TERRACOTTA_BROWN, MapColor.TERRACOTTA_BROWN));
    public static final RegistryObject<Block> GHOSTCAP_ROOTS = registerBlock("ghostcap_roots", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> GHOSTCAP_PILEUS = registerBlock("ghostcap_pileus", () -> new PileusBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASS).strength(0.2F).sound(SoundType.FUNGUS).ignitedByLava()));
    public static final RegistryObject<Block> GHOSTCAP_SIGN = BLOCKS.register("ghostcap_sign", () -> new StandingSignBlock(BlockBehaviour.Properties.of().mapColor(GHOSTCAP_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), ModWoodType.GHOSTCAP));
    public static final RegistryObject<Block> GHOSTCAP_WALL_SIGN = BLOCKS.register("ghostcap_wall_sign", () -> new WallSignBlock(BlockBehaviour.Properties.of().mapColor(GHOSTCAP_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).dropsLike(GHOSTCAP_SIGN.get()).ignitedByLava(), ModWoodType.GHOSTCAP));
    public static final RegistryObject<Block> GHOSTCAP_HANGING_SIGN = BLOCKS.register("ghostcap_hanging_sign", () -> new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(GHOSTCAP_STEM.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava(), ModWoodType.GHOSTCAP));
    public static final RegistryObject<Block> GHOSTCAP_WALL_HANGING_SIGN = BLOCKS.register("ghostcap_wall_hanging_sign", () -> new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(GHOSTCAP_STEM.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava().dropsLike(GHOSTCAP_HANGING_SIGN.get()), ModWoodType.GHOSTCAP));
    public static final RegistryObject<Block> GHOSTCAP_PRESSURE_PLATE = registerBlock("ghostcap_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(GHOSTCAP_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY), ModBlockSetType.GHOSTCAP));
    public static final RegistryObject<Block> GHOSTCAP_TRAPDOOR = registerBlock("ghostcap_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(GHOSTCAP_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never).ignitedByLava(), ModBlockSetType.GHOSTCAP));
    public static final RegistryObject<Block> GHOSTCAP_STAIRS = registerBlock("ghostcap_stairs", () -> new StairBlock(GHOSTCAP_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(GHOSTCAP_PLANKS.get())));
    public static final RegistryObject<Block> POTTED_GHOSTCAP = registerBlock("potted_ghostcap", () -> flowerPot(GHOSTCAP.get()));
    public static final RegistryObject<Block> GHOSTCAP_BUTTON = registerBlock("ghostcap_button", () -> woodenButton(ModBlockSetType.GHOSTCAP));
    public static final RegistryObject<Block> GHOSTCAP_SLAB = registerBlock("ghostcap_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(GHOSTCAP_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> GHOSTCAP_FENCE_GATE = registerBlock("ghostcap_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(GHOSTCAP_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava(), ModWoodType.GHOSTCAP));
    public static final RegistryObject<Block> GHOSTCAP_FENCE = registerBlock("ghostcap_fence", () -> new FenceBlock(BlockBehaviour.Properties.of().mapColor(GHOSTCAP_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava().sound(SoundType.NETHER_WOOD)));
    public static final RegistryObject<Block> GHOSTCAP_DOOR = registerBlock("ghostcap_door", () -> new DoorBlock(BlockBehaviour.Properties.of().mapColor(GHOSTCAP_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY), ModBlockSetType.GHOSTCAP));
    public static final RegistryObject<Block> MYTHRIL_ORE = registerBlock("mythril_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F), UniformInt.of(5, 9)));
    public static final RegistryObject<Block> RAW_MYTHRIL_BLOCK = registerBlock("raw_mythril_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 6.0F)));
    public static final RegistryObject<Block> MYTHRIL_BLOCK = registerBlock("mythril_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).instrument(NoteBlockInstrument.FLUTE).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> ALCHEMY_TABLE = registerBlock("alchemy_table", () -> new AlchemyTableBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5F).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<Block> KILN = registerBlock("kiln", () -> new KilnBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.5F).lightLevel(litBlockEmission(13))));
    public static final RegistryObject<Block> MORTAR = registerBlock("mortar", () -> new MortarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F).noOcclusion()));

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> {
            return state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
        };
    }
    private static Boolean never(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entity) {
        return (boolean)false;
    }
    private static Boolean always(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entity) {
        return (boolean)true;
    }
    private static Boolean ocelotOrParrot(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> entity) {
        return (boolean)(entity == EntityType.OCELOT || entity == EntityType.PARROT);
    }
    private static RotatedPillarBlock log(MapColor topMapColor, MapColor sideMapColor) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor((state) -> {
            return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    }
    private static RotatedPillarBlock log(MapColor topMapColor, MapColor sideMapColor, SoundType soundType) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor((state) -> {
            return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(soundType).ignitedByLava());
    }
    private static boolean always(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }
    private static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }
    private static LeavesBlock leaves(SoundType type) {
        return new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(type).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().pushReaction(PushReaction.DESTROY).isRedstoneConductor(ModBlocks::never));
    }
    private static ButtonBlock woodenButton(BlockSetType setType, FeatureFlag... requiredFeatures) {
        BlockBehaviour.Properties blockbehaviour$properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (requiredFeatures.length > 0) {
            blockbehaviour$properties = blockbehaviour$properties.requiredFeatures(requiredFeatures);
        }

        return new ButtonBlock(blockbehaviour$properties, setType, 30, true);
    }
    private static FlowerPotBlock flowerPot(Block block, FeatureFlag... requiredFeatures) {
        BlockBehaviour.Properties blockbehaviour$properties = BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);
        if (requiredFeatures.length > 0) {
            blockbehaviour$properties = blockbehaviour$properties.requiredFeatures(requiredFeatures);
        }

        return new FlowerPotBlock(block, blockbehaviour$properties);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}