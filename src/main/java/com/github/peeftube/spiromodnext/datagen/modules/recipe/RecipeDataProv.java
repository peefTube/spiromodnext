package com.github.peeftube.spiromodnext.datagen.modules.recipe;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.init.Registrar;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodnext.util.SpiroTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class RecipeDataProv extends RecipeProvider implements IConditionBuilder
{
    public RecipeDataProv(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup)
    { super(output, lookup); }

    @Override
    protected void buildRecipes(RecipeOutput output)
    {
        // Automatic ore smelting handler, this will later handle ore-to-ingot conversions when that's implemented
        // NOTE: The ore-to-ingot conversion mentioned is not block-to-ingot smelting, but item-to-ingot, which will
        //       need to be handled later when new non-gems are added
        for (OreCollection ore : OreCollection.ORE_COLLECTIONS) { oreSmeltingHandler(ore, output); }
    }

    private void oreSmeltingHandler(OreCollection set, RecipeOutput consumer)
    {
        TagKey<Item> tag = set.getOreIT();
        String mat = set.getMat().get();

        Item output = ( set.getMat().getIngotConvertible() != null ) ? set.getMat().getIngotConvertible().get() :
                set.getRawOre().getRawItem().get();

        // TODO: Add data to OreCollection which handles the experience and cooking time; should probably be enumerated
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(tag), RecipeCategory.MISC, output, 1.0f, 200)
                .unlockedBy("has_ore", has(tag))
                .save(consumer, new ResourceLocation(SpiroMod.MOD_ID, "spiro_smelt_" + mat + "_from_ore_block"));
    }
}
