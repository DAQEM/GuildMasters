package com.daqem.guildmasters.entity;

import com.daqem.guildmasters.GuildMasters;
import com.daqem.guildmasters.world.GuildMastersPOIs;
import com.google.common.collect.ImmutableSet;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;

import java.util.function.Predicate;

public interface GuildMastersVillagerProfessions {

    Registrar<VillagerProfession> PROFESSIONS = GuildMasters.MANAGER.get().get(Registries.VILLAGER_PROFESSION);

    RegistrySupplier<VillagerProfession> GUILD_MASTER = register(
            "guild_master",
            holder -> holder.is(GuildMastersPOIs.GUILD_MASTER.getId()),
            SoundEvents.VILLAGER_WORK_CARTOGRAPHER
    );

    static RegistrySupplier<VillagerProfession> register(String id, Predicate<Holder<PoiType>> predicate, SoundEvent soundEvent) {
        return PROFESSIONS.register(GuildMasters.getId(id), () -> new VillagerProfession(GuildMasters.getId(id).toString(), predicate, predicate, ImmutableSet.of(), ImmutableSet.of(), soundEvent));
    }

    static void init() {
    }
}
