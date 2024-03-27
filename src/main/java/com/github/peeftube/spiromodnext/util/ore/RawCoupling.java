package com.github.peeftube.spiromodnext.util.ore;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record RawCoupling(OreCoupling c, Supplier<Item> i)
{
    public static List<RawCoupling> COUPLINGS = new ArrayList<>();

    public static RawCoupling couple(OreCoupling c, Supplier<Item> i)
    { RawCoupling r = new RawCoupling(c, i); COUPLINGS.add(r); return r; }

    public OreCoupling getCoupling()
    { return c; }

    public Supplier<Item> getRawItem()
    { return i; }
}
