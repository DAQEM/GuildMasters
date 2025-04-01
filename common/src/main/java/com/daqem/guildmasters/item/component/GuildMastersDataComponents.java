package com.daqem.guildmasters.item.component;

import com.daqem.guildmasters.GuildMasters;
import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public interface GuildMastersDataComponents {

    Registrar<DataComponentType<?>> COMPONENTS = GuildMasters.MANAGER.get().get(Registries.DATA_COMPONENT_TYPE);

    RegistrySupplier<DataComponentType<DiaryDataComponent>> DIARY_DATA = register("diary_data", DiaryDataComponent.CODEC, DiaryDataComponent.STREAM_CODEC);

    static <T extends Record> RegistrySupplier<DataComponentType<T>> register(String id, Codec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
        return COMPONENTS.register(GuildMasters.getId(id), () -> ((DataComponentType.Builder) DataComponentType.builder()).persistent(codec).networkSynchronized(streamCodec).build());
    }

    static void init() {
    }
}
