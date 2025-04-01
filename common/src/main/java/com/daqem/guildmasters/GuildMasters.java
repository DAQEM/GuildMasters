package com.daqem.guildmasters;

import com.daqem.guildmasters.entity.GuildMastersVillagerProfessions;
import com.daqem.guildmasters.item.GuildMastersCreativeTabs;
import com.daqem.guildmasters.item.GuildMastersItems;
import com.daqem.guildmasters.item.component.GuildMastersDataComponents;
import com.daqem.guildmasters.menu.GuildMastersMenus;
import com.daqem.guildmasters.networking.GuildMastersNetworking;
import com.daqem.guildmasters.world.GuildMastersPOIs;
import com.daqem.guildmasters.world.GuildMastersStructures;
import com.google.common.base.Suppliers;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Lifecycle;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.function.Supplier;

public final class GuildMasters {

    public static final String MOD_ID = "guildmasters";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static void init() {
        GuildMastersNetworking.init();
        GuildMastersCreativeTabs.init();
        GuildMastersDataComponents.init();
        GuildMastersItems.init();
        GuildMastersMenus.init();
        GuildMastersPOIs.init();
        GuildMastersVillagerProfessions.init();

        registerEvents();

        LifecycleEvent.SERVER_BEFORE_START.register(server -> {
            HolderSet.Named<PoiType> tag = server.registryAccess().registry(Registries.POINT_OF_INTEREST_TYPE).get().getTag(PoiTypeTags.ACQUIRABLE_JOB_SITE).get();
            tag.stream().forEach(x -> LOGGER.info(x.toString()));

            server.registryAccess().registry(Registries.VILLAGER_PROFESSION).get().forEach(x -> LOGGER.info(x.toString()));
        });
    }

    static void registerEvents() {
        GuildMastersStructures.register();
    }

    public static ResourceLocation getId(String str) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, str);
    }

    public static MutableComponent translatable(String str) {
        return Component.translatable(MOD_ID + "." + str);
    }

    public static MutableComponent translatable(String str, Object... objects) {
        return Component.translatable(MOD_ID + "." + str, objects);
    }
}
