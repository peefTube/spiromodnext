package com.github.peeftube.spiromodnext.datagen.modules.tags;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodnext.util.ore.OreCoupling;
import com.github.peeftube.spiromodnext.util.ore.PrereqTier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ItemTagDataProv extends ItemTagsProvider
{
    public ItemTagDataProv(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup,
            CompletableFuture<TagLookup<Block>> bLookup, ExistingFileHelper eFH)
    { super(output, lookup, bLookup, SpiroMod.MOD_ID, eFH); }

    @Override
    protected void addTags(HolderLookup.Provider lookup)
    {
        // Ores
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreTags(ore); }
    }

    private void oreTags(OreCollection set)
    {
        TagKey<Item> tag       = set.getOreIT();

        for (OreCoupling c : set.getBulkData().values())
        { tag(tag).add(c.getItem().get()); }
    }
}
