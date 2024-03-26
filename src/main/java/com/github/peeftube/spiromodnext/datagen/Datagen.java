package com.github.peeftube.spiromodnext.datagen;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.datagen.modules.BlockstateDataProv;
import com.github.peeftube.spiromodnext.datagen.modules.ItemModelDataProv;
import com.github.peeftube.spiromodnext.datagen.modules.lang.EN_USLangDataProv;
import com.github.peeftube.spiromodnext.datagen.modules.tags.BlockTagDataProv;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = SpiroMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Datagen
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator      generator          = event.getGenerator();
        PackOutput         output             = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new BlockTagDataProv(output, event.getLookupProvider(), existingFileHelper));
        // generator.addProvider(true, new SMItemTagProv(output, event.getLookupProvider(), blockTags, existingFileHelper));
        // generator.addProvider(true, new SMBiomeTagProv(output, event.getLookupProvider(), existingFileHelper));

        // generator.addProvider(true, new SMRecipeProv(output));
        generator.addProvider(true, new BlockstateDataProv(output, existingFileHelper));
        generator.addProvider(true, new ItemModelDataProv(output, existingFileHelper));
        // generator.addProvider(true, new SMLootModProv(output));
        // generator.addProvider(true, new SMLootTableProv(output));

        // Language providers.
        generator.addProvider(true, new EN_USLangDataProv(output, "en_us"));

        // generator.addProvider(true, new SMWorldgenDataProv(output,
        //        event.getLookupProvider(), Set.of(SpiroMod.MOD_ID, "minecraft")));
    }
}
