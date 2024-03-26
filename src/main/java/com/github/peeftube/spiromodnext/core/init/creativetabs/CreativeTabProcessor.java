package com.github.peeftube.spiromodnext.core.init.creativetabs;

import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodnext.util.ore.Coupling;
import net.minecraft.world.item.ItemStack;

import java.util.LinkedHashSet;
import java.util.Set;

public class CreativeTabProcessor
{
    public static Set<ItemStack> precacheMineralsTab()
    {
        Set<ItemStack> output = new LinkedHashSet<>();

        // Run over all ore sets.
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS)
        {
            for (Coupling c : ore.getBulkData().values())
            {
                ItemStack iS = c.getItem().get().getDefaultInstance();
                output.add(iS);
            }

            ItemStack iSBlock = ore.getRawOre().getCoupling().getItem().get().getDefaultInstance();
            ItemStack iSItem = ore.getRawOre().getRawItem().get().getDefaultInstance();
            output.add(iSBlock); output.add(iSItem);
        }

        return output;
    }
}
