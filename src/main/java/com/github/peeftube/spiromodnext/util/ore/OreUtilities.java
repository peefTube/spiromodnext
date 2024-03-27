package com.github.peeftube.spiromodnext.util.ore;

import com.github.peeftube.spiromodnext.core.init.Registrar;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public interface OreUtilities
{
    static Map<BaseStone, Map<OreMaterial, OreCoupling>> getComboPresets()
    {
        Map<BaseStone, Map<OreMaterial, OreCoupling>> reference          = new HashMap<>();
        Map<OreMaterial, OreCoupling>                 stoneDefaults      = new HashMap<>();
        Map<OreMaterial, OreCoupling>                 deepslateDefaults  = new HashMap<>();
        Map<OreMaterial, OreCoupling>                 netherrackDefaults = new HashMap<>();

        // Stone.
        stoneDefaults.put(OreMaterial.COAL, new OreCoupling(() -> Blocks.COAL_ORE, () -> Items.COAL_ORE));
        stoneDefaults.put(OreMaterial.IRON, new OreCoupling(() -> Blocks.IRON_ORE, () -> Items.IRON_ORE));
        stoneDefaults.put(OreMaterial.COPPER, new OreCoupling(() -> Blocks.COPPER_ORE, () -> Items.COPPER_ORE));
        stoneDefaults.put(OreMaterial.GOLD, new OreCoupling(() -> Blocks.GOLD_ORE, () -> Items.GOLD_ORE));
        stoneDefaults.put(OreMaterial.LAPIS, new OreCoupling(() -> Blocks.LAPIS_ORE, () -> Items.LAPIS_ORE));
        stoneDefaults.put(OreMaterial.REDSTONE, new OreCoupling(() -> Blocks.REDSTONE_ORE, () -> Items.REDSTONE_ORE));
        stoneDefaults.put(OreMaterial.EMERALD, new OreCoupling(() -> Blocks.EMERALD_ORE, () -> Items.EMERALD_ORE));
        stoneDefaults.put(OreMaterial.DIAMOND, new OreCoupling(() -> Blocks.DIAMOND_ORE, () -> Items.DIAMOND_ORE));
        reference.put(BaseStone.STONE, stoneDefaults);

        // Deepslate.
        deepslateDefaults.put(OreMaterial.COAL,
                new OreCoupling(() -> Blocks.DEEPSLATE_COAL_ORE, () -> Items.DEEPSLATE_COAL_ORE));
        deepslateDefaults.put(OreMaterial.IRON,
                new OreCoupling(() -> Blocks.DEEPSLATE_IRON_ORE, () -> Items.DEEPSLATE_IRON_ORE));
        deepslateDefaults.put(OreMaterial.COPPER,
                new OreCoupling(() -> Blocks.DEEPSLATE_COPPER_ORE, () -> Items.DEEPSLATE_COPPER_ORE));
        deepslateDefaults.put(OreMaterial.GOLD,
                new OreCoupling(() -> Blocks.DEEPSLATE_GOLD_ORE, () -> Items.DEEPSLATE_GOLD_ORE));
        deepslateDefaults.put(OreMaterial.LAPIS,
                new OreCoupling(() -> Blocks.DEEPSLATE_LAPIS_ORE, () -> Items.DEEPSLATE_LAPIS_ORE));
        deepslateDefaults.put(OreMaterial.REDSTONE,
                new OreCoupling(() -> Blocks.DEEPSLATE_REDSTONE_ORE, () -> Items.DEEPSLATE_REDSTONE_ORE));
        deepslateDefaults.put(OreMaterial.EMERALD,
                new OreCoupling(() -> Blocks.DEEPSLATE_EMERALD_ORE, () -> Items.DEEPSLATE_EMERALD_ORE));
        deepslateDefaults.put(OreMaterial.DIAMOND,
                new OreCoupling(() -> Blocks.DEEPSLATE_DIAMOND_ORE, () -> Items.DEEPSLATE_DIAMOND_ORE));
        reference.put(BaseStone.DEEPSLATE, deepslateDefaults);

        // Netherrack.
        netherrackDefaults.put(OreMaterial.GOLD,
                new OreCoupling(() -> Blocks.NETHER_GOLD_ORE, () -> Items.NETHER_GOLD_ORE));
        netherrackDefaults.put(OreMaterial.QUARTZ,
                new OreCoupling(() -> Blocks.NETHER_QUARTZ_ORE, () -> Items.NETHER_QUARTZ_ORE));
        reference.put(BaseStone.NETHERRACK, netherrackDefaults);

        return reference;
    }

    static RawCoupling determineRawOre(OreMaterial material, int li)
    {
        // A map of existing raw material blocks with their associated items.
        Map<OreMaterial, RawCoupling> presets = new HashMap<>();
        presets.put(OreMaterial.COAL, new RawCoupling(new OreCoupling(() -> Blocks.COAL_BLOCK, () -> Items.COAL_BLOCK),
                () -> Items.COAL));
        presets.put(OreMaterial.IRON, new RawCoupling(new OreCoupling(() -> Blocks.RAW_IRON_BLOCK, () -> Items.RAW_IRON_BLOCK),
                () -> Items.RAW_IRON));
        presets.put(OreMaterial.COPPER, new RawCoupling(new OreCoupling(() -> Blocks.RAW_COPPER_BLOCK, () -> Items.RAW_COPPER_BLOCK),
                () -> Items.RAW_COPPER));
        presets.put(OreMaterial.GOLD, new RawCoupling(new OreCoupling(() -> Blocks.RAW_GOLD_BLOCK, () -> Items.RAW_GOLD_BLOCK),
                () -> Items.RAW_GOLD));
        presets.put(OreMaterial.LAPIS, new RawCoupling(new OreCoupling(() -> Blocks.LAPIS_BLOCK, () -> Items.LAPIS_BLOCK),
                () -> Items.LAPIS_LAZULI));
        presets.put(OreMaterial.REDSTONE, new RawCoupling(new OreCoupling(() -> Blocks.REDSTONE_BLOCK, () -> Items.REDSTONE_BLOCK),
                () -> Items.REDSTONE));
        presets.put(OreMaterial.EMERALD, new RawCoupling(new OreCoupling(() -> Blocks.EMERALD_BLOCK, () -> Items.EMERALD_BLOCK),
                () -> Items.EMERALD));
        presets.put(OreMaterial.DIAMOND, new RawCoupling(new OreCoupling(() -> Blocks.DIAMOND_BLOCK, () -> Items.DIAMOND_BLOCK),
                () -> Items.DIAMOND));
        presets.put(OreMaterial.QUARTZ, new RawCoupling(new OreCoupling(() -> Blocks.QUARTZ_BLOCK, () -> Items.QUARTZ_BLOCK),
                () -> Items.QUARTZ));

        if (presets.containsKey(material)) { return presets.get(material); }
        else
        {
            OreCoupling c;
            String      rawMineral = material.isGem() ? "" : "raw_";

            Supplier<Block> b = Registrar.regBlock(rawMineral + material.get() + "_block",
                    () -> new Block(Registrar.RAW_ORE.lightLevel(s -> li)));
            Supplier<Item> bi = Registrar.regSimpleBlockItem((DeferredBlock<Block>) b);
            c = new OreCoupling(b, bi);

            return new RawCoupling(c, Registrar.ITEMS.register(rawMineral + material.get(),
                    () -> new Item(new Item.Properties())));
        }
    }
}
