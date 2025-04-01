package com.daqem.guildmasters.neoforge;

import com.daqem.guildmasters.GuildMasters;
import dev.architectury.utils.EnvExecutor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(GuildMasters.MOD_ID)
public final class GuildMastersNeoForge {
    public GuildMastersNeoForge(IEventBus modEventBus) {
        EnvExecutor.getEnvSpecific(
                () -> () -> new SideProxy.Client(modEventBus),
                () -> () -> new SideProxy.Server(modEventBus)
        );
    }
}
