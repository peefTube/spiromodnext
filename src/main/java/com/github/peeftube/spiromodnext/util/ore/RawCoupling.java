package com.github.peeftube.spiromodnext.util.ore;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record RawCoupling(Coupling c, Supplier<Item> i)
{
    public static List<RawCoupling> COUPLINGS = new ArrayList<>();

    public static RawCoupling couple(Coupling c, Supplier<Item> i)
    { RawCoupling r = new RawCoupling(c, i); COUPLINGS.add(r); return r; }

    public Coupling getCoupling()
    { return c; }

    public Supplier<Item> getRawItem()
    { return i; }
}
