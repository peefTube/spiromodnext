package com.github.peeftube.spiromodnext.datagen.modules.world;

import com.github.peeftube.spiromodnext.datagen.modules.world.util.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldgenDataProv extends DatapackBuiltinEntriesProvider
{
    public static final RegistrySetBuilder worldgenRSB = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ConfigFeaturesData::bootstrap)
            .add(Registries.PLACED_FEATURE, PlacedFeaturesData::bootstrap)
            .add(Registries.NOISE_SETTINGS, NoiseSettingsData::bootstrap)
            .add(Registries.DIMENSION_TYPE, DimSettingsData::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, BiomeModifiersData::bootstrap);

    public WorldgenDataProv(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, Set<String> modIds)
    { super(output, registries, worldgenRSB, modIds); }
}
