package com.github.peeftube.spiromodnext.datagen.modules;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.Registry;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.List;
import java.util.function.Supplier;

public class ItemModelDataProv extends ItemModelProvider
{
    public ItemModelDataProv(PackOutput output, ExistingFileHelper eFH)
    {
        super(output, SpiroMod.MOD_ID, eFH);
    }

    @Override
    protected void registerModels()
    {
        // ============================================================================================================
        // Normal items


        // ============================================================================================================
        // Block items
        oreSetDesign(Registry.COAL_ORES);
        oreSetDesign(Registry.IRON_ORES);
        oreSetDesign(Registry.COPPER_ORES);
        oreSetDesign(Registry.GOLD_ORES);
        oreSetDesign(Registry.DIAMOND_ORES);
        oreSetDesign(Registry.LAPIS_ORES);
        oreSetDesign(Registry.REDSTONE_ORES);
        oreSetDesign(Registry.EMERALD_ORES);
        oreSetDesign(Registry.QUARTZ_ORES);
    }

    // Ore BlockItem handler subroutine
    // Copied from BlockstateDataProv.java
    protected void oreSetDesign(OreCollection set)
    {
        // Flags for whether we should ignore block-model creation.
        boolean ignoreStone  = false; // For ignoring default stone, assumes true for deepslate as well
        boolean ignoreNether = false; // For ignoring default Netherrack ore

        // Prepare set data.
        String                          material = set.getName();
        List<Supplier<? extends Item>>  items    = set.getItems();

        if (material.equals("coal") || material.equals("iron") || material.equals("copper") || material.equals("gold")
                || material.equals("diamond") || material.equals("lapis") || material.equals("redstone")
                || material.equals("emerald"))
        { ignoreStone = true; }

        if (material.equals("gold") || material.equals("quartz"))
        { ignoreNether = true; }

        for (int i = 0; i < items.size(); i++)
        {
            /* NOTE:
             * 0: default stone | 1: andesite | 2: diorite | 3: granite
             * 4: calcite | 5: smooth sandstone | 6: smooth red sandstone
             * 7: deepslate | 8: tuff | 9: dripstone | 10: netherrack
             * 11: basalt (smooth) | 12: endstone
             */
            if (((i == 0 || i == 7) && ignoreStone) || ((i == 10) && ignoreNether))
            { continue; } // Do nothing, we're using a combination which already has a BlockItem

            blockParser((DeferredItem<Item>) items.get(i));
        }
    }

    // Block item model creation subroutine
    protected void blockParser(DeferredItem<Item> item)
    {
        String path = "block/" + BuiltInRegistries.ITEM.getKey(item.get()).getPath();
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), modLoc(path));
    }
}
