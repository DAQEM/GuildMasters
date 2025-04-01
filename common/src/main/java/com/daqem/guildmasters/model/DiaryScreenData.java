package com.daqem.guildmasters.model;

import net.minecraft.network.RegistryFriendlyByteBuf;

public class DiaryScreenData {

    public static DiaryScreenData fromNetwork(RegistryFriendlyByteBuf buf) {
        return new DiaryScreenData();
    }

    public void toNetwork(RegistryFriendlyByteBuf buf) {

    }
}
