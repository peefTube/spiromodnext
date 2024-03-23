package com.github.peeftube.spiromodnext.core.init.registry.data;

import com.github.peeftube.spiromodnext.core.init.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.github.peeftube.spiromodnext.core.init.Registry.*;

public record OreCollection(String name,
                            Supplier<Block> defaultOre, Supplier<Item> defaultItem,
                            Supplier<Block> andesiteOre, Supplier<Item> andesiteItem,
                            Supplier<Block> dioriteOre, Supplier<Item> dioriteItem,
                            Supplier<Block> graniteOre, Supplier<Item> graniteItem,
                            Supplier<Block> calciteOre, Supplier<Item> calciteItem,
                            Supplier<Block> smtSaStOre, Supplier<Item> smtSaStItem,
                            Supplier<Block> smtRedSaStOre, Supplier<Item> smtRedSaStItem,
                            Supplier<Block> deepslateOre, Supplier<Item> deepslateItem,
                            Supplier<Block> tuffOre, Supplier<Item> tuffItem,
                            Supplier<Block> dripstoneOre, Supplier<Item> dripstoneItem,
                            Supplier<Block> netherrackOre, Supplier<Item> netherrackItem,
                            Supplier<Block> basaltOre, Supplier<Item> basaltItem,
                            Supplier<Block> endstoneOre, Supplier<Item> endstoneItem)
{
    public static List<OreCollection> ORE_COLLECTIONS = new ArrayList<>();

    // Protected enum for internal class use.
    protected enum BaseStone
    {
        STONE(""), ANDESITE("andesite_"), DIORITE("diorite_"),
        GRANITE("granite_"), CALCITE("calcite_"),
        SMSAST("smooth_sandstone_"), SMRSAST("smooth_red_sandstone_"),
        DEEPSLATE("deepslate_"), TUFF("tuff_"), DRIPSTONE("dripstone_"),
        NETHERRACK("nether_"), BASALT("basalt_"), ENDSTONE("end_");

        private final String name;

        BaseStone(String name)
        { this.name = name; }

        public String get()
        { return name; }
    }

    public static OreCollection registerCollection(String name)
    { return registerCollection(name, 0); }

    public static OreCollection registerCollection(String name, int lightEmissionLevel)
    {
        String oreName = name + "_ore";
        int li = lightEmissionLevel;

        // Prepare all desired possible vanilla variants ahead of time
        Supplier<Block> defaultOre; Supplier<Item> defaultItem;
        Supplier<Block> andesiteOre; Supplier<Item> andesiteItem;
        Supplier<Block> dioriteOre; Supplier<Item> dioriteItem;
        Supplier<Block> graniteOre; Supplier<Item> graniteItem;
        Supplier<Block> calciteOre; Supplier<Item> calciteItem;
        Supplier<Block> smtSaStOre; Supplier<Item> smtSaStItem;
        Supplier<Block> smtRedSaStOre; Supplier<Item> smtRedSaStItem;
        Supplier<Block> deepslateOre; Supplier<Item> deepslateItem;
        Supplier<Block> tuffOre; Supplier<Item> tuffItem;
        Supplier<Block> dripstoneOre; Supplier<Item> dripstoneItem;
        Supplier<Block> netherrackOre; Supplier<Item> netherrackItem;
        Supplier<Block> basaltOre; Supplier<Item> basaltItem;
        Supplier<Block> endstoneOre; Supplier<Item> endstoneItem;

        switch (name)
        {
            case "coal" ->
            {
                defaultOre = () -> Blocks.COAL_ORE;
                defaultItem = () -> Items.COAL_ORE;
                deepslateOre = () -> Blocks.DEEPSLATE_COAL_ORE;
                deepslateItem = () -> Items.DEEPSLATE_COAL_ORE;
                andesiteOre = Registry.regBlock(BaseStone.ANDESITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                andesiteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) andesiteOre);
                dioriteOre = Registry.regBlock(BaseStone.DIORITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                dioriteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dioriteOre);
                graniteOre = Registry.regBlock(BaseStone.GRANITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                graniteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) graniteOre);
                calciteOre = Registry.regBlock(BaseStone.CALCITE.get() + oreName,
                        () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> li)));
                calciteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) calciteOre);
                smtSaStOre = Registry.regBlock(BaseStone.SMSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtSaStOre);
                smtRedSaStOre = Registry.regBlock(BaseStone.SMRSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtRedSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtRedSaStOre);
                tuffOre = Registry.regBlock(BaseStone.TUFF.get() + oreName,
                        () -> new Block(TUFF_BASED_ORE.lightLevel(s -> li)));
                tuffItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) tuffOre);
                dripstoneOre = Registry.regBlock(BaseStone.DRIPSTONE.get() + oreName,
                        () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> li)));
                dripstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dripstoneOre);
                netherrackOre = Registry.regBlock(BaseStone.NETHERRACK.get() + oreName,
                        () -> new Block(NETHER_BASED_ORE.lightLevel(s -> li)));
                netherrackItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) netherrackOre);
                basaltOre = Registry.regBlock(BaseStone.BASALT.get() + oreName,
                        () -> new Block(BASALT_BASED_ORE.lightLevel(s -> li)));
                basaltItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) basaltOre);
                endstoneOre = Registry.regBlock(BaseStone.ENDSTONE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                endstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) endstoneOre);
            }
            case "iron" ->
            {
                defaultOre = () -> Blocks.IRON_ORE;
                defaultItem = () -> Items.IRON_ORE;
                deepslateOre = () -> Blocks.DEEPSLATE_IRON_ORE;
                deepslateItem = () -> Items.DEEPSLATE_IRON_ORE;
                andesiteOre = Registry.regBlock(BaseStone.ANDESITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                andesiteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) andesiteOre);
                dioriteOre = Registry.regBlock(BaseStone.DIORITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                dioriteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dioriteOre);
                graniteOre = Registry.regBlock(BaseStone.GRANITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                graniteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) graniteOre);
                calciteOre = Registry.regBlock(BaseStone.CALCITE.get() + oreName,
                        () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> li)));
                calciteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) calciteOre);
                smtSaStOre = Registry.regBlock(BaseStone.SMSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtSaStOre);
                smtRedSaStOre = Registry.regBlock(BaseStone.SMRSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtRedSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtRedSaStOre);
                tuffOre = Registry.regBlock(BaseStone.TUFF.get() + oreName,
                        () -> new Block(TUFF_BASED_ORE.lightLevel(s -> li)));
                tuffItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) tuffOre);
                dripstoneOre = Registry.regBlock(BaseStone.DRIPSTONE.get() + oreName,
                        () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> li)));
                dripstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dripstoneOre);
                netherrackOre = Registry.regBlock(BaseStone.NETHERRACK.get() + oreName,
                        () -> new Block(NETHER_BASED_ORE.lightLevel(s -> li)));
                netherrackItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) netherrackOre);
                basaltOre = Registry.regBlock(BaseStone.BASALT.get() + oreName,
                        () -> new Block(BASALT_BASED_ORE.lightLevel(s -> li)));
                basaltItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) basaltOre);
                endstoneOre = Registry.regBlock(BaseStone.ENDSTONE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                endstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) endstoneOre);
            }
            case "copper" ->
            {
                defaultOre = () -> Blocks.COPPER_ORE;
                defaultItem = () -> Items.COPPER_ORE;
                deepslateOre = () -> Blocks.DEEPSLATE_COPPER_ORE;
                deepslateItem = () -> Items.DEEPSLATE_COPPER_ORE;
                andesiteOre = Registry.regBlock(BaseStone.ANDESITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                andesiteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) andesiteOre);
                dioriteOre = Registry.regBlock(BaseStone.DIORITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                dioriteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dioriteOre);
                graniteOre = Registry.regBlock(BaseStone.GRANITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                graniteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) graniteOre);
                calciteOre = Registry.regBlock(BaseStone.CALCITE.get() + oreName,
                        () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> li)));
                calciteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) calciteOre);
                smtSaStOre = Registry.regBlock(BaseStone.SMSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtSaStOre);
                smtRedSaStOre = Registry.regBlock(BaseStone.SMRSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtRedSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtRedSaStOre);
                tuffOre = Registry.regBlock(BaseStone.TUFF.get() + oreName,
                        () -> new Block(TUFF_BASED_ORE.lightLevel(s -> li)));
                tuffItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) tuffOre);
                dripstoneOre = Registry.regBlock(BaseStone.DRIPSTONE.get() + oreName,
                        () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> li)));
                dripstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dripstoneOre);
                netherrackOre = Registry.regBlock(BaseStone.NETHERRACK.get() + oreName,
                        () -> new Block(NETHER_BASED_ORE.lightLevel(s -> li)));
                netherrackItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) netherrackOre);
                basaltOre = Registry.regBlock(BaseStone.BASALT.get() + oreName,
                        () -> new Block(BASALT_BASED_ORE.lightLevel(s -> li)));
                basaltItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) basaltOre);
                endstoneOre = Registry.regBlock(BaseStone.ENDSTONE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                endstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) endstoneOre);
            }
            case "gold" ->
            {
                defaultOre = () -> Blocks.GOLD_ORE;
                defaultItem = () -> Items.GOLD_ORE;
                deepslateOre = () -> Blocks.DEEPSLATE_GOLD_ORE;
                deepslateItem = () -> Items.DEEPSLATE_GOLD_ORE;
                netherrackOre = () -> Blocks.NETHER_GOLD_ORE;
                netherrackItem = () -> Items.NETHER_GOLD_ORE;
                andesiteOre = Registry.regBlock(BaseStone.ANDESITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                andesiteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) andesiteOre);
                dioriteOre = Registry.regBlock(BaseStone.DIORITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                dioriteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dioriteOre);
                graniteOre = Registry.regBlock(BaseStone.GRANITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                graniteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) graniteOre);
                calciteOre = Registry.regBlock(BaseStone.CALCITE.get() + oreName,
                        () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> li)));
                calciteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) calciteOre);
                smtSaStOre = Registry.regBlock(BaseStone.SMSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtSaStOre);
                smtRedSaStOre = Registry.regBlock(BaseStone.SMRSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtRedSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtRedSaStOre);
                tuffOre = Registry.regBlock(BaseStone.TUFF.get() + oreName,
                        () -> new Block(TUFF_BASED_ORE.lightLevel(s -> li)));
                tuffItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) tuffOre);
                dripstoneOre = Registry.regBlock(BaseStone.DRIPSTONE.get() + oreName,
                        () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> li)));
                dripstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dripstoneOre);
                basaltOre = Registry.regBlock(BaseStone.BASALT.get() + oreName,
                        () -> new Block(BASALT_BASED_ORE.lightLevel(s -> li)));
                basaltItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) basaltOre);
                endstoneOre = Registry.regBlock(BaseStone.ENDSTONE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                endstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) endstoneOre);
            }
            case "lapis" ->
            {
                defaultOre = () -> Blocks.LAPIS_ORE;
                defaultItem = () -> Items.LAPIS_ORE;
                deepslateOre = () -> Blocks.DEEPSLATE_LAPIS_ORE;
                deepslateItem = () -> Items.DEEPSLATE_LAPIS_ORE;
                andesiteOre = Registry.regBlock(BaseStone.ANDESITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                andesiteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) andesiteOre);
                dioriteOre = Registry.regBlock(BaseStone.DIORITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                dioriteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dioriteOre);
                graniteOre = Registry.regBlock(BaseStone.GRANITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                graniteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) graniteOre);
                calciteOre = Registry.regBlock(BaseStone.CALCITE.get() + oreName,
                        () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> li)));
                calciteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) calciteOre);
                smtSaStOre = Registry.regBlock(BaseStone.SMSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtSaStOre);
                smtRedSaStOre = Registry.regBlock(BaseStone.SMRSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtRedSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtRedSaStOre);
                tuffOre = Registry.regBlock(BaseStone.TUFF.get() + oreName,
                        () -> new Block(TUFF_BASED_ORE.lightLevel(s -> li)));
                tuffItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) tuffOre);
                dripstoneOre = Registry.regBlock(BaseStone.DRIPSTONE.get() + oreName,
                        () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> li)));
                dripstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dripstoneOre);
                netherrackOre = Registry.regBlock(BaseStone.NETHERRACK.get() + oreName,
                        () -> new Block(NETHER_BASED_ORE.lightLevel(s -> li)));
                netherrackItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) netherrackOre);
                basaltOre = Registry.regBlock(BaseStone.BASALT.get() + oreName,
                        () -> new Block(BASALT_BASED_ORE.lightLevel(s -> li)));
                basaltItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) basaltOre);
                endstoneOre = Registry.regBlock(BaseStone.ENDSTONE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                endstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) endstoneOre);
            }
            case "redstone" ->
            {
                defaultOre = () -> Blocks.REDSTONE_ORE;
                defaultItem = () -> Items.REDSTONE_ORE;
                deepslateOre = () -> Blocks.DEEPSLATE_REDSTONE_ORE;
                deepslateItem = () -> Items.DEEPSLATE_REDSTONE_ORE;
                andesiteOre = Registry.regBlock(BaseStone.ANDESITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                andesiteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) andesiteOre);
                dioriteOre = Registry.regBlock(BaseStone.DIORITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                dioriteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dioriteOre);
                graniteOre = Registry.regBlock(BaseStone.GRANITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                graniteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) graniteOre);
                calciteOre = Registry.regBlock(BaseStone.CALCITE.get() + oreName,
                        () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> li)));
                calciteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) calciteOre);
                smtSaStOre = Registry.regBlock(BaseStone.SMSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtSaStOre);
                smtRedSaStOre = Registry.regBlock(BaseStone.SMRSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtRedSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtRedSaStOre);
                tuffOre = Registry.regBlock(BaseStone.TUFF.get() + oreName,
                        () -> new Block(TUFF_BASED_ORE.lightLevel(s -> li)));
                tuffItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) tuffOre);
                dripstoneOre = Registry.regBlock(BaseStone.DRIPSTONE.get() + oreName,
                        () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> li)));
                dripstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dripstoneOre);
                netherrackOre = Registry.regBlock(BaseStone.NETHERRACK.get() + oreName,
                        () -> new Block(NETHER_BASED_ORE.lightLevel(s -> li)));
                netherrackItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) netherrackOre);
                basaltOre = Registry.regBlock(BaseStone.BASALT.get() + oreName,
                        () -> new Block(BASALT_BASED_ORE.lightLevel(s -> li)));
                basaltItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) basaltOre);
                endstoneOre = Registry.regBlock(BaseStone.ENDSTONE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                endstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) endstoneOre);
            }
            case "diamond" ->
            {
                defaultOre = () -> Blocks.DIAMOND_ORE;
                defaultItem = () -> Items.DIAMOND_ORE;
                deepslateOre = () -> Blocks.DEEPSLATE_DIAMOND_ORE;
                deepslateItem = () -> Items.DEEPSLATE_DIAMOND_ORE;
                andesiteOre = Registry.regBlock(BaseStone.ANDESITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                andesiteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) andesiteOre);
                dioriteOre = Registry.regBlock(BaseStone.DIORITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                dioriteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dioriteOre);
                graniteOre = Registry.regBlock(BaseStone.GRANITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                graniteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) graniteOre);
                calciteOre = Registry.regBlock(BaseStone.CALCITE.get() + oreName,
                        () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> li)));
                calciteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) calciteOre);
                smtSaStOre = Registry.regBlock(BaseStone.SMSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtSaStOre);
                smtRedSaStOre = Registry.regBlock(BaseStone.SMRSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtRedSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtRedSaStOre);
                tuffOre = Registry.regBlock(BaseStone.TUFF.get() + oreName,
                        () -> new Block(TUFF_BASED_ORE.lightLevel(s -> li)));
                tuffItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) tuffOre);
                dripstoneOre = Registry.regBlock(BaseStone.DRIPSTONE.get() + oreName,
                        () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> li)));
                dripstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dripstoneOre);
                netherrackOre = Registry.regBlock(BaseStone.NETHERRACK.get() + oreName,
                        () -> new Block(NETHER_BASED_ORE.lightLevel(s -> li)));
                netherrackItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) netherrackOre);
                basaltOre = Registry.regBlock(BaseStone.BASALT.get() + oreName,
                        () -> new Block(BASALT_BASED_ORE.lightLevel(s -> li)));
                basaltItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) basaltOre);
                endstoneOre = Registry.regBlock(BaseStone.ENDSTONE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                endstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) endstoneOre);
            }
            case "emerald" ->
            {
                defaultOre = () -> Blocks.EMERALD_ORE;
                defaultItem = () -> Items.EMERALD_ORE;
                deepslateOre = () -> Blocks.DEEPSLATE_EMERALD_ORE;
                deepslateItem = () -> Items.DEEPSLATE_EMERALD_ORE;
                andesiteOre = Registry.regBlock(BaseStone.ANDESITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                andesiteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) andesiteOre);
                dioriteOre = Registry.regBlock(BaseStone.DIORITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                dioriteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dioriteOre);
                graniteOre = Registry.regBlock(BaseStone.GRANITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                graniteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) graniteOre);
                calciteOre = Registry.regBlock(BaseStone.CALCITE.get() + oreName,
                        () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> li)));
                calciteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) calciteOre);
                smtSaStOre = Registry.regBlock(BaseStone.SMSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtSaStOre);
                smtRedSaStOre = Registry.regBlock(BaseStone.SMRSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtRedSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtRedSaStOre);
                tuffOre = Registry.regBlock(BaseStone.TUFF.get() + oreName,
                        () -> new Block(TUFF_BASED_ORE.lightLevel(s -> li)));
                tuffItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) tuffOre);
                dripstoneOre = Registry.regBlock(BaseStone.DRIPSTONE.get() + oreName,
                        () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> li)));
                dripstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dripstoneOre);
                netherrackOre = Registry.regBlock(BaseStone.NETHERRACK.get() + oreName,
                        () -> new Block(NETHER_BASED_ORE.lightLevel(s -> li)));
                netherrackItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) netherrackOre);
                basaltOre = Registry.regBlock(BaseStone.BASALT.get() + oreName,
                        () -> new Block(BASALT_BASED_ORE.lightLevel(s -> li)));
                basaltItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) basaltOre);
                endstoneOre = Registry.regBlock(BaseStone.ENDSTONE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                endstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) endstoneOre);
            }
            case "quartz" ->
            {
                netherrackOre = () -> Blocks.NETHER_QUARTZ_ORE;
                netherrackItem = () -> Items.NETHER_QUARTZ_ORE;
                defaultOre = Registry.regBlock(BaseStone.STONE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                defaultItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) defaultOre);
                andesiteOre = Registry.regBlock(BaseStone.ANDESITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                andesiteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) andesiteOre);
                dioriteOre = Registry.regBlock(BaseStone.DIORITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                dioriteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dioriteOre);
                graniteOre = Registry.regBlock(BaseStone.GRANITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                graniteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) graniteOre);
                calciteOre = Registry.regBlock(BaseStone.CALCITE.get() + oreName,
                        () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> li)));
                calciteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) calciteOre);
                smtSaStOre = Registry.regBlock(BaseStone.SMSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtSaStOre);
                smtRedSaStOre = Registry.regBlock(BaseStone.SMRSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtRedSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtRedSaStOre);
                deepslateOre = Registry.regBlock(BaseStone.DEEPSLATE.get() + oreName,
                        () -> new Block(DEEPSLATE_BASED_ORE.lightLevel(s -> li)));
                deepslateItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) deepslateOre);
                tuffOre = Registry.regBlock(BaseStone.TUFF.get() + oreName,
                        () -> new Block(TUFF_BASED_ORE.lightLevel(s -> li)));
                tuffItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) tuffOre);
                dripstoneOre = Registry.regBlock(BaseStone.DRIPSTONE.get() + oreName,
                        () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> li)));
                dripstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dripstoneOre);
                basaltOre = Registry.regBlock(BaseStone.BASALT.get() + oreName,
                        () -> new Block(BASALT_BASED_ORE.lightLevel(s -> li)));
                basaltItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) basaltOre);
                endstoneOre = Registry.regBlock(BaseStone.ENDSTONE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                endstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) endstoneOre);
            }
            default ->
            {
                defaultOre = Registry.regBlock(BaseStone.STONE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                defaultItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) defaultOre);
                andesiteOre = Registry.regBlock(BaseStone.ANDESITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                andesiteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) andesiteOre);
                dioriteOre = Registry.regBlock(BaseStone.DIORITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                dioriteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dioriteOre);
                graniteOre = Registry.regBlock(BaseStone.GRANITE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                graniteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) graniteOre);
                calciteOre = Registry.regBlock(BaseStone.CALCITE.get() + oreName,
                        () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> li)));
                calciteItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) calciteOre);
                smtSaStOre = Registry.regBlock(BaseStone.SMSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtSaStOre);
                smtRedSaStOre = Registry.regBlock(BaseStone.SMRSAST.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                smtRedSaStItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) smtRedSaStOre);
                deepslateOre = Registry.regBlock(BaseStone.DEEPSLATE.get() + oreName,
                        () -> new Block(DEEPSLATE_BASED_ORE.lightLevel(s -> li)));
                deepslateItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) deepslateOre);
                tuffOre = Registry.regBlock(BaseStone.TUFF.get() + oreName,
                        () -> new Block(TUFF_BASED_ORE.lightLevel(s -> li)));
                tuffItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) tuffOre);
                dripstoneOre = Registry.regBlock(BaseStone.DRIPSTONE.get() + oreName,
                        () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> li)));
                dripstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) dripstoneOre);
                netherrackOre = Registry.regBlock(BaseStone.NETHERRACK.get() + oreName,
                        () -> new Block(NETHER_BASED_ORE.lightLevel(s -> li)));
                netherrackItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) netherrackOre);
                basaltOre = Registry.regBlock(BaseStone.BASALT.get() + oreName,
                        () -> new Block(BASALT_BASED_ORE.lightLevel(s -> li)));
                basaltItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) basaltOre);
                endstoneOre = Registry.regBlock(BaseStone.ENDSTONE.get() + oreName,
                        () -> new Block(STONE_BASED_ORE.lightLevel(s -> li)));
                endstoneItem = Registry.regSimpleBlockItem((DeferredBlock<Block>) endstoneOre);
            }
        }
        OreCollection collection = new OreCollection(name, defaultOre, defaultItem, andesiteOre, andesiteItem,
                dioriteOre, dioriteItem, graniteOre, graniteItem, calciteOre, calciteItem,
                smtSaStOre, smtSaStItem, smtRedSaStOre, smtRedSaStItem, deepslateOre, deepslateItem,
                tuffOre, tuffItem, dripstoneOre, dripstoneItem, netherrackOre, netherrackItem,
                basaltOre, basaltItem, endstoneOre, endstoneItem);
        ORE_COLLECTIONS.add(collection); return collection;
    }

    public String getName()
    { return this.name; }

    public List<Supplier<? extends Block>> getBlocks()
    { return List.of(defaultOre, andesiteOre, dioriteOre, graniteOre, calciteOre, smtSaStOre,
            smtRedSaStOre, deepslateOre, tuffOre, dripstoneOre, netherrackOre, basaltOre, endstoneOre); }
    
    public List<Supplier<? extends Item>> getItems()
    { return List.of(defaultItem, andesiteItem, dioriteItem, graniteItem, calciteItem, smtSaStItem,
            smtRedSaStItem, deepslateItem, tuffItem, dripstoneItem, netherrackItem, basaltItem, endstoneItem); }
}
