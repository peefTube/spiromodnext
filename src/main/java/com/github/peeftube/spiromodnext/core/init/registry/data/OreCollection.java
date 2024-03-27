package com.github.peeftube.spiromodnext.core.init.registry.data;

import com.github.peeftube.spiromodnext.core.init.Registry;
import com.github.peeftube.spiromodnext.util.SpiroTags;
import com.github.peeftube.spiromodnext.util.ore.BaseStone;
import com.github.peeftube.spiromodnext.util.ore.Coupling;
import com.github.peeftube.spiromodnext.util.ore.OreUtilities;
import com.github.peeftube.spiromodnext.util.ore.RawCoupling;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public record OreCollection(OreMaterial material, Map<BaseStone, Coupling> bulkData,
                            RawCoupling rawOreCoupling, TagKey<Block> oreTag) implements OreUtilities
{
    public static List<OreCollection> ORE_COLLECTIONS = new ArrayList<>();

    public static OreCollection registerCollection(OreMaterial material)
    { return registerCollection(material, 0); }

    public static OreCollection registerCollection(OreMaterial material, int lightEmissionLevel)
    {
        String oreName = material.get() + "_ore";
        int li = lightEmissionLevel;

        // Set up the map.
        Map<BaseStone, Coupling> mappings = new HashMap<>();

        for(BaseStone s : BaseStone.values())
        {
            switch(s)
            {
                case STONE, DEEPSLATE ->
                {
                    switch(material)
                    {
                        case COAL, IRON, COPPER, GOLD, LAPIS, REDSTONE, EMERALD, DIAMOND ->
                            { mappings.put(s, findPreset(s, material)); }
                        default -> { mappings.put(s, createNew(s, oreName, li)); }
                    }
                }
                case NETHERRACK ->
                {
                    switch(material)
                    {
                        case GOLD, QUARTZ ->
                        { mappings.put(s, findPreset(s, material)); }
                        default -> { mappings.put(s, createNew(s, oreName, li)); }
                    }
                }
                default -> { mappings.put(s, createNew(s, oreName, li)); }
            }
        }
        TagKey<Block> tag = SpiroTags.Blocks.tag("spiro_" + material.get() + "_ore");

        OreCollection collection = new OreCollection(material, mappings, OreUtilities.determineRawOre(material, li), tag);
        ORE_COLLECTIONS.add(collection); return collection;
    }

   /* This is only ever called on vanilla blocks.
    * Don't be stupid and try to use this with other use cases unless
    * you insist upon giving yourself a headache.
    * That or you're a dense masochist. Choice is yours, just don't cry to me about it.
    * - spiro9 - */
    private static Coupling findPreset(BaseStone b, OreMaterial m)
    {
        // Easy!
        Map<BaseStone, Map<OreMaterial, Coupling>> reference = OreUtilities.getComboPresets();
        return reference.get(b).get(m);
    }

    private static Coupling createNew(BaseStone b, String m, int li)
    {
        Supplier<Block> block;

        // Quick redstone catch case
        if (m.toLowerCase().contains("redstone"))
        { block = () -> new RedStoneOreBlock(b.getProps().noOcclusion().lightLevel(s -> li)); }
        else { block = () -> new Block(b.getProps().noOcclusion().lightLevel(s -> li)); }

        block = Registry.regBlock(b.get() + m, block);
        Supplier<Item>  item  = Registry.regSimpleBlockItem((DeferredBlock<Block>) block);

        return new Coupling(block, item);
    }

    public OreMaterial getMat()
    { return this.material; }

    public Map<BaseStone, Coupling> getBulkData()
    { return bulkData; }
    
    public RawCoupling getRawOre()
    { return rawOreCoupling; }

    public TagKey<Block> getOreTag()
    { return oreTag; }
}
