package com.github.peeftube.spiromodnext.core;

import com.google.common.collect.Multimap;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.TierSortingRegistry;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

// Ported from old mod. Will require restructuring changes for new mod!

public class TierMod
{
    public static void fire()
    {
        // Modify vanilla tiers.
        Tiers.GOLD.uses = 580; Tiers.GOLD.level = 3;
        Tiers.DIAMOND.level = 4;
        Tiers.NETHERITE.level = 5;

        Items.GOLDEN_AXE.maxDamage = Tiers.GOLD.uses;
        Items.GOLDEN_HOE.maxDamage = Tiers.GOLD.uses;
        Items.GOLDEN_PICKAXE.maxDamage = Tiers.GOLD.uses;
        Items.GOLDEN_SWORD.maxDamage = Tiers.GOLD.uses;
        Items.GOLDEN_SHOVEL.maxDamage = Tiers.GOLD.uses;

        // Modify armors.
        ArmorMaterials.CHAIN.durabilityMultiplier = 17;
        ArmorMaterials.IRON.durabilityMultiplier = 18;
        ArmorMaterials.GOLD.durabilityMultiplier = 22;

        ArmorMaterials.GOLD.protectionFunctionForType = Util.make(new EnumMap<>(ArmorItem.Type.class), p_266649_ -> {
            p_266649_.put(ArmorItem.Type.BOOTS, 2);
            p_266649_.put(ArmorItem.Type.LEGGINGS, 6);
            p_266649_.put(ArmorItem.Type.CHESTPLATE, 7);
            p_266649_.put(ArmorItem.Type.HELMET, 2);});

        Items.IRON_HELMET.maxDamage = ArmorMaterials.IRON.getDurabilityForType(ArmorItem.Type.HELMET);
        Items.IRON_CHESTPLATE.maxDamage = ArmorMaterials.IRON.getDurabilityForType(ArmorItem.Type.CHESTPLATE);
        Items.IRON_LEGGINGS.maxDamage = ArmorMaterials.IRON.getDurabilityForType(ArmorItem.Type.LEGGINGS);
        Items.IRON_BOOTS.maxDamage = ArmorMaterials.IRON.getDurabilityForType(ArmorItem.Type.BOOTS);

        Items.CHAINMAIL_HELMET.maxDamage = ArmorMaterials.CHAIN.getDurabilityForType(ArmorItem.Type.HELMET);
        Items.CHAINMAIL_CHESTPLATE.maxDamage = ArmorMaterials.CHAIN.getDurabilityForType(ArmorItem.Type.CHESTPLATE);
        Items.CHAINMAIL_LEGGINGS.maxDamage = ArmorMaterials.CHAIN.getDurabilityForType(ArmorItem.Type.LEGGINGS);
        Items.CHAINMAIL_BOOTS.maxDamage = ArmorMaterials.CHAIN.getDurabilityForType(ArmorItem.Type.BOOTS);

        Items.GOLDEN_HELMET.maxDamage = ArmorMaterials.GOLD.getDurabilityForType(ArmorItem.Type.HELMET);
        Items.GOLDEN_CHESTPLATE.maxDamage = ArmorMaterials.GOLD.getDurabilityForType(ArmorItem.Type.CHESTPLATE);
        Items.GOLDEN_LEGGINGS.maxDamage = ArmorMaterials.GOLD.getDurabilityForType(ArmorItem.Type.LEGGINGS);
        Items.GOLDEN_BOOTS.maxDamage = ArmorMaterials.GOLD.getDurabilityForType(ArmorItem.Type.BOOTS);

        Class sortReg = TierSortingRegistry.class;
        try
        {
            Field edgesF = sortReg.getDeclaredField("edges");
            edgesF.setAccessible(true);

            Multimap<ResourceLocation, ResourceLocation> edges = (Multimap<ResourceLocation, ResourceLocation>) edgesF.get(edgesF);
            edges.clear();

            var wood = new ResourceLocation("wood");
            var stone = new ResourceLocation("stone");
            var iron = new ResourceLocation("iron");
            var diamond = new ResourceLocation("diamond");
            var netherite = new ResourceLocation("netherite");
            var gold = new ResourceLocation("gold");

            // PRIMITIVE AGE
            processTier(edges, Tiers.WOOD, wood, List.of(), List.of(stone));

            // STONE AGE
            processTier(edges, Tiers.STONE, stone, List.of(wood), List.of(iron));

            // BRONZE AGE
            // Early metals and first alloys: brass and bronze

            // IRON AGE
            processTier(edges, Tiers.IRON, iron, List.of(stone), List.of(gold));
            processTier(edges, Tiers.GOLD, gold, List.of(iron), List.of(diamond));

            // MEDIEVAL AGE
            // Steel, platinum, and aurichalcum

            // EARLY INDUSTRIAL AGE
            // Cobalt, nickel, tungsten
            // Other various elements

            // INDUSTRIAL AGE
            // Uranium, titanium, chromium, palladium, silicon, aluminum
            // Other various elements

            // ATOMIC AGE
            // Plutonium, thorium
            // Other various elements

            // ??? AGE
            processTier(edges, Tiers.DIAMOND, diamond, List.of(gold), List.of(netherite));
            processTier(edges, Tiers.NETHERITE, netherite, List.of(diamond), List.of());
        }
        catch (NoSuchFieldException | SecurityException |
               IllegalArgumentException | IllegalAccessException e) { e.printStackTrace(); }
    }

    private static void processTier(Multimap<ResourceLocation, ResourceLocation> edges, Tier tier, ResourceLocation name, List<Object> afters, List<Object> befores)
    {
        for(Object after : afters)
        {
            ResourceLocation other = getTierName(after);
            edges.put(other, name);
        }
        for(Object before : befores)
        {
            ResourceLocation other = getTierName(before);
            edges.put(name, other);
        }
    }

    private static ResourceLocation getTierName(Object entry)
    {
        if (entry instanceof String s)
            return new ResourceLocation(s);
        if (entry instanceof ResourceLocation rl)
            return rl;
        if (entry instanceof Tier t)
            return Objects.requireNonNull(TierSortingRegistry.getName(t), "Can't have sorting dependencies for tiers not registered in the TierSortingRegistry");
        throw new IllegalStateException("Invalid object type passed into the tier dependencies " + entry.getClass());
    }
}
