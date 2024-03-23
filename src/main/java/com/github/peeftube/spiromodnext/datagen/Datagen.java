package com.github.peeftube.spiromodnext.datagen;

import com.github.peeftube.spiromodnext.SpiroMod;
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

        // SMBlockTagProv blockTags = new SMBlockTagProv(output, event.getLookupProvider(), existingFileHelper);
        // generator.addProvider(true, blockTags);
        // generator.addProvider(true, new SMItemTagProv(output, event.getLookupProvider(), blockTags, existingFileHelper));
        // generator.addProvider(true, new SMBiomeTagProv(output, event.getLookupProvider(), existingFileHelper));

        // generator.addProvider(true, new SMRecipeProv(output));
        generator.addProvider(true, new BlockstateDataProv(output, existingFileHelper));
        // generator.addProvider(true, new SMItemModelProv(output, existingFileHelper));
        // generator.addProvider(true, new SMLangProv(output, "en_us"));
        // generator.addProvider(true, new SMLootModProv(output));
        // generator.addProvider(true, new SMLootTableProv(output));

        // generator.addProvider(true, new SMWorldgenDataProv(output,
        //        event.getLookupProvider(), Set.of(SpiroMod.MOD_ID, "minecraft")));
    }
}
