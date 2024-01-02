package net.iscactus.archaicraft.recipe;

import net.iscactus.archaicraft.Archaicraft;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALISERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Archaicraft.MODID);

    public static final RegistryObject<RecipeSerializer<MortarGrindingRecipe>> MORTAR_SERIALISER =
            SERIALISERS.register("mortar_grinding", () -> MortarGrindingRecipe.Serialiser.INSTANCE);
    public static final RegistryObject<RecipeSerializer<AlchemyMixingRecipe>> ALCHEMY_SERIALISER =
            SERIALISERS.register("alchemy_mixing", () -> AlchemyMixingRecipe.Serialiser.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALISERS.register(eventBus);
    }
}
