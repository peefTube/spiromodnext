package com.github.peeftube.spiromodnext.core.init.registry.data;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum OreMaterial
{
    // Vanilla.
    COAL("coal", false, BlockTags.COAL_ORES, null),
    IRON("iron", false, BlockTags.IRON_ORES, () -> Items.IRON_INGOT),
    COPPER("copper", false, BlockTags.COPPER_ORES, () -> Items.COPPER_INGOT),
    GOLD("gold", false, BlockTags.GOLD_ORES, () -> Items.GOLD_INGOT),
    LAPIS("lapis", true, BlockTags.LAPIS_ORES, null),
    REDSTONE("redstone", true, BlockTags.REDSTONE_ORES, null),
    EMERALD("emerald", true, BlockTags.EMERALD_ORES, null),
    DIAMOND("diamond", true, BlockTags.DIAMOND_ORES, null),
    QUARTZ("quartz", true, null, null),

    // Modded.
    RUBY("ruby", true, null, null);

    private final String name;

    // NOTE: May not actually be a gem, this just "asks" whether the material behaves similarly.
    private final boolean isGem;

    // Associated block tag; this is for vanilla ores but can be extended to Forge ores as well if needed
    private final TagKey<Block> associatedOreTag;

    // Associated ingot to smelt to, if one exists; if this is a "high-yield gem" which outputs raw ores, use that instead
    private final Supplier<Item> ingotConvertible;

    OreMaterial(String name, boolean isGem, TagKey<Block> associatedOreTag, Supplier<Item> ingotConvertible)
    { this.name = name; this.isGem = isGem;
        this.associatedOreTag = associatedOreTag; this.ingotConvertible = ingotConvertible; }

    public String get()
    { return name; }

    public boolean isGem()
    { return isGem; }

    public TagKey<Block> getAOT()
    { return associatedOreTag; }

    public Supplier<Item> getIngotConvertible()
    { return ingotConvertible; }
}
