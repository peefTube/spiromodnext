package com.github.peeftube.spiromodnext.datagen.modules;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.Registry;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreMaterial;
import com.github.peeftube.spiromodnext.util.ore.BaseStone;
import com.github.peeftube.spiromodnext.util.ore.Coupling;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlockstateDataProv extends BlockStateProvider
{
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
        // NOTE: these two may be used in an OR statement to determine if this is a vanilla block. If so,
        //       code should ignore the raw ore blocks.
        // TODO: add handler for this!

        // Prepare set data.
        OreMaterial              material = set.getMat();
        Map<BaseStone, Coupling> bulkData = set.getBulkData();

        if (material == OreMaterial.COAL || material == OreMaterial.IRON || material == OreMaterial.COPPER
                || material == OreMaterial.GOLD || material == OreMaterial.LAPIS || material == OreMaterial.REDSTONE
                || material == OreMaterial.EMERALD || material == OreMaterial.DIAMOND)
        { ignoreStone = true; }

        if (material == OreMaterial.GOLD || material == OreMaterial.QUARTZ)
        { ignoreNether = true; }
        
        ResourceLocation mat = oreOverlayHelper(material.get());

        for (BaseStone s : BaseStone.values())
        {
            if (((s == BaseStone.STONE || s == BaseStone.DEEPSLATE) && ignoreStone)
                    || ((s == BaseStone.NETHERRACK) && ignoreNether))
            { continue; } // Do nothing, we're using a material which already uses this combination...

            // Make this code easier to read, PLEASE..
            Block b = bulkData.get(s).block().get();
            ResourceLocation r = blockTexture(s.getAssociatedBlock().get());

            // Quick sanity check for smooth sandstone and related
            switch(s)
            {
                case SMSAST -> r = getTopTex(blockTexture(Blocks.SANDSTONE));
                case SMRSAST -> r = getTopTex(blockTexture(Blocks.RED_SANDSTONE));
            }

            // Initialize this.
            BlockModelBuilder ore;

            switch (s)
            {
                default -> ore = modularOreBuilder(b, r, mat);
                case NETHERRACK, BASALT ->
                {
                    if (material.equals("gold"))
                    { ore = modularOreBuilder(b, r, oreOverlayHelper(material.get(), true)); }
                    else
                    { ore = modularOreBuilder(b, r, mat); }
                }
            }

            getVariantBuilder(b).partialState().setModels(new ConfiguredModel(ore));
        }
    }

    // These are copied from BlockStateProvider. I do not claim ownership of these!
    private ResourceLocation key(Block block)
    { return BuiltInRegistries.BLOCK.getKey(block); }

    private String name(Block block)
    { return key(block).getPath(); }
    // == Non-ownership block ends.

    // Translucent rendering.
    private static final String renTranslucent = /* "cutout"; */ "translucent";
    
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
