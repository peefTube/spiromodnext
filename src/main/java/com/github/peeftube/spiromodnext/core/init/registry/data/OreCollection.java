package com.github.peeftube.spiromodnext.core.init.registry.data;

import com.github.peeftube.spiromodnext.core.init.Registrar;
import com.github.peeftube.spiromodnext.util.MinMax;
import com.github.peeftube.spiromodnext.util.SpiroTags;
import com.github.peeftube.spiromodnext.util.ore.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public record OreCollection(OreMaterial material, Map<BaseStone, Coupling> bulkData,
                            RawCoupling rawOreCoupling, TagKey<Block> oreTag, PrereqTier neededTier,
                            NumberProvider oreDropData)
        implements OreUtilities
{
    public static List<OreCollection> ORE_COLLECTIONS = new ArrayList<>();

    public static OreCollection registerCollection(OreMaterial material)
    { return registerCollection(material, PrereqTier.NONE); }

    public static OreCollection registerCollection(OreMaterial material, int li)
    { return registerCollection(material, li, PrereqTier.NONE); }

    public static OreCollection registerCollection(OreMaterial material, MinMax minMax)
    { return registerCollection(material, minMax, PrereqTier.NONE); }

    public static OreCollection registerCollection(OreMaterial material, PrereqTier neededTier)
    { return registerCollection(material, 0, new MinMax(1, 1), neededTier); }

    public static OreCollection registerCollection(OreMaterial material, int li, PrereqTier neededTier)
    { return registerCollection(material, li, new MinMax(1, 1), neededTier); }

    public static OreCollection registerCollection(OreMaterial material, MinMax minMax, PrereqTier neededTier)
    { return registerCollection(material, 0, minMax, neededTier); }

    public static OreCollection registerCollection(OreMaterial material, int lightEmissionLevel, MinMax minMax,
            PrereqTier neededTier)
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

        NumberProvider oreDrops = (MinMax.getMin() == MinMax.getMax()) ? ConstantValue.exactly(MinMax.getMin()) :
                UniformGenerator.between(MinMax.getMin(), MinMax.getMax());

        OreCollection collection = new OreCollection(material, mappings, OreUtilities.determineRawOre(material, li),
                tag, neededTier, oreDrops);
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

        block = Registrar.regBlock(b.get() + m, block);
        Supplier<Item>  item  = Registrar.regSimpleBlockItem((DeferredBlock<Block>) block);

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

    public NumberProvider getDropCount()
    { return oreDropData; }

    public PrereqTier getPrerequisiteTier()
    { return neededTier; }
}
