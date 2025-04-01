package com.daqem.guildmasters.networking;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public interface GuildMastersPacket extends CustomPacketPayload {

    void handle(NetworkManager.PacketContext context);
}
