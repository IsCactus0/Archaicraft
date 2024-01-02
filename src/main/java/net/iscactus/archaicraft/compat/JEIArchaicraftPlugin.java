package net.iscactus.archaicraft.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.iscactus.archaicraft.Archaicraft;
import net.iscactus.archaicraft.recipe.AlchemyMixingRecipe;
import net.iscactus.archaicraft.recipe.MortarGrindingRecipe;
import net.iscactus.archaicraft.screen.AlchemyTableScreen;
import net.iscactus.archaicraft.screen.MortarScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIArchaicraftPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Archaicraft.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new MortarGrindingCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new AlchemyMixingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<MortarGrindingRecipe> grindingRecipes = recipeManager.getAllRecipesFor(MortarGrindingRecipe.Type.INSTANCE);
        registration.addRecipes(MortarGrindingCategory.MORTAR_GRINDING_TYPE, grindingRecipes);
        List<AlchemyMixingRecipe> mixingRecipes = recipeManager.getAllRecipesFor(AlchemyMixingRecipe.Type.INSTANCE);
        registration.addRecipes(AlchemyMixingCategory.ALCHEMY_MIXING_TYPE, mixingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(MortarScreen.class, 79, 35, 16, 20,
                MortarGrindingCategory.MORTAR_GRINDING_TYPE);
        registration.addRecipeClickArea(AlchemyTableScreen.class, 107, 35, 16, 20,
                AlchemyMixingCategory.ALCHEMY_MIXING_TYPE);
    }
}
