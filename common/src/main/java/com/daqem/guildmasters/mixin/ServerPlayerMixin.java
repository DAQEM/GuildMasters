package com.daqem.guildmasters.mixin;

import com.daqem.guildmasters.api.player.GuildMastersServerPlayer;
import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player implements GuildMastersServerPlayer {

    @Unique
    private int guildmasters$reputation = 0;

    public ServerPlayerMixin(Level level, BlockPos blockPos, float f, GameProfile gameProfile) {
        super(level, blockPos, f, gameProfile);
    }

    @Inject(at = @At("TAIL"), method = "restoreFrom")
    private void guildmasters$restoreFrom(ServerPlayer serverPlayer, boolean bl, CallbackInfo ci) {
        if (serverPlayer instanceof GuildMastersServerPlayer guildMastersServerPlayer) {
            this.guildmasters$reputation = guildMastersServerPlayer.guildmasters$getReputation();
        }
    }

    @Inject(at = @At("TAIL"), method = "addAdditionalSaveData")
    private void guildmasters$addAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        CompoundTag guildmastersData = new CompoundTag();
        guildmastersData.putInt("Reputation", this.guildmasters$reputation);
        compoundTag.put("GuildMasters", guildmastersData);
    }

    @Inject(at = @At("TAIL"), method = "readAdditionalSaveData")
    private void guildmasters$readAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        if (compoundTag.contains("GuildMasters")) {
            CompoundTag guildmastersData = compoundTag.getCompound("GuildMasters");
            this.guildmasters$reputation = guildmastersData.getInt("Reputation");
        }
    }
}
