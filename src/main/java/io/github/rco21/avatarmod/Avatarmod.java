package io.github.rco21.avatarmod;

import io.github.rco21.avatarmod.entity.ModEntities;
import io.github.rco21.avatarmod.entity.custom.OstrichHorseEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.fml.config.ModConfig;

@Mod(Avatarmod.MODID)
public class Avatarmod {
    public static final String MODID = "avatarmod";

    public Avatarmod(IEventBus modEventBus, ModContainer modContainer) {
        // 1. Registers your entity database list
        ModEntities.register(modEventBus);

        // 2. Maps the initialization events through explicit functional references
        modEventBus.addListener(this::registerAttributes);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::registerSpawnPlacements);

        // 3. Registers your configuration file specifications
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        
        // CRUCIAL FIX: Removed the NeoForge.EVENT_BUS.register(this) line entirely!
        // This stops the engine from searching for annotations that don't exist.
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

    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            // event.accept(EXAMPLE_BLOCK_ITEM);
        }
    }

    public void registerAttributes(EntityAttributeCreationEvent event) {
        event.put((EntityType<? extends LivingEntity>) ModEntities.OSTRICH_HORSE.get(), OstrichHorseEntity.createAttributes().build());
    }
}