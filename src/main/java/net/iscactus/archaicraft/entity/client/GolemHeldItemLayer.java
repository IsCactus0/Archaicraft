package net.iscactus.archaicraft.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.iscactus.archaicraft.entity.custom.Golem;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GolemHeldItemLayer extends RenderLayer<Golem, GolemModel<Golem>> {
    private final ItemInHandRenderer itemInHandRenderer;

    public GolemHeldItemLayer(RenderLayerParent<Golem, GolemModel<Golem>> renderer, ItemInHandRenderer itemInHandRenderer) {
        super(renderer);
        this.itemInHandRenderer = itemInHandRenderer;
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Golem entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        poseStack.pushPose();
        poseStack.translate((this.getParentModel()).head.x / 16.0F, (this.getParentModel()).head.y / 16.0F, (this.getParentModel()).head.z / 16.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(netHeadYaw));
        poseStack.mulPose(Axis.XP.rotationDegrees(headPitch));
        poseStack.translate(0.06F, 0.27F, -0.5F);
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));

        ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.MAINHAND);
        this.itemInHandRenderer.renderItem(entity, itemstack, ItemDisplayContext.GROUND, false, poseStack, buffer, packedLight);
        poseStack.popPose();
    }
}
