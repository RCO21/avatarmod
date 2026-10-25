package io.github.rco21.avatarmod;

import io.github.rco21.avatarmod.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Avatarmod.MODID);

   
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AVATAR_TAB = 
            CREATIVE_MODE_TABS.register("avatar_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.avatarmod")) 
                    .icon(() -> new ItemStack(ModItems.OSTRICHFEATHER.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.OSTRICHFEATHER.get());
                        output.accept(ModItems.OSTRICHHORSEMEAT.get());
                        
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}