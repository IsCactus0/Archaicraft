package com.iscactus.archaicraft.setup;

import com.iscactus.archaicraft.Archaicraft;
import com.iscactus.archaicraft.crafting.recipe.GrindingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;

public class ModRecipes {
    public static final class Types {
        public static final IRecipeType<GrindingRecipe> GRINDING = IRecipeType.register(Archaicraft.MOD_ID + ":grinding");

        private Types() {}
    }

    public static final class Serializers {
        public static final RegistryObject<IRecipeSerializer<?>> GRINDING = Registration.RECIPE_SERIALIZERS.register("grinding", GrindingRecipe.Serializer::new);

        private Serializers() {}
    }

    private ModRecipes() {}

    static void register() {}
}