package io.github.rco21.avatarmod.entity.custom;

import io.github.rco21.avatarmod.entity.custom.OstrichHorseEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OstrichHorseModel extends GeoModel<OstrichHorseEntity> {
    @Override
    public ResourceLocation getModelResource(OstrichHorseEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath("avatarmod", "geo/ostrich_horse.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OstrichHorseEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath("avatarmod", "textures/entity/ostrich_horse.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OstrichHorseEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath("avatarmod", "animations/ostrich_horse.animation.json");
    }
}