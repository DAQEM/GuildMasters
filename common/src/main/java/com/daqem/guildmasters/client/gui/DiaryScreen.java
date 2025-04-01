package com.daqem.guildmasters.client.gui;

import com.daqem.guildmasters.GuildMasters;
import com.daqem.guildmasters.model.DiaryScreenData;
import com.daqem.uilib.client.gui.AbstractScreen;
import net.minecraft.client.gui.GuiGraphics;

public class DiaryScreen extends AbstractScreen {

    private final DiaryScreenData data;

    public DiaryScreen(DiaryScreenData data) {
        super(GuildMasters.translatable("dairy"));
        this.data = data;
    }

    @Override
    public void startScreen() {

    }

    @Override
    public void onTickScreen(GuiGraphics guiGraphics, int i, int i1, float v) {

    }
}
