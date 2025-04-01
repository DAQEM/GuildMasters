package com.daqem.guildmasters.fabric.client;

import com.daqem.guildmasters.client.GuildMastersClient;
import com.daqem.guildmasters.client.gui.GuildMasterScreen;
import com.daqem.guildmasters.menu.GuildMastersMenus;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public final class GuildMastersFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GuildMastersClient.init();
        MenuScreens.register(GuildMastersMenus.GUILD_MASTER_MENU.get(), GuildMasterScreen::new);
    }
}
