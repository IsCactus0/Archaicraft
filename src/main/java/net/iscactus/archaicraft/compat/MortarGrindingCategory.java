package net.iscactus.archaicraft.compat;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.iscactus.archaicraft.Archaicraft;
import net.iscactus.archaicraft.block.ModBlocks;
import net.iscactus.archaicraft.recipe.MortarGrindingRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static mezz.jei.api.recipe.RecipeIngredientRole.INPUT;
import static mezz.jei.api.recipe.RecipeIngredientRole.OUTPUT;

public class MortarGrindingCategory implements IRecipeCategory<MortarGrindingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Archaicraft.MODID, "mortar_grinding");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Archaicraft.MODID, "textures/gui/mortar_jei.png");

    public static final RecipeType<MortarGrindingRecipe> MORTAR_GRINDING_TYPE =
            new RecipeType<>(UID, MortarGrindingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    public MortarGrindingCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 59);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.MORTAR.get()));
        this.cachedArrows = CacheBuilder.newBuilder()
                .maximumSize(20)
                .build(new CacheLoader<>() {
                    @Override
                    public IDrawableAnimated load(Integer grindTime) {
                        return helper.drawableBuilder(TEXTURE, 176, 0, 16, 20)
                                .buildAnimated(grindTime, IDrawableAnimated.StartDirection.TOP, false);
                    }
                });
    }

    @Override
    public RecipeType<MortarGrindingRecipe> getRecipeType() {
        return MORTAR_GRINDING_TYPE;
    }

    protected IDrawableAnimated getArrow() {
        return this.cachedArrows.getUnchecked(80);
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void draw(MortarGrindingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IDrawableAnimated arrow = getArrow();
        arrow.draw(guiGraphics, 79, 19);
        drawGrindTime(recipe, guiGraphics, 100, 25);
    }

    protected void drawGrindTime(MortarGrindingRecipe recipe, GuiGraphics graphics, int x, int y) {
        Component timeString = Component.translatable("gui." + Archaicraft.MODID + ".category.grinding.time", 4);
        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;
        graphics.drawString(fontRenderer, timeString, x, y, 0xFF808080, false);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("gui." + Archaicraft.MODID + ".category.grinding.title");
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MortarGrindingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(INPUT, 79, 1)
                .addIngredients(recipe.getIngredient());
        builder.addSlot(OUTPUT, 79, 42)
                .addItemStack(recipe.getResultItem(null));
    }
}
