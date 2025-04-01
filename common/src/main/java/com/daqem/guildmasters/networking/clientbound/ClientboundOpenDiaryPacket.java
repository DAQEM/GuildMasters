package com.daqem.guildmasters.networking.clientbound;

import com.daqem.guildmasters.api.player.GuildMastersLocalPlayer;
import com.daqem.guildmasters.model.DiaryScreenData;
import com.daqem.guildmasters.networking.GuildMastersNetworking;
import com.daqem.guildmasters.networking.GuildMastersPacket;
import dev.architectury.networking.NetworkManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public class ClientboundOpenDiaryPacket implements GuildMastersPacket {

    private final DiaryScreenData data;

    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundOpenDiaryPacket> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public @NotNull ClientboundOpenDiaryPacket decode(RegistryFriendlyByteBuf buf) {
            return new ClientboundOpenDiaryPacket(DiaryScreenData.fromNetwork(buf));
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf, ClientboundOpenDiaryPacket packet) {
            packet.data.toNetwork(buf);
        }
    };

    public ClientboundOpenDiaryPacket(DiaryScreenData data) {
        this.data = data;
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return GuildMastersNetworking.CLIENTBOUND_OPEN_DIARY;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void handle(NetworkManager.PacketContext context) {
        if (context.getPlayer() instanceof GuildMastersLocalPlayer localPlayer) {
            localPlayer.guildmasters$openDiary(data);
        }
    }
}
