package com.daqem.guildmasters.mixin;

import com.daqem.guildmasters.api.player.GuildMastersLocalPlayer;
import com.daqem.guildmasters.client.gui.DiaryScreen;
import com.daqem.guildmasters.model.DiaryScreenData;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer implements GuildMastersLocalPlayer {

    @Shadow @Final protected Minecraft minecraft;

    public LocalPlayerMixin(ClientLevel clientLevel, GameProfile gameProfile) {
        super(clientLevel, gameProfile);
    }

    @Override
    public void guildmasters$openDiary(DiaryScreenData data) {
        minecraft.setScreen(new DiaryScreen(data));
    }
}
