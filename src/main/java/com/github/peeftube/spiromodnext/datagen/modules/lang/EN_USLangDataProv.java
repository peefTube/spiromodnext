package com.github.peeftube.spiromodnext.datagen.modules.lang;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.Registry;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.List;
import java.util.function.Supplier;

public class EN_USLangDataProv extends LanguageProvider
{
    public EN_USLangDataProv(PackOutput output, String locale)
    { super(output, SpiroMod.MOD_ID, locale); }

    @Override
    protected void addTranslations()
    {
        oreParser(Registry.COAL_ORES);
        oreParser(Registry.IRON_ORES);
        oreParser(Registry.COPPER_ORES);
        oreParser(Registry.GOLD_ORES);
        oreParser(Registry.LAPIS_ORES);
        oreParser(Registry.REDSTONE_ORES);
        oreParser(Registry.DIAMOND_ORES);
        oreParser(Registry.EMERALD_ORES);
        oreParser(Registry.QUARTZ_ORES);
    }

    // Ore set handler
    protected void oreParser(OreCollection set)
    {
        // Flags for whether we should ignore block-model creation.
        boolean ignoreStone = false; // For ignoring default stone, assumes true for deepslate as well
        boolean ignoreNether = false; // For ignoring default Netherrack ore

        // Prepare set data.
        String                          material = set.getName();
        List<Supplier<? extends Block>> blocks   = set.getBlocks();

        if (material.equals("coal") || material.equals("iron") || material.equals("copper") || material.equals("gold")
                || material.equals("diamond") || material.equals("lapis") || material.equals("redstone")
                || material.equals("emerald"))
        { ignoreStone = true; }

        if (material.equals("gold") || material.equals("quartz"))
        { ignoreNether = true; }

        for (int i = 0; i < blocks.size(); i++)
        {
            /* NOTE:
             * 0: default stone | 1: andesite | 2: diorite | 3: granite
             * 4: calcite | 5: smooth sandstone | 6: smooth red sandstone
             * 7: deepslate | 8: tuff | 9: dripstone | 10: netherrack
             * 11: basalt (smooth) | 12: endstone
             */
            if (((i == 0 || i == 7) && ignoreStone) || ((i == 10) && ignoreNether))
            { continue; } // Do nothing, we're using a material which already uses this combination...

            add(blocks.get(i).get(), generateOreBlockString(i, material));
        }
    }

    // Ore set String subroutine
    protected String generateOreBlockString(int t, String mat)
    {
        String readable = mat.substring(0, 1).toUpperCase() + mat.substring(1) + " Ore";

        switch(t) // Case switch based on passed stone type (int format)
        {
            case 0 -> { return readable; }
            case 1 -> { return readable + " (Andesite)"; }
            case 2 -> { return readable + " (Diorite)"; }
            case 3 -> { return readable + " (Granite)"; }
            case 4 -> { return readable + " (Calcite)"; }
            case 5 -> { return readable + " (Sandstone)"; }
            case 6 -> { return readable + " (Red Sandstone)"; }
            case 7 -> { return "Deepslate " + readable; }
            case 8 -> { return readable + " (Tuff)"; }
            case 9 -> { return readable + " (Dripstone)"; }
            case 10 -> { return "Nether " + readable; }
            case 11 -> { return readable + " (Basalt)"; }
            case 12 -> { return "Ender " + readable; }
            default -> { return "error:no translation for " + mat + ":" + t; }
        }
    }
}
