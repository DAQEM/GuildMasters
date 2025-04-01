package com.daqem.guildmasters.item;

import com.daqem.guildmasters.GuildMasters;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public interface GuildMastersItems {

    Registrar<Item> ITEMS = GuildMasters.MANAGER.get().get(Registries.ITEM);

    RegistrySupplier<Item> GUILD_MASTERS_DIARY = register(GuildMasters.getId("diary"), DiaryItem::new);

    @SuppressWarnings("UnstableApiUsage")
    static <T extends Item> RegistrySupplier<T> register(ResourceLocation id, Function<Item.Properties, T> constructor) {
        return ITEMS.register(id, () -> constructor.apply(new Item.Properties().arch$tab(GuildMastersCreativeTabs.TAB)));
    }

    static void init() {
    }
}
