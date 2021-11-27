package com.iscactus.archaicraft.client.model;

import com.iscactus.archaicraft.entity.passive.MothEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class MothModel<T extends MothEntity> extends EntityModel<T> {

    private final ModelRenderer bone;
    private final ModelRenderer leftWing;
    private final ModelRenderer rightWing;
    private final ModelRenderer frontLeg;
    private final ModelRenderer midLeg;
    private final ModelRenderer backLeg;
    private final ModelRenderer leftAntenna;
    private final ModelRenderer rightAntenna;
    private final ModelRenderer torso;

    public MothModel() {
        texWidth = 64;
        texHeight = 64;

        bone = new ModelRenderer(this);
        bone.setPos(0.0F, 24.0F, 0.0F);


        leftWing = new ModelRenderer(this);
        leftWing.setPos(2.0F, 17.0F, 0.0F);
        leftWing.texOffs(0, 18).addBox(-0.5F, 0.0F, -3.0F, 7.0F, 0.0F, 10.0F, 0.0F, true);

        rightWing = new ModelRenderer(this);
        rightWing.setPos(-2.0F, 17.0F, 0.0F);
        rightWing.texOffs(0, 18).addBox(-6.5F, 0.0F, -3.0F, 7.0F, 0.0F, 10.0F, 0.0F, false);

        frontLeg = new ModelRenderer(this);
        frontLeg.setPos(0.0F, 22.0F, -2.0F);
        frontLeg.texOffs(28, 1).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, false);

        midLeg = new ModelRenderer(this);
        midLeg.setPos(0.0F, 22.0F, 0.0F);
        midLeg.texOffs(27, 3).addBox(-2.5F, -1.0F, 0.0F, 5.0F, 2.0F, 0.0F, 0.0F, false);

        backLeg = new ModelRenderer(this);
        backLeg.setPos(0.0F, 22.0F, 2.0F);
        backLeg.texOffs(27, 5).addBox(-2.5F, -1.0F, 0.0F, 5.0F, 2.0F, 0.0F, 0.0F, false);

        leftAntenna = new ModelRenderer(this);
        leftAntenna.setPos(2.0F, 17.0F, -5.0F);
        leftAntenna.texOffs(2, 2).addBox(-0.5F, -1.0F, -1.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        rightAntenna = new ModelRenderer(this);
        rightAntenna.setPos(-2.0F, 17.0F, -5.0F);
        rightAntenna.texOffs(2, 5).addBox(-2.5F, -1.0F, -1.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        torso = new ModelRenderer(this);
        torso.setPos(0.0F, 19.0F, 0.0F);
        torso.texOffs(0, 0).addBox(-2.5F, -2.0F, -5.0F, 5.0F, 4.0F, 10.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.rightWing.xRot = 0.0F;
        this.leftAntenna.xRot = 0.0F;
        this.rightAntenna.xRot = 0.0F;
        this.bone.xRot = 0.0F;
        this.bone.y = 19.0F;
        boolean flag = p_225597_1_.isOnGround() && p_225597_1_.getDeltaMovement().lengthSqr() < 1.0E-7D;
        if (flag) {
            this.rightWing.yRot = -0.2618F;
            this.rightWing.zRot = 0.0F;
            this.leftWing.xRot = 0.0F;
            this.leftWing.yRot = 0.2618F;
            this.leftWing.zRot = 0.0F;
            this.frontLeg.xRot = 0.0F;
            this.midLeg.xRot = 0.0F;
            this.backLeg.xRot = 0.0F;
        } else {
            float f = p_225597_4_ * 2.1F;
            this.rightWing.yRot = 0.0F;
            this.rightWing.zRot = MathHelper.cos(f) * (float)Math.PI * 0.15F;
            this.leftWing.xRot = this.rightWing.xRot;
            this.leftWing.yRot = this.rightWing.yRot;
            this.leftWing.zRot = -this.rightWing.zRot;
            this.frontLeg.xRot = ((float)Math.PI / 4F);
            this.midLeg.xRot = ((float)Math.PI / 4F);
            this.backLeg.xRot = ((float)Math.PI / 4F);
            this.bone.xRot = 0.0F;
            this.bone.yRot = 0.0F;
            this.bone.zRot = 0.0F;
        }
    }
    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        bone.render(matrixStack, buffer, packedLight, packedOverlay);
        leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
        rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
        frontLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        midLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        backLeg.render(matrixStack, buffer, packedLight, packedOverlay);
        leftAntenna.render(matrixStack, buffer, packedLight, packedOverlay);
        rightAntenna.render(matrixStack, buffer, packedLight, packedOverlay);
        torso.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
