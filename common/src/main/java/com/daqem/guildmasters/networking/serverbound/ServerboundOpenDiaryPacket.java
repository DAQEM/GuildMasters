package com.daqem.guildmasters.networking.serverbound;

import com.daqem.guildmasters.GuildMasters;
import com.daqem.guildmasters.item.component.DiaryDataComponent;
import com.daqem.guildmasters.item.component.GuildMastersDataComponents;
import com.daqem.guildmasters.model.DiaryScreenData;
import com.daqem.guildmasters.networking.GuildMastersNetworking;
import com.daqem.guildmasters.networking.GuildMastersPacket;
import com.daqem.guildmasters.networking.clientbound.ClientboundOpenDiaryPacket;
import dev.architectury.networking.NetworkManager;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ServerboundOpenDiaryPacket implements GuildMastersPacket {

    private UUID owner;
    private final InteractionHand hand;

    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundOpenDiaryPacket> STREAM_CODEC = new StreamCodec<>() {
        @Override
        public @NotNull ServerboundOpenDiaryPacket decode(RegistryFriendlyByteBuf buf) {
            return new ServerboundOpenDiaryPacket(
                    buf.readUUID(),
                    buf.readEnum(InteractionHand.class)
            );
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buf, ServerboundOpenDiaryPacket packet) {
            buf.writeUUID(packet.owner);
            buf.writeEnum(packet.hand);
        }
    };

    public ServerboundOpenDiaryPacket(UUID owner, InteractionHand hand) {
        this.owner = owner;
        this.hand = hand;
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return GuildMastersNetworking.SERVERBOUND_OPEN_DIARY;
    }

    @Override
    public void handle(NetworkManager.PacketContext context) {
        if (this.owner.toString().equals("00000000-0000-0000-0000-000000000000")) {
            ItemStack itemStack = context.getPlayer().getItemInHand(hand);
            this.owner = context.getPlayer().getUUID();
            DiaryDataComponent diaryDataComponent = new DiaryDataComponent(owner);
            itemStack.set(GuildMastersDataComponents.DIARY_DATA.get(), diaryDataComponent);
        }
        GuildMasters.LOGGER.info("Opening diary for player: {}", this.owner);
        NetworkManager.sendToPlayer((ServerPlayer) context.getPlayer(), new ClientboundOpenDiaryPacket(new DiaryScreenData()));
    }
}
