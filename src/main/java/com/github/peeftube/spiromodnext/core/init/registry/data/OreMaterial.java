package com.github.peeftube.spiromodnext.core.init.registry.data;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public enum OreMaterial
{
    // Vanilla.
    COAL("coal", false, BlockTags.COAL_ORES),
    IRON("iron", false, BlockTags.IRON_ORES),
    COPPER("copper", false, BlockTags.COPPER_ORES),
    GOLD("gold", false, BlockTags.GOLD_ORES),
    LAPIS("lapis", true, BlockTags.LAPIS_ORES),
    REDSTONE("redstone", true, BlockTags.REDSTONE_ORES),
    EMERALD("emerald", true, BlockTags.EMERALD_ORES),
    DIAMOND("diamond", true, BlockTags.DIAMOND_ORES),
    QUARTZ("quartz", true, null),

    // Modded.
    RUBY("ruby", true, null);

    private final String name;

    // NOTE: May not actually be a gem, this just "asks" whether the material behaves similarly.
    private final boolean isGem;

    // Associated block tag; this is for vanilla ores but can be extended to Forge ores as well if needed
    private final TagKey<Block> associatedOreTag;

    OreMaterial(String name, boolean isGem, TagKey<Block> associatedOreTag)
    { this.name = name; this.isGem = isGem; this.associatedOreTag = associatedOreTag; }

    public String get()
    { return name; }

    public boolean isGem()
    { return isGem; }

    public TagKey<Block> getAOT()
    { return associatedOreTag; }
}
