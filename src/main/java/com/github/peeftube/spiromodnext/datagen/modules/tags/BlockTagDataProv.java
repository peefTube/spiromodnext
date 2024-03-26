package com.github.peeftube.spiromodnext.datagen.modules.tags;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.Registry;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodnext.util.ore.Coupling;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class BlockTagDataProv extends BlockTagsProvider
{
    public BlockTagDataProv(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper eFH)
    { super(output, lookup, SpiroMod.MOD_ID, eFH); }

    @Override
    protected void addTags(HolderLookup.Provider lookup)
    {
        // Ores
        oreTags(Registry.COAL_ORES);
        oreTags(Registry.IRON_ORES);
        oreTags(Registry.COPPER_ORES);
        oreTags(Registry.GOLD_ORES);
        oreTags(Registry.LAPIS_ORES);
        oreTags(Registry.REDSTONE_ORES);
        oreTags(Registry.EMERALD_ORES);
        oreTags(Registry.DIAMOND_ORES);
        oreTags(Registry.QUARTZ_ORES);
        oreTags(Registry.RUBY_ORES);
    }

    private void oreTags(OreCollection set)
    {
        TagKey<Block> tag = set.getOreTag();
        for (Coupling c : set.getBulkData().values())
        { tag(tag).add(c.getBlock().get()); }
    }
}
