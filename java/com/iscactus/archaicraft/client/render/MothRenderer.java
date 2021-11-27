package com.iscactus.archaicraft.client.render;

import com.iscactus.archaicraft.Archaicraft;
import com.iscactus.archaicraft.client.model.MothModel;
import com.iscactus.archaicraft.entity.passive.MothEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MothRenderer extends MobRenderer<MothEntity, MothModel<MothEntity>> {
    private static final ResourceLocation MOTH_TEXTURE = new ResourceLocation(Archaicraft.MOD_ID, "textures/entity/moth.png");

    public MothRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new MothModel<>(), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(MothEntity p_110775_1_) {
        return MOTH_TEXTURE;
    }
}
