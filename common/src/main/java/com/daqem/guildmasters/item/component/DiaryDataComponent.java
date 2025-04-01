package com.daqem.guildmasters.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record DiaryDataComponent(UUID owner) {

    public static final Codec<DiaryDataComponent> CODEC = Codec.lazyInitialized(() ->
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.STRING.fieldOf("owner").forGetter(component -> component.owner().toString())
            ).apply(instance, owner -> new DiaryDataComponent(UUID.fromString(owner))))
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, DiaryDataComponent> STREAM_CODEC = new StreamCodec<>() {

        @Override
        public void encode(RegistryFriendlyByteBuf buf, DiaryDataComponent component) {
            buf.writeUtf(component.owner().toString());
        }

        @Override
        public @NotNull DiaryDataComponent decode(RegistryFriendlyByteBuf buf) {
            return new DiaryDataComponent(UUID.fromString(buf.readUtf()));
        }
    };
}
