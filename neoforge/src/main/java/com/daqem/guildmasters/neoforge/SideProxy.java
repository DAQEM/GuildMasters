package com.daqem.guildmasters.neoforge;

import com.daqem.guildmasters.GuildMasters;
import com.daqem.guildmasters.client.GuildMastersClient;
import com.daqem.guildmasters.client.gui.GuildMasterScreen;
import com.daqem.guildmasters.menu.GuildMastersMenus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

public class SideProxy {

    public SideProxy() {
        GuildMasters.init();
    }

    public static class Client extends SideProxy {
        public Client(IEventBus modEventBus) {
            GuildMastersClient.init();
        }

        @SubscribeEvent
        private void registerScreens(RegisterMenuScreensEvent event) {
            event.register(GuildMastersMenus.GUILD_MASTER_MENU.get(), GuildMasterScreen::new);
        }
    }

    public static class Server extends SideProxy {
        public Server(IEventBus modEventBus) {
        }
    }
}
