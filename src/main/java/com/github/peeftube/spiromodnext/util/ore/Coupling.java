package com.github.peeftube.spiromodnext.util.ore;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record Coupling(Supplier<Block> block, Supplier<Item> item)
{
    public static List<Coupling> COUPLINGS = new ArrayList<>();

    public static Coupling couple(Supplier<Block> b, Supplier<Item> i)
    { Coupling c = new Coupling(b, i); COUPLINGS.add(c); return c; }

    public Supplier<Block> getBlock()
    { return block; }

    public Supplier<Item> getItem()
    { return item; }
}
