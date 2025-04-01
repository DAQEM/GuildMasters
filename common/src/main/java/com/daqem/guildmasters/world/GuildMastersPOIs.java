package com.daqem.guildmasters.world;

import com.daqem.guildmasters.GuildMasters;
import com.google.common.collect.ImmutableSet;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public interface GuildMastersPOIs {

    Registrar<PoiType> POI_TYPES = GuildMasters.MANAGER.get().get(Registries.POINT_OF_INTEREST_TYPE);

    RegistrySupplier<PoiType> GUILD_MASTER = register("guild_master", Blocks.TNT);

    static RegistrySupplier<PoiType> register(String str, Block block) {
        Set<BlockState> blockStates = ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
        return POI_TYPES.register(GuildMasters.getId(str), () -> new PoiType(blockStates, 1, 1));
    }

    static void init() {
    }
}
