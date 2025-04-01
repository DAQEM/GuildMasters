package com.daqem.guildmasters.item;

import com.daqem.guildmasters.GuildMasters;
import com.daqem.guildmasters.item.component.DiaryDataComponent;
import com.daqem.guildmasters.item.component.GuildMastersDataComponents;
import com.daqem.guildmasters.networking.serverbound.ServerboundOpenDiaryPacket;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.ProfileResult;
import dev.architectury.networking.NetworkManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public class DiaryItem extends Item {

    public DiaryItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (level.isClientSide) {
            UUID owner = itemStack.getOrDefault(
                    GuildMastersDataComponents.DIARY_DATA.get(),
                    new DiaryDataComponent(UUID.fromString("00000000-0000-0000-0000-000000000000"))
            ).owner();
            NetworkManager.sendToServer(new ServerboundOpenDiaryPacket(owner, hand));
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, tooltipContext, list, tooltipFlag);
        Minecraft minecraft = Minecraft.getInstance();
        DiaryDataComponent diaryDataComponent = itemStack.get(GuildMastersDataComponents.DIARY_DATA.get());
        if (diaryDataComponent != null) {
            UUID owner = diaryDataComponent.owner();
            MinecraftSessionService sessionService = minecraft.getMinecraftSessionService();
            ProfileResult profile = sessionService.fetchProfile(owner, false);
            if (profile != null) {
                list.add(GuildMasters.translatable("diary.owner", profile.profile().getName()).withColor(ChatFormatting.GRAY.getColor().intValue()));
            } else {
                list.add(GuildMasters.translatable("diary.owner", GuildMasters.translatable("diary.unknown")).withColor(ChatFormatting.GRAY.getColor().intValue()));
            }
        }
    }
}
