package net.iscactus.archaicraft.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.iscactus.archaicraft.Archaicraft;
import net.iscactus.archaicraft.entity.custom.Golem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GolemRenderer extends MobRenderer<Golem, GolemModel<Golem>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Archaicraft.MODID,"textures/entity/golem.png");

    public GolemRenderer(EntityRendererProvider.Context context) {
        super(context, new GolemModel<>(context.bakeLayer(ModModelLayers.GOLEM)), 0.3F);
        this.addLayer(new GolemHeldItemLayer(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(Golem entity) {
        return TEXTURE;
    }

    @Override
    public void render(Golem entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.scale(2f, 2f, 2f);

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}