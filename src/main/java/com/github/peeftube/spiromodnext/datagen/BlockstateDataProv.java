package com.github.peeftube.spiromodnext.datagen;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.Registry;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Supplier;

public class BlockstateDataProv extends BlockStateProvider
{
    // Vanilla block locations.
    protected final ResourceLocation diorite = blockTexture(Blocks.DIORITE);
    protected final ResourceLocation andesite = blockTexture(Blocks.ANDESITE);
    protected final ResourceLocation calcite = blockTexture(Blocks.CALCITE);
    protected final ResourceLocation granite = blockTexture(Blocks.GRANITE);
    protected final ResourceLocation tuff = blockTexture(Blocks.TUFF);
    protected final ResourceLocation dripstone = blockTexture(Blocks.DRIPSTONE_BLOCK);
    protected final ResourceLocation smtSaSt = getTopTex(blockTexture(Blocks.SANDSTONE));
    protected final ResourceLocation smtRedSaSt = getTopTex(blockTexture(Blocks.RED_SANDSTONE));
    protected final ResourceLocation basalt = blockTexture(Blocks.SMOOTH_BASALT);
    protected final ResourceLocation netherrack = blockTexture(Blocks.NETHERRACK);
    protected final ResourceLocation stone = blockTexture(Blocks.STONE);
    protected final ResourceLocation deepslate = blockTexture(Blocks.DEEPSLATE);
    protected final ResourceLocation endstone = blockTexture(Blocks.END_STONE);

    protected final ResourceLocation ironOverlay = new ResourceLocation(SpiroMod.MOD_ID, "block/overlays/iron_overlay");
    protected final ResourceLocation coalOverlay = new ResourceLocation(SpiroMod.MOD_ID, "block/overlays/coal_overlay");
    protected final ResourceLocation copperOverlay = new ResourceLocation(SpiroMod.MOD_ID, "block/overlays/copper_overlay");
    protected final ResourceLocation goldOverlay = new ResourceLocation(SpiroMod.MOD_ID, "block/overlays/gold_overlay");
    protected final ResourceLocation nethGoldOverlay = new ResourceLocation(SpiroMod.MOD_ID, "block/overlays/nether_gold_overlay");
    protected final ResourceLocation lapisOverlay = new ResourceLocation(SpiroMod.MOD_ID, "block/overlays/lapis_overlay");
    protected final ResourceLocation diamondOverlay = new ResourceLocation(SpiroMod.MOD_ID, "block/overlays/diamond_overlay");
    protected final ResourceLocation redstoneOverlay = new ResourceLocation(SpiroMod.MOD_ID, "block/overlays/redstone_overlay");
    protected final ResourceLocation emeraldOverlay = new ResourceLocation(SpiroMod.MOD_ID, "block/overlays/emerald_overlay");
    protected final ResourceLocation quartzOverlay = new ResourceLocation(SpiroMod.MOD_ID, "block/overlays/quartz_overlay");

    public BlockstateDataProv(PackOutput output, ExistingFileHelper eFH)
    { super(output, SpiroMod.MOD_ID, eFH); }

    @Override
    protected void registerStatesAndModels()
    {
        oreSetDesign(Registry.COAL_ORES);
        oreSetDesign(Registry.IRON_ORES);
        oreSetDesign(Registry.COPPER_ORES);
        oreSetDesign(Registry.GOLD_ORES);
        oreSetDesign(Registry.DIAMOND_ORES);
        oreSetDesign(Registry.LAPIS_ORES);
        oreSetDesign(Registry.REDSTONE_ORES);
        oreSetDesign(Registry.EMERALD_ORES);
        oreSetDesign(Registry.QUARTZ_ORES);
    }

    protected void oreSetDesign(OreCollection set)
    {
        // Flags for whether we should ignore block-model creation.
        boolean ignoreStone = false; // For ignoring default stone, assumes true for deepslate as well
        boolean ignoreNether = false; // For ignoring default Netherrack ore

        // Prepare set data.
        String material = set.getName();
        List<Supplier<? extends Block>> blocks = set.getBlocks();

        if (material.equals("coal") || material.equals("iron") || material.equals("copper") || material.equals("gold")
                || material.equals("diamond") || material.equals("lapis") || material.equals("redstone")
                || material.equals("emerald"))
        { ignoreStone = true; }

        if (material.equals("gold") || material.equals("quartz"))
        { ignoreNether = true; }
        
        ResourceLocation mat = oreOverlayHelper(material);

        for (int i = 0; i < blocks.size(); i++)
        {
            /* NOTE:
             * 0: default stone | 1: andesite | 2: diorite | 3: granite
             * 4: calcite | 5: smooth sandstone | 6: smooth red sandstone
             * 7: deepslate | 8: tuff | 9: dripstone | 10: netherrack
             * 11: basalt (smooth) | 12: endstone
             */
            if (((i == 0 || i == 7) && ignoreStone) || ((i == 10) && ignoreNether))
            { continue; } // Do nothing, we're using a material which already uses this combination...

            switch (i)
            {
                case 0 -> modularOreBuilder(blocks.get(i).get(), stone, mat);
                case 1 -> modularOreBuilder(blocks.get(i).get(), andesite, mat);
                case 2 -> modularOreBuilder(blocks.get(i).get(), diorite, mat);
                case 3 -> modularOreBuilder(blocks.get(i).get(), granite, mat);
                case 4 -> modularOreBuilder(blocks.get(i).get(), calcite, mat);
                case 5 -> modularOreBuilder(blocks.get(i).get(), smtSaSt, mat);
                case 6 -> modularOreBuilder(blocks.get(i).get(), smtRedSaSt, mat);
                case 7 -> modularOreBuilder(blocks.get(i).get(), deepslate, mat);
                case 8 -> modularOreBuilder(blocks.get(i).get(), tuff, mat);
                case 9 -> modularOreBuilder(blocks.get(i).get(), dripstone, mat);
                case 10 ->
                {
                    if (material.equals("gold"))
                    { modularOreBuilder(blocks.get(i).get(), netherrack, oreOverlayHelper(material, true)); }
                    else
                    { modularOreBuilder(blocks.get(i).get(), netherrack, mat); }
                }
                case 11 ->
                {
                    if (material.equals("gold"))
                    { modularOreBuilder(blocks.get(i).get(), basalt, oreOverlayHelper(material, true)); }
                    else
                    { modularOreBuilder(blocks.get(i).get(), basalt, mat); }
                }
                case 12 -> modularOreBuilder(blocks.get(i).get(), endstone, mat);
                default -> {}
            }
        }
    }

    // These are copied from BlockStateProvider. I do not claim ownership of these!
    private ResourceLocation key(Block block)
    {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private String name(Block block)
    {
        return key(block).getPath();
    }
    // == Non-ownership block ends.

    // Translucent rendering.
    private static final String renTranslucent = "translucent";
    
    // Ore overlay location helper.
    protected ResourceLocation oreOverlayHelper(String material)
    { return oreOverlayHelper(material, false); }
    
    protected ResourceLocation oreOverlayHelper(String material, boolean usingNetherVariant)
    {
        String p = "block/overlays/"; String s = "_overlay";
        String n = (usingNetherVariant ? "nether_" : "");
        return new ResourceLocation(SpiroMod.MOD_ID, p + n + material + s);
    }

    private ResourceLocation getBottomTex(ResourceLocation block) { return new ResourceLocation(block.toString() + "_bottom"); }
    private ResourceLocation getTopTex(ResourceLocation block) { return new ResourceLocation(block.toString() + "_top"); }
    private ResourceLocation getFrontTex(ResourceLocation block) { return new ResourceLocation(block.toString() + "_front"); }
    private ResourceLocation getSideTex(ResourceLocation block) { return new ResourceLocation(block.toString() + "_side"); }

    protected BlockModelBuilder modularOreBuilder(Block block, ResourceLocation baseTex, ResourceLocation oreTex)
    { return modularOreBuilder(block, baseTex, oreTex, 0); }

    protected BlockModelBuilder modularOreBuilder(Block block, ResourceLocation baseTex,
            ResourceLocation oreTex, int type)
    {
        switch(type)
        {
            default ->
            {
                return models().withExistingParent(name(block), "cube")
                               .texture("base", baseTex).element().cube("#base").end()
                               .texture("over", oreTex).element().cube("#over").end()
                               .texture("particle", baseTex)
                               .renderType(renTranslucent);
            }
            case 1 ->
            {
                return models().withExistingParent(name(block), "cube_bottom_top")
                               .texture("base", baseTex)
                               .texture("top", getTopTex(baseTex))
                               .texture("bottom", getBottomTex(baseTex))
                               .element()
                               .face(Direction.UP).texture("#top").cullface(Direction.UP).end()
                               .face(Direction.DOWN).texture("#bottom").cullface(Direction.DOWN).end()
                               .face(Direction.EAST).texture("#base").cullface(Direction.EAST).end()
                               .face(Direction.WEST).texture("#base").cullface(Direction.WEST).end()
                               .face(Direction.NORTH).texture("#base").cullface(Direction.NORTH).end()
                               .face(Direction.SOUTH).texture("#base").cullface(Direction.SOUTH).end()
                               .end()
                               .texture("over", oreTex).element().cube("#over").end()
                               .texture("particle", getBottomTex(baseTex))
                               .renderType(renTranslucent);
            }
        }
    }
}
