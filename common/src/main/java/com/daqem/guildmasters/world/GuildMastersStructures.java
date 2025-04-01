package com.daqem.guildmasters.world;

import com.daqem.guildmasters.GuildMasters;
import com.daqem.guildmasters.mixin.StructureTemplatePoolAccessor;
import com.mojang.datafixers.util.Pair;
import dev.architectury.event.events.common.LifecycleEvent;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.ArrayList;
import java.util.List;

public interface GuildMastersStructures {

    ResourceKey<StructureProcessorList> EMPTY_PROCESSOR_LIST_KEY = ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.parse("empty"));

    ResourceLocation PLAINS_HOUSES = ResourceLocation.parse("village/plains/houses");

    ResourceLocation PLAINS_GUILD_MASTER_HOUSE_1 = GuildMasters.getId("village/plains/houses/plains_guild_master_house_1");

    static void register() {
        LifecycleEvent.SERVER_BEFORE_START.register(server -> {
            addBuildingToPool(server, PLAINS_HOUSES, PLAINS_GUILD_MASTER_HOUSE_1, 150);
        });
    }

    static void addBuildingToPool(MinecraftServer server, ResourceLocation poolLocation, ResourceLocation structureLocation, int weight) {
        Registry<StructureTemplatePool> templatePoolRegistry = server.registryAccess().registry(Registries.TEMPLATE_POOL).orElseThrow();
        Registry<StructureProcessorList> processorListRegistry = server.registryAccess().registry(Registries.PROCESSOR_LIST).orElseThrow();

        Holder<StructureProcessorList> emptyProcessorList = processorListRegistry.getHolderOrThrow(EMPTY_PROCESSOR_LIST_KEY);

        StructureTemplatePool pool = templatePoolRegistry.get(poolLocation);
        if (pool == null) return;

        SinglePoolElement piece = SinglePoolElement.single(structureLocation.toString(), emptyProcessorList).apply(StructureTemplatePool.Projection.RIGID);

        for (int i = 0; i < weight; i++) {
            ((StructureTemplatePoolAccessor) pool).guildmasters$getTemplates().add(piece);
        }

        List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(((StructureTemplatePoolAccessor) pool).guildmasters$getRawTemplates());
        listOfPieceEntries.add(new Pair<>(piece, weight));
        ((StructureTemplatePoolAccessor) pool).guildmasters$setRawTemplates(listOfPieceEntries);
    }
}
