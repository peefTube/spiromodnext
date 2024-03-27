package com.github.peeftube.spiromodnext.datagen.modules.tags;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodnext.util.ore.OreCoupling;
import com.github.peeftube.spiromodnext.util.ore.PrereqTier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class BlockTagDataProv extends BlockTagsProvider
{
    public BlockTagDataProv(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper eFH)
    { super(output, lookup, SpiroMod.MOD_ID, eFH); }

    @Override
    protected void addTags(HolderLookup.Provider lookup)
    {
        // Ores
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreTags(ore); }
    }

    private void oreTags(OreCollection set)
    {
        TagKey<Block> tag = set.getOreBT();
        TagKey<Block> prereqTag = (set.getPrerequisiteTier() == PrereqTier.NONE) ? null : set.getPrerequisiteTier().getATT();
        TagKey<Block> stockTag = set.getMat().getAOT();

        for (OreCoupling c : set.getBulkData().values())
        {
            tag(tag).add(c.getBlock().get());
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(c.getBlock().get());

            if (prereqTag != null) { tag(prereqTag).add(c.getBlock().get()); }
        }

        Block b = set.getRawOre().getCoupling().getBlock().get();
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(b);
        if (prereqTag != null) { tag(prereqTag).add(b); }

        if (stockTag != null) { tag(stockTag).addTag(tag); }
    }
}
