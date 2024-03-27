package com.github.peeftube.spiromodnext.util.ore;

import com.github.peeftube.spiromodnext.util.SpiroTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

public enum PrereqTier
{
    NONE(null), WOOD(Tags.Blocks.NEEDS_WOOD_TOOL), FLINT(SpiroTags.Blocks.NEEDS_FLINT_TOOL),
    STONE(BlockTags.NEEDS_STONE_TOOL), COPPER(SpiroTags.Blocks.NEEDS_COPPER_TOOL), IRON(BlockTags.NEEDS_IRON_TOOL),
    GOLD(Tags.Blocks.NEEDS_GOLD_TOOL), DIAMOND(BlockTags.NEEDS_DIAMOND_TOOL), NETHERITE(Tags.Blocks.NEEDS_NETHERITE_TOOL);

    private final TagKey<Block> associatedTierTag;

    PrereqTier(TagKey<Block> associatedTierTag)
    { this.associatedTierTag = associatedTierTag; }

    public TagKey<Block> getATT()
    { return associatedTierTag; }
}
