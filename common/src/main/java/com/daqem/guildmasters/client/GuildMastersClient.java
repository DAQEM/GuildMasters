package com.daqem.guildmasters.client;

import com.daqem.guildmasters.GuildMasters;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

@Environment(EnvType.CLIENT)
public class GuildMastersClient {

    public static void init() {
        EntityModelLayerRegistry.register(new ModelLayerLocation(GuildMasters.getId("guild_master"), "main"), () -> LayerDefinition.create(VillagerModel.createBodyModel(), 64, 64));
//        MenuRegistry.registerScreenFactory(GuildMastersMenus.GUILD_MASTER_MENU.get(), GuildMasterScreen::new);
    }
}
