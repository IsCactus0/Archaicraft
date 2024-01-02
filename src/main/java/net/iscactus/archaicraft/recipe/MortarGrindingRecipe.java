package net.iscactus.archaicraft.recipe;

import com.google.gson.JsonObject;
import net.iscactus.archaicraft.Archaicraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MortarGrindingRecipe implements Recipe<SimpleContainer> {
    private final Ingredient ingredient;
    private final ItemStack result;
    private final ResourceLocation id;

    public MortarGrindingRecipe(Ingredient ingredient, ItemStack result, ResourceLocation id) {
        this.ingredient = ingredient;
        this.result = result;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        return ingredient.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return result.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serialiser.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<MortarGrindingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "mortar_grinding";
    }

    public static class Serialiser implements RecipeSerializer<MortarGrindingRecipe> {
        public static final Serialiser INSTANCE = new Serialiser();
        public static final ResourceLocation ID = new ResourceLocation(Archaicraft.MODID, "mortar_grinding");

        @Override
        public MortarGrindingRecipe fromJson(ResourceLocation recipeId, JsonObject serializedRecipe) {
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(serializedRecipe, "result"));
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(serializedRecipe, "ingredient"));

            return new MortarGrindingRecipe(ingredient, result, recipeId);
        }

        @Override
        public @Nullable MortarGrindingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();

            return new MortarGrindingRecipe(ingredient, result, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MortarGrindingRecipe recipe) {
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItemStack(recipe.getResultItem(null), false);
        }
    }
}