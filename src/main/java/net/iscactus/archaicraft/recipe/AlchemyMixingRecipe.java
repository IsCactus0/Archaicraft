package net.iscactus.archaicraft.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.iscactus.archaicraft.Archaicraft;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class AlchemyMixingRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;
    private final ResourceLocation id;

    public AlchemyMixingRecipe(NonNullList<Ingredient> ingredients, ItemStack result, ResourceLocation id) {
        this.ingredients = ingredients;
        this.result = result;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
        NonNullList<ItemStack> recipe = NonNullList.withSize(3, ItemStack.EMPTY);
        for (int i = 0; i < 3; i++) {
            items.set(i, container.getItem(i));
            recipe.set(i, ingredients.get(i).getItems()[i]);
        }

        return items.containsAll(ingredients);
    }

    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
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

    public static class Type implements RecipeType<AlchemyMixingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "alchemy_mixing";
    }

    public static class Serialiser implements RecipeSerializer<AlchemyMixingRecipe> {
        public static final Serialiser INSTANCE = new Serialiser();
        public static final ResourceLocation ID = new ResourceLocation(Archaicraft.MODID, "alchemy_mixing");

        @Override
        public AlchemyMixingRecipe fromJson(ResourceLocation recipeId, JsonObject serializedRecipe) {
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(serializedRecipe, "result"));

            JsonArray array = GsonHelper.getAsJsonArray(serializedRecipe, "ingredients");
            NonNullList<Ingredient> ingredients = NonNullList.withSize(1, Ingredient.EMPTY);
            for (int i = 0; i < ingredients.size(); i++) {
                ingredients.set(i, Ingredient.fromJson(array.get(i)));
            }

            return new AlchemyMixingRecipe(ingredients, result, recipeId);
        }

        @Override
        public @Nullable AlchemyMixingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> ingredients = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);
            for (int i = 0; i < ingredients.size(); i++) {
                ingredients.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack result = buffer.readItem();

            return new AlchemyMixingRecipe(ingredients, result, recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, AlchemyMixingRecipe recipe) {
            buffer.writeInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItemStack(recipe.getResultItem(null), false);
        }
    }
}