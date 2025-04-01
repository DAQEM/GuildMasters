package com.daqem.guildmasters.item;

import com.daqem.guildmasters.GuildMasters;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public interface GuildMastersCreativeTabs {

    String TAB_NAME = GuildMasters.MOD_ID + "_tab";
    ResourceLocation TAB_LOCATION = GuildMasters.getId(TAB_NAME);

    Registrar<CreativeModeTab> TABS = GuildMasters.MANAGER.get().get(Registries.CREATIVE_MODE_TAB);

    RegistrySupplier<CreativeModeTab> TAB = TABS.register(
            TAB_LOCATION,
            () -> CreativeTabRegistry.create(Component.translatable("itemGroup." + GuildMasters.MOD_ID + "." + TAB_NAME),
                    () -> new ItemStack(GuildMastersItems.GUILD_MASTERS_DIARY.get())
            )
    );

    static void init() {
    }
}
