package com.daqem.guildmasters.menu;

import com.daqem.guildmasters.GuildMasters;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public interface GuildMastersMenus {

    Registrar<MenuType<?>> MENUS = GuildMasters.MANAGER.get().get(Registries.MENU);

    RegistrySupplier<MenuType<GuildMasterMenu>> GUILD_MASTER_MENU = register("guild_master_menu", GuildMasterMenu::new);

    static <T extends GuildMasterMenu> RegistrySupplier<MenuType<T>> register(String str, MenuType.MenuSupplier<T> menuSupplier) {
        return MENUS.register(GuildMasters.getId(str), () -> new MenuType<>(menuSupplier, FeatureFlags.VANILLA_SET));
    }

    static void init() {
    }
}
