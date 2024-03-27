package com.github.peeftube.spiromodnext.util.ore;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record OreTagCoupling(TagKey<Block> blockTag, TagKey<Item> itemTag)
{
    public static List<OreTagCoupling> ORE_TAG_COUPLINGS = new ArrayList<>();

    public static OreTagCoupling couple(TagKey<Block> b, TagKey<Item> i)
    { OreTagCoupling c = new OreTagCoupling(b, i); ORE_TAG_COUPLINGS.add(c); return c; }

    public TagKey<Block> getBlockTag()
    { return blockTag; }

    public TagKey<Item> getItemTag()
    { return itemTag; }
}
