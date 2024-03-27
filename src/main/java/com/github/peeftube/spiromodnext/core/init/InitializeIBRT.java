package com.github.peeftube.spiromodnext.core.init;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreMaterial;
import com.github.peeftube.spiromodnext.util.ore.BaseStone;
import com.github.peeftube.spiromodnext.util.ore.Coupling;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;

import java.util.Map;

public class InitializeIBRT
{
    public static void go()
    {
        // Ore handling.
        oreSettings(Registrar.COAL_ORES);
        oreSettings(Registrar.IRON_ORES);
        oreSettings(Registrar.COPPER_ORES);
        oreSettings(Registrar.GOLD_ORES);
        oreSettings(Registrar.LAPIS_ORES);
        oreSettings(Registrar.REDSTONE_ORES);
        oreSettings(Registrar.EMERALD_ORES);
        oreSettings(Registrar.DIAMOND_ORES);
        oreSettings(Registrar.QUARTZ_ORES);
        oreSettings(Registrar.RUBY_ORES);
    }

    protected static void oreSettings(OreCollection set)
    {
        // Flags for what we should ignore.
        boolean ignoreStone = false; // For ignoring default stone, assumes true for deepslate as well
        boolean ignoreNether = false; // For ignoring default Netherrack ore
        // DO NOT include packed ore blocks in this unless you want a headache.

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

            // I don't know why, I don't want to know why, I shouldn't have to know why, but without this
            // logger call this code doesn't seem to want to work properly.
            // Oracle pls fix
            SpiroMod.LOGGER.info("RUNNING: " + s.get().toUpperCase() + material.get().toUpperCase());

            // Change render type status.
            ItemBlockRenderTypes.setRenderLayer(b, ChunkRenderTypeSet.of(RenderType.solid(), RenderType.translucent()));
        }
    }
}
