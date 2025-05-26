package com.github.peeftube.spiromodnext.datagen.modules.world.util;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.Registrar;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ConfigFeaturesData
{
    private static      HolderGetter<Block>                  blocks;

    public static final ResourceKey<ConfiguredFeature<? ,?>> OVERRIDE_STOCK = registerKey("sm_override_stock_biome_blocks");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        blocks = context.lookup(Registries.BLOCK);
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name)
    { return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(SpiroMod.MOD_ID, name)); }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void
    register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<? ,?>> key, F feature, FC configuration)
    { context.register(key, new ConfiguredFeature<>(feature, configuration)); }

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String key, F value)
    {
        Registrar.FEATURES.register(key, () -> value);
        return value;
    }

    public static void register() {}
}
