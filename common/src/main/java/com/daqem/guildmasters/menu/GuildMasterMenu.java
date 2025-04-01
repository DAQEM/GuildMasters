package com.daqem.guildmasters.menu;

import com.daqem.guildmasters.GuildMasters;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuildMasterMenu extends AbstractContainerMenu {

    public GuildMasterMenu(int windowId, Inventory inventory, Player player) {
        this(windowId, inventory);
    }

    public GuildMasterMenu(int windowId, Inventory inventory) {
        super(GuildMastersMenus.GUILD_MASTER_MENU.get(), windowId);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int slotId) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public static class MenuProvider implements net.minecraft.world.MenuProvider {

        @Override
        public @NotNull Component getDisplayName() {
            return GuildMasters.translatable("gui.guild_master");
        }

        @Override
        public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
            return new GuildMasterMenu(i, inventory, player);
        }
    }
}
