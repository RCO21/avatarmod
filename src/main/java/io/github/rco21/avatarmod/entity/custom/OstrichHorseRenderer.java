package io.github.rco21.avatarmod.entity.custom;

import io.github.rco21.avatarmod.entity.custom.OstrichHorseEntity;
import io.github.rco21.avatarmod.entity.custom.OstrichHorseModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OstrichHorseRenderer extends GeoEntityRenderer<OstrichHorseEntity> {
    public OstrichHorseRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OstrichHorseModel());
        this.shadowRadius = 0.7F;
    }
}