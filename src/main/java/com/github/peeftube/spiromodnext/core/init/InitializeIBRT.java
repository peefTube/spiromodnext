package com.github.peeftube.spiromodnext.core.init;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreMaterial;
import com.github.peeftube.spiromodnext.util.ore.BaseStone;
import com.github.peeftube.spiromodnext.util.ore.Coupling;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;

import java.util.Map;

public class InitializeIBRT
{
    public static void go()
    {
        // Ore handling.
        oreSettings(Registry.COAL_ORES);
        oreSettings(Registry.IRON_ORES);
        oreSettings(Registry.COPPER_ORES);
        oreSettings(Registry.GOLD_ORES);
        oreSettings(Registry.LAPIS_ORES);
        oreSettings(Registry.REDSTONE_ORES);
        oreSettings(Registry.EMERALD_ORES);
        oreSettings(Registry.DIAMOND_ORES);
        oreSettings(Registry.QUARTZ_ORES);
    }

    protected static void oreSettings(OreCollection set)
    {
        // Flags for whether we should ignore block-model creation.
        boolean ignoreStone = false; // For ignoring default stone, assumes true for deepslate as well
        boolean ignoreNether = false; // For ignoring default Netherrack ore
        // NOTE: these two may be used in an OR statement to determine if this is a vanilla block. If so,
        //       code should ignore the raw ore blocks.
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
            { continue; } // Do nothing, we're using a material which already uses this combination...

            // Make this code easier to read, PLEASE..
            Block            b = bulkData.get(s).block().get();

            SpiroMod.LOGGER.info("RUNNING: " + s.get().toUpperCase() + material.get().toUpperCase());

            // Change render type status.
            ItemBlockRenderTypes.setRenderLayer(b, ChunkRenderTypeSet.of(RenderType.solid(), RenderType.translucent()));
        }
    }
}
