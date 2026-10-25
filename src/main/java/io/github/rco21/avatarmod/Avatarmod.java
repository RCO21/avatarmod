package io.github.rco21.avatarmod;

import io.github.rco21.avatarmod.entity.ModEntities;
import io.github.rco21.avatarmod.entity.custom.OstrichHorseEntity;
import io.github.rco21.avatarmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.fml.config.ModConfig;

@Mod(Avatarmod.MODID)
public class Avatarmod {
    public static final String MODID = "avatarmod";

    public Avatarmod(IEventBus modEventBus, ModContainer modContainer) {
        // 1. Registers your item database list (Crucial Fix!)
        ModItems.register(modEventBus);

        // 2. Registers your entity database list
        ModEntities.register(modEventBus);

        // 3. Registers your new custom creative tab (Crucial Fix!)
        ModCreativeTabs.register(modEventBus);

        // 4. Maps the initialization events through explicit functional references
        modEventBus.addListener(this::registerAttributes);
        modEventBus.addListener(this::registerSpawnPlacements);

        // 5. Registers your configuration file specifications
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
            ModEntities.OSTRICH_HORSE.get(),
            net.minecraft.world.entity.SpawnPlacementTypes.ON_GROUND,
            net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            net.minecraft.world.entity.animal.Animal::checkAnimalSpawnRules,
            RegisterSpawnPlacementsEvent.Operation.AND
        );
    }

    public void registerAttributes(EntityAttributeCreationEvent event) {
        event.put((EntityType<? extends LivingEntity>) ModEntities.OSTRICH_HORSE.get(), OstrichHorseEntity.createAttributes().build());
    }
}