package com.github.peeftube.spiromodnext.datagen;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.datagen.modules.BlockstateDataProv;
import com.github.peeftube.spiromodnext.datagen.modules.ItemModelDataProv;
import com.github.peeftube.spiromodnext.datagen.modules.lang.EN_USLangDataProv;
import com.github.peeftube.spiromodnext.datagen.modules.loot.LootTableDataProv;
import com.github.peeftube.spiromodnext.datagen.modules.recipe.RecipeDataProv;
import com.github.peeftube.spiromodnext.datagen.modules.tags.BlockTagDataProv;
import com.github.peeftube.spiromodnext.datagen.modules.tags.ItemTagDataProv;
import com.github.peeftube.spiromodnext.datagen.modules.world.WorldgenDataProv;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;

@Mod.EventBusSubscriber(modid = SpiroMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Datagen
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator      generator          = event.getGenerator();
        PackOutput         output             = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        BlockTagDataProv bTags = new BlockTagDataProv(output, event.getLookupProvider(), existingFileHelper);
        generator.addProvider(true, bTags);
        generator.addProvider(true, new ItemTagDataProv(output, event.getLookupProvider(),
                bTags.contentsGetter(), existingFileHelper));
        // generator.addProvider(true, new SMBiomeTagProv(output, event.getLookupProvider(), existingFileHelper));

        generator.addProvider(true, new RecipeDataProv(output, event.getLookupProvider()));
        generator.addProvider(true, new BlockstateDataProv(output, existingFileHelper));
        generator.addProvider(true, new ItemModelDataProv(output, existingFileHelper));
        // generator.addProvider(true, new SMLootModProv(output));
        generator.addProvider(true, new LootTableDataProv(output));

        // Language providers.
        generator.addProvider(true, new EN_USLangDataProv(output, "en_us"));

        generator.addProvider(true, new WorldgenDataProv(output,
                event.getLookupProvider(), Set.of(SpiroMod.MOD_ID, "minecraft")));
    }
}
