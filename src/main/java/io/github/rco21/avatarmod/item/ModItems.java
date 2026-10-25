package io.github.rco21.avatarmod.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = 
            DeferredRegister.create(BuiltInRegistries.ITEM, "avatarmod"); 

    public static final DeferredHolder<Item, Item> OSTRICHFEATHER = ITEMS.register("ostrichfeather",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> OSTRICHHORSEMEAT = ITEMS.register("ostrich_horse_meat",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus); 
    }
}