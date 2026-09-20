package io.github.rco21.avatarmod.entity;


import io.github.rco21.avatarmod.entity.custom.OstrichHorseRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = "avatarmod", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AvatarmodClient {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.OSTRICH_HORSE.get(), OstrichHorseRenderer::new);
    }
}