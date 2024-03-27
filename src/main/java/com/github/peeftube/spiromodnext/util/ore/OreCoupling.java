package com.github.peeftube.spiromodnext.util.ore;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record OreCoupling(Supplier<Block> block, Supplier<Item> item)
{
    public static List<OreCoupling> ORE_COUPLINGS = new ArrayList<>();

    public static OreCoupling couple(Supplier<Block> b, Supplier<Item> i)
    { OreCoupling c = new OreCoupling(b, i); ORE_COUPLINGS.add(c); return c; }

    public Supplier<Block> getBlock()
    { return block; }

    public Supplier<Item> getItem()
    { return item; }
}
