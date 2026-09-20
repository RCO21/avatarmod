package io.github.rco21.avatarmod.entity;
import io.github.rco21.avatarmod.entity.ModEntities;
import io.github.rco21.avatarmod.entity.custom.OstrichHorseEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    // 1. Create the register for Entities using the modern NeoForge package structure
    public static final DeferredRegister<EntityType<?>> ENTITIES = 
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, "avatarmod");

    // 2. Register the Ostrich Horse entity type
    public static final DeferredHolder<EntityType<?>, EntityType<OstrichHorseEntity>> OSTRICH_HORSE =
            ENTITIES.register("ostrich_horse", () -> EntityType.Builder.of(OstrichHorseEntity::new, MobCategory.CREATURE)
                    .sized(1.4F, 1.6F) // Sets the collision box dimensions (width, height)
                    .build("ostrich_horse"));

    // 3. Connect this register to the mod's event bus
    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}