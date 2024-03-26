package com.github.peeftube.spiromodnext.util;

import com.github.peeftube.spiromodnext.SpiroMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class SpiroTags
{
    public static class Blocks
    {
        public static TagKey<Block> tag(String name) { return BlockTags.create(new ResourceLocation(SpiroMod.MOD_ID, name)); }
        public static TagKey<Block> forgeTag(String name) { return BlockTags.create(new ResourceLocation("forge", name)); }
    }

    public static class Items
    {
        public static TagKey<Item> tag(String name) { return ItemTags.create(new ResourceLocation(SpiroMod.MOD_ID, name)); }
        public static TagKey<Item> forgeTag(String name) { return ItemTags.create(new ResourceLocation("forge", name)); }
    }
}
