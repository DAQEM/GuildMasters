package com.daqem.guildmasters.networking;

import com.daqem.guildmasters.GuildMasters;
import com.daqem.guildmasters.networking.clientbound.ClientboundOpenDiaryPacket;
import com.daqem.guildmasters.networking.serverbound.ServerboundOpenDiaryPacket;
import dev.architectury.networking.NetworkManager;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public interface GuildMastersNetworking {

    CustomPacketPayload.Type<GuildMastersPacket> SERVERBOUND_OPEN_DIARY = createType("serverbound_open_diary");

    CustomPacketPayload.Type<GuildMastersPacket> CLIENTBOUND_OPEN_DIARY = createType("clientbound_open_diary");

    static void initClient() {
        registerClientReceiver(CLIENTBOUND_OPEN_DIARY, ClientboundOpenDiaryPacket.STREAM_CODEC);
    }

    static void initCommon() {
        registerServerReceiver(SERVERBOUND_OPEN_DIARY, ServerboundOpenDiaryPacket.STREAM_CODEC);
    }

    static void initServer() {
        registerServerPayloadType(CLIENTBOUND_OPEN_DIARY, ClientboundOpenDiaryPacket.STREAM_CODEC);
    }

    static void init() {
        EnvExecutor.runInEnv(Env.CLIENT, () -> GuildMastersNetworking::initClient);
        EnvExecutor.runInEnv(Env.SERVER, () -> GuildMastersNetworking::initServer);
        initCommon();
    }

    static <T extends GuildMastersPacket> CustomPacketPayload.Type<T> createType(String str) {
        return new CustomPacketPayload.Type<>(GuildMasters.getId(str));
    }

    @SuppressWarnings("unchecked")
    static void registerClientReceiver(CustomPacketPayload.Type<GuildMastersPacket> type, StreamCodec<RegistryFriendlyByteBuf, ?> codec) {
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, type, (StreamCodec<RegistryFriendlyByteBuf, GuildMastersPacket>) codec, GuildMastersPacket::handle);
    }

    @SuppressWarnings("unchecked")
    static void registerServerReceiver(CustomPacketPayload.Type<GuildMastersPacket> type, StreamCodec<RegistryFriendlyByteBuf, ?> codec) {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, type, (StreamCodec<RegistryFriendlyByteBuf, GuildMastersPacket>) codec, GuildMastersPacket::handle);
    }

    @SuppressWarnings("unchecked")
    static void registerServerPayloadType(CustomPacketPayload.Type<?> type, StreamCodec<RegistryFriendlyByteBuf, ?> codec) {
        NetworkManager.registerS2CPayloadType((CustomPacketPayload.Type<GuildMastersPacket>) type, (StreamCodec<RegistryFriendlyByteBuf, GuildMastersPacket>) codec);
    }
}
