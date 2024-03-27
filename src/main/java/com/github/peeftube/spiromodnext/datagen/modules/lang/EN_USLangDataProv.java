package com.github.peeftube.spiromodnext.datagen.modules.lang;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.Registrar;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreMaterial;
import com.github.peeftube.spiromodnext.util.ore.BaseStone;
import com.github.peeftube.spiromodnext.util.ore.Coupling;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.HashMap;
import java.util.Map;

public class EN_USLangDataProv extends LanguageProvider
{
    public EN_USLangDataProv(PackOutput output, String locale)
    { super(output, SpiroMod.MOD_ID, locale); }

    @Override
    protected void addTranslations()
    {
        // Ores
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreParser(ore); }

        // Override name of "Nether Quartz", call it "Quartz" instead.
        add(Items.QUARTZ, "Quartz");

        // Creative tabs
        add(Registrar.TAB_TITLE_KEY_FORMULAIC + ".minerals_tab", "Ores and Raw Minerals");
    }

    // Ore set handler
    protected void oreParser(OreCollection set)
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

        for (BaseStone s : BaseStone.values())
        {
            if (((s == BaseStone.STONE || s == BaseStone.DEEPSLATE) && ignoreStone)
                    || ((s == BaseStone.NETHERRACK) && ignoreNether))
            { continue; } // Do nothing, we're using a material which already uses this combination...

            // Make this code easier to read, PLEASE..
            Block b = bulkData.get(s).block().get();
            String mat = material.get();

            // Generate a translation string and then add it to the translation set.
            String readableMat = mat.substring(0, 1).toUpperCase() + mat.substring(1) + " Ore";
            add(b, generateOreBlockString(s, readableMat));
        }

        // Raw block and item; assume not vanilla.
        if (!(ignoreStone || ignoreNether))
        {
            // Make this code easier to read, PLEASE..
            Block b = set.getRawOre().getCoupling().getBlock().get();
            Item i  = set.getRawOre().getRawItem().get();
            String mat = material.get();

            // Readable block String:
            String rawMineral = material.isGem() ? "" : "Raw ";
            String readableBlockMat = "Block of " + mat.substring(0, 1).toUpperCase() + mat.substring(1);
            add(b, rawMineral + readableBlockMat);

            // Readable item String:
            String readableMat = mat.substring(0, 1).toUpperCase() + mat.substring(1) +
                    (material.isGem() ? "" : " Chunk");
            add(i, readableMat);
        }
    }

    // Ore set String subroutine
    protected String generateOreBlockString(BaseStone s, String readable)
    {
        // OPTIONAL, BUT PREFERRED. Defaults to Stone string otherwise.
        Map<BaseStone, String> genFormulae = new HashMap<>();
        genFormulae.put(BaseStone.ANDESITE, readable + " (Andesite)");
        genFormulae.put(BaseStone.DIORITE, readable + " (Diorite)");
        genFormulae.put(BaseStone.GRANITE, readable + " (Granite)");
        genFormulae.put(BaseStone.CALCITE, readable + " (Calcite)");
        genFormulae.put(BaseStone.SMSAST, readable + " (Smooth Sandstone)");
        genFormulae.put(BaseStone.SMRSAST, readable + " (Smooth Red Sandstone)");
        genFormulae.put(BaseStone.DEEPSLATE, "Deepslate " + readable);
        genFormulae.put(BaseStone.TUFF, readable + " (Tuff)");
        genFormulae.put(BaseStone.DRIPSTONE, readable + " (Dripstone)");
        genFormulae.put(BaseStone.NETHERRACK, "Nether " + readable);
        genFormulae.put(BaseStone.BASALT, readable + " (Basalt)");
        genFormulae.put(BaseStone.ENDSTONE, "Ender " + readable);

        return genFormulae.getOrDefault(s, readable);
    }
}
