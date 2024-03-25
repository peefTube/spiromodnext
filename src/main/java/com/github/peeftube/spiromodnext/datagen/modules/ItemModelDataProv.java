package com.github.peeftube.spiromodnext.datagen.modules;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.Registry;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreMaterial;
import com.github.peeftube.spiromodnext.util.ore.BaseStone;
import com.github.peeftube.spiromodnext.util.ore.Coupling;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.List;
import java.util.Map;
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
        // Flags for what we should ignore.
        boolean ignoreStone  = false; // For ignoring default stone, assumes true for deepslate as well
        boolean ignoreNether = false; // For ignoring default Netherrack ore
        // NOTE: these two may be used in an OR statement to determine if this is a vanilla block. If so,
        //       code should ignore the raw ore blocks and raw ore item.
        // TODO: add handler for this!

        // Prepare set data.
        OreMaterial              material = set.getMat();
        Map<BaseStone, Coupling> bulkData = set.getBulkData();

        if (material == OreMaterial.COAL || material == OreMaterial.IRON || material == OreMaterial.COPPER
                || material == OreMaterial.GOLD || material == OreMaterial.LAPIS || material == OreMaterial.REDSTONE
                || material == OreMaterial.EMERALD || material == OreMaterial.DIAMOND)
        { ignoreStone = true; }

        if (material == OreMaterial.GOLD || material == OreMaterial.QUARTZ)
        { ignoreNether = true; }

        for (BaseStone s : BaseStone.values())
        {
            if (((s == BaseStone.STONE || s == BaseStone.DEEPSLATE) && ignoreStone)
                    || ((s == BaseStone.NETHERRACK) && ignoreNether))
            { continue; } // Do nothing, we're using a combination which already has a BlockItem

            // Make this code easier to read, PLEASE..
            Supplier<Item> i = bulkData.get(s).item();

            // This part is extremely easy, fortunately.
            blockParser((DeferredItem<Item>) i);
        }
    }

    // Block item model creation subroutine
    protected void blockParser(DeferredItem<Item> item)
    {
        String path = "block/" + BuiltInRegistries.ITEM.getKey(item.get()).getPath();
        withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), modLoc(path));
    }
}
