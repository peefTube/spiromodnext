package com.github.peeftube.spiromodnext.util.ore;

import com.github.peeftube.spiromodnext.core.init.Registry;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public interface OreUtilities
{
    static Map<BaseStone, Map<OreMaterial, Coupling>> getComboPresets()
    {
        Map<BaseStone, Map<OreMaterial, Coupling>> reference     = new HashMap<>();
        Map<OreMaterial, Coupling>                               stoneDefaults = new HashMap<>();
        Map<OreMaterial, Coupling>                               deepslateDefaults  = new HashMap<>();
        Map<OreMaterial, Coupling>                               netherrackDefaults = new HashMap<>();

        // Stone.
        stoneDefaults.put(OreMaterial.COAL, new Coupling(() -> Blocks.COAL_ORE, () -> Items.COAL_ORE));
        stoneDefaults.put(OreMaterial.IRON, new Coupling(() -> Blocks.IRON_ORE, () -> Items.IRON_ORE));
        stoneDefaults.put(OreMaterial.COPPER, new Coupling(() -> Blocks.COPPER_ORE, () -> Items.COPPER_ORE));
        stoneDefaults.put(OreMaterial.GOLD, new Coupling(() -> Blocks.GOLD_ORE, () -> Items.GOLD_ORE));
        stoneDefaults.put(OreMaterial.LAPIS, new Coupling(() -> Blocks.LAPIS_ORE, () -> Items.LAPIS_ORE));
        stoneDefaults.put(OreMaterial.REDSTONE, new Coupling(() -> Blocks.REDSTONE_ORE, () -> Items.REDSTONE_ORE));
        stoneDefaults.put(OreMaterial.EMERALD, new Coupling(() -> Blocks.EMERALD_ORE, () -> Items.EMERALD_ORE));
        stoneDefaults.put(OreMaterial.DIAMOND, new Coupling(() -> Blocks.DIAMOND_ORE, () -> Items.DIAMOND_ORE));
        reference.put(BaseStone.STONE, stoneDefaults);

        // Deepslate.
        deepslateDefaults.put(OreMaterial.COAL,
                new Coupling(() -> Blocks.DEEPSLATE_COAL_ORE, () -> Items.DEEPSLATE_COAL_ORE));
        deepslateDefaults.put(OreMaterial.IRON,
                new Coupling(() -> Blocks.DEEPSLATE_IRON_ORE, () -> Items.DEEPSLATE_IRON_ORE));
        deepslateDefaults.put(OreMaterial.COPPER,
                new Coupling(() -> Blocks.DEEPSLATE_COPPER_ORE, () -> Items.DEEPSLATE_COPPER_ORE));
        deepslateDefaults.put(OreMaterial.GOLD,
                new Coupling(() -> Blocks.DEEPSLATE_GOLD_ORE, () -> Items.DEEPSLATE_GOLD_ORE));
        deepslateDefaults.put(OreMaterial.LAPIS,
                new Coupling(() -> Blocks.DEEPSLATE_LAPIS_ORE, () -> Items.DEEPSLATE_LAPIS_ORE));
        deepslateDefaults.put(OreMaterial.REDSTONE,
                new Coupling(() -> Blocks.DEEPSLATE_REDSTONE_ORE, () -> Items.DEEPSLATE_REDSTONE_ORE));
        deepslateDefaults.put(OreMaterial.EMERALD,
                new Coupling(() -> Blocks.DEEPSLATE_EMERALD_ORE, () -> Items.DEEPSLATE_EMERALD_ORE));
        deepslateDefaults.put(OreMaterial.DIAMOND,
                new Coupling(() -> Blocks.DEEPSLATE_DIAMOND_ORE, () -> Items.DEEPSLATE_DIAMOND_ORE));
        reference.put(BaseStone.DEEPSLATE, deepslateDefaults);

        // Netherrack.
        netherrackDefaults.put(OreMaterial.GOLD,
                new Coupling(() -> Blocks.NETHER_GOLD_ORE, () -> Items.NETHER_GOLD_ORE));
        netherrackDefaults.put(OreMaterial.QUARTZ,
                new Coupling(() -> Blocks.NETHER_QUARTZ_ORE, () -> Items.NETHER_QUARTZ_ORE));
        reference.put(BaseStone.NETHERRACK, netherrackDefaults);

        return reference;
    }

    static RawCoupling determineRawOre(OreMaterial material, int li)
    {
        // A map of existing raw material blocks with their associated items.
        Map<OreMaterial, RawCoupling> presets = new HashMap<>();
        presets.put(OreMaterial.COAL, new RawCoupling(new Coupling(() -> Blocks.COAL_BLOCK, () -> Items.COAL_BLOCK),
                () -> Items.COAL));
        presets.put(OreMaterial.IRON, new RawCoupling(new Coupling(() -> Blocks.RAW_IRON_BLOCK, () -> Items.RAW_IRON_BLOCK),
                () -> Items.RAW_IRON));
        presets.put(OreMaterial.COPPER, new RawCoupling(new Coupling(() -> Blocks.RAW_COPPER_BLOCK, () -> Items.RAW_COPPER_BLOCK),
                () -> Items.RAW_COPPER));
        presets.put(OreMaterial.GOLD, new RawCoupling(new Coupling(() -> Blocks.RAW_GOLD_BLOCK, () -> Items.RAW_GOLD_BLOCK),
                () -> Items.RAW_GOLD));
        presets.put(OreMaterial.LAPIS, new RawCoupling(new Coupling(() -> Blocks.LAPIS_BLOCK, () -> Items.LAPIS_BLOCK),
                () -> Items.LAPIS_LAZULI));
        presets.put(OreMaterial.REDSTONE, new RawCoupling(new Coupling(() -> Blocks.REDSTONE_BLOCK, () -> Items.REDSTONE_BLOCK),
                () -> Items.REDSTONE));
        presets.put(OreMaterial.EMERALD, new RawCoupling(new Coupling(() -> Blocks.EMERALD_BLOCK, () -> Items.EMERALD_BLOCK),
                () -> Items.EMERALD));
        presets.put(OreMaterial.DIAMOND, new RawCoupling(new Coupling(() -> Blocks.DIAMOND_BLOCK, () -> Items.DIAMOND_BLOCK),
                () -> Items.DIAMOND));
        presets.put(OreMaterial.QUARTZ, new RawCoupling(new Coupling(() -> Blocks.QUARTZ_BLOCK, () -> Items.QUARTZ_BLOCK),
                () -> Items.QUARTZ));

        if (presets.containsKey(material)) { return presets.get(material); }
        else
        {
            Coupling        c;
            String rawMineral = material.isGem() ? "" : "raw_";

            Supplier<Block> b = Registry.regBlock(rawMineral + material.get() + "_block",
                    () -> new Block(Registry.RAW_ORE.lightLevel(s -> li)));
            Supplier<Item> bi = Registry.regSimpleBlockItem((DeferredBlock<Block>) b);
            c = new Coupling(b, bi);

            return new RawCoupling(c, Registry.ITEMS.register(rawMineral + material.get(),
                    () -> new Item(new Item.Properties())));
        }
    }
}
