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
        // Explicitly hard-map each variant directly to its asset path
        switch (animatable.getVariant()) {
            case DARK:
                return ResourceLocation.fromNamespaceAndPath("avatarmod", "textures/entity/ostrich_horse/ostrich_horse_darkbrown.png");
            case BLACK:
                return ResourceLocation.fromNamespaceAndPath("avatarmod", "textures/entity/ostrich_horse/ostrich_horse_black.png");
            case ORANGISH:
                return ResourceLocation.fromNamespaceAndPath("avatarmod", "textures/entity/ostrich_horse/ostrich_horse_orangishbrown.png");
            case ORANGE:
                return ResourceLocation.fromNamespaceAndPath("avatarmod", "textures/entity/ostrich_horse/ostrich_horse_orange.png");
            case DEFAULT:
            default:
                return ResourceLocation.fromNamespaceAndPath("avatarmod", "textures/entity/ostrich_horse/ostrich_horse.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(OstrichHorseEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath("avatarmod", "animations/ostrich_horse.animation.json");
    }
}