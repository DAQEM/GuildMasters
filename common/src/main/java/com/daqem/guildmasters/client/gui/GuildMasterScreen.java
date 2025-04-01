package com.daqem.guildmasters.client.gui;

import com.daqem.guildmasters.menu.GuildMasterMenu;
import com.daqem.uilib.client.gui.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class GuildMasterScreen extends AbstractContainerScreen<GuildMasterMenu> {

    public GuildMasterScreen(GuildMasterMenu container, Inventory inventory, Component component) {
        super(container, inventory, component);
    }

    @Override
    public void startScreen() {
        setBackground(null);
    }

    @Override
    public void onTickScreen(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBlurredBackground(delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int i, int j) {
    }
}
