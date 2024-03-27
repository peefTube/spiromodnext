package com.github.peeftube.spiromodnext.datagen.modules.loot.subprov;

import com.github.peeftube.spiromodnext.core.init.Registrar;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreMaterial;
import com.github.peeftube.spiromodnext.util.ore.BaseStone;
import com.github.peeftube.spiromodnext.util.ore.Coupling;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.EntryGroup;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Map;
import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider
{
    public BlockLootTables()
    { super(Set.of(), FeatureFlags.REGISTRY.allFlags()); }

    @Override
    protected void generate()
    {
        // Ore tables
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreTables(ore); }
    }

    protected void oreTables(OreCollection set)
    {
        // Flags for what we should ignore.
        boolean ignoreStone = false; // For ignoring default stone, assumes true for deepslate as well
        boolean ignoreNether = false; // For ignoring default Netherrack ore
        // NOTE: these two may be used in an OR statement to determine if this is a vanilla block. If so,
        //       code should ignore the raw ore blocks.

        // Prepare set data.
        OreMaterial              material = set.getMat();
        Map<BaseStone, Coupling> bulkData = set.getBulkData();

        if (material == OreMaterial.COAL || material == OreMaterial.IRON || material == OreMaterial.COPPER
                || material == OreMaterial.GOLD || material == OreMaterial.LAPIS || material == OreMaterial.REDSTONE
                || material == OreMaterial.EMERALD || material == OreMaterial.DIAMOND)
        { ignoreStone = true; }

        if (material == OreMaterial.GOLD || material == OreMaterial.QUARTZ)
        { ignoreNether = true; }

        for (BaseStone s : bulkData.keySet())
        {
            if (((s == BaseStone.STONE || s == BaseStone.DEEPSLATE) && ignoreStone)
                    || ((s == BaseStone.NETHERRACK) && ignoreNether))
            { continue; } // Do nothing, we're using a material which already uses this combination,
                          // you'll want to use loot modifiers instead in this case.

            // Make this code easier to read, PLEASE..
            Block self = bulkData.get(s).getBlock().get();
            Item oreToDrop = set.getRawOre().getRawItem().get();
            NumberProvider oreToDropAmounts = set.getDropCount();

            add(self, oreTable01(self, s.getAssociatedBlock().get(), oreToDrop, oreToDropAmounts));
        }

        // For block of this ore, assuming that it doesn't exist in vanilla already.
        if (!(ignoreStone || ignoreNether)) { dropSelf(set.getRawOre().getCoupling().getBlock().get()); };
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    { return Registrar.BLOCKS.getEntries().stream().<Block>map(DeferredHolder::value).toList(); }

    protected LootTable.Builder oreTable01(Block b0, Block b1, Item item, NumberProvider dropAmtRange)
    {
        LootPool.Builder mainPool = LootPool.lootPool();

        mainPool.setRolls(ConstantValue.exactly(1))
                .add(AlternativesEntry.alternatives(

                        // Silk touch.
                        LootItem.lootTableItem(b0)
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item()
                                .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))))),

                        // Otherwise:
                        EntryGroup.list(
                                // Generic drop.
                                LootItem.lootTableItem(item)
                                        .apply(SetItemCountFunction.setCount(dropAmtRange))
                                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                                        .apply(ApplyExplosionDecay.explosionDecay()),

                                // Stone drop type associated with this ore variant.
                                LootItem.lootTableItem(b1)
                                        .when(LootItemRandomChanceCondition.randomChance(0.1f))))); // 10% chance to drop base block.

        return LootTable.lootTable().withPool(mainPool);
    }
}