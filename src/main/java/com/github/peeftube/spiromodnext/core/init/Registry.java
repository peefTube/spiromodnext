package com.github.peeftube.spiromodnext.core.init;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.TierMod;
import com.github.peeftube.spiromodnext.core.init.registry.data.BlockToughness;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Registry
{
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    //public static final DeferredRegister.Items            ITEMS              = DeferredRegister.createItems(MOD_ID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    // public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    // public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    // public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);

    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    // public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
    // .alwaysEat().nutrition(1).saturationMod(2f).build()));

    // Creates a creative tab with the id "examplemod:example_tab" for the example item, that is placed after the combat tab
    // public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
    //.title(Component.translatable("itemGroup.examplemod")) //The language key for the title of your CreativeModeTab
    //.withTabsBefore(CreativeModeTabs.COMBAT)
    //.icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
    //.displayItems((parameters, output) -> {
    //output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
    //}).build());
    // ==[EXAMPLES END]==

    public static void init()
    {
        IEventBus bus = ModLoadingContext.get().getActiveContainer().getEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);

        TierMod.fire();
    }

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SpiroMod.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SpiroMod.MOD_ID);

    public static final DeferredBlock<Block> DIORITE_COAL = BLOCKS.register("diorite_coal_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> GRANITE_COAL = BLOCKS.register("granite_coal_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> ANDESITE_COAL = BLOCKS.register("andesite_coal_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> CALCITE_COAL = BLOCKS.register("calcite_coal_ore",
            () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> S_COAL = BLOCKS.register("sandstone_coal_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> RS_COAL = BLOCKS.register("red_sandstone_coal_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMS_COAL = BLOCKS.register("sm_sandstone_coal_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMRS_COAL = BLOCKS.register("sm_red_sandstone_coal_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> TUFF_COAL = BLOCKS.register("tuff_coal_ore",
            () -> new Block(TUFF_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> DRIPSTONE_COAL = BLOCKS.register("dripstone_coal_ore",
            () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> 0)));

    public static final DeferredItem<BlockItem> DIORITE_COAL_ITEM = ITEMS.registerSimpleBlockItem(DIORITE_COAL);
    public static final DeferredItem<BlockItem> GRANITE_COAL_ITEM = ITEMS.registerSimpleBlockItem(GRANITE_COAL);
    public static final DeferredItem<BlockItem> ANDESITE_COAL_ITEM = ITEMS.registerSimpleBlockItem(ANDESITE_COAL);
    public static final DeferredItem<BlockItem> CALCITE_COAL_ITEM = ITEMS.registerSimpleBlockItem(CALCITE_COAL);
    public static final DeferredItem<BlockItem> S_COAL_ITEM = ITEMS.registerSimpleBlockItem(S_COAL);
    public static final DeferredItem<BlockItem> RS_COAL_ITEM = ITEMS.registerSimpleBlockItem(RS_COAL);
    public static final DeferredItem<BlockItem> SMS_COAL_ITEM = ITEMS.registerSimpleBlockItem(SMS_COAL);
    public static final DeferredItem<BlockItem> SMRS_COAL_ITEM = ITEMS.registerSimpleBlockItem(SMRS_COAL);
    public static final DeferredItem<BlockItem> TUFF_COAL_ITEM = ITEMS.registerSimpleBlockItem(TUFF_COAL);
    public static final DeferredItem<BlockItem> DRIPSTONE_COAL_ITEM = ITEMS.registerSimpleBlockItem(DRIPSTONE_COAL);

    public static final DeferredBlock<Block> DIORITE_COPPER = BLOCKS.register("diorite_copper_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> GRANITE_COPPER = BLOCKS.register("granite_copper_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> ANDESITE_COPPER = BLOCKS.register("andesite_copper_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> CALCITE_COPPER = BLOCKS.register("calcite_copper_ore",
            () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> S_COPPER = BLOCKS.register("sandstone_copper_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> RS_COPPER = BLOCKS.register("red_sandstone_copper_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMS_COPPER = BLOCKS.register("sm_sandstone_copper_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMRS_COPPER = BLOCKS.register("sm_red_sandstone_copper_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> TUFF_COPPER = BLOCKS.register("tuff_copper_ore",
            () -> new Block(TUFF_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> DRIPSTONE_COPPER = BLOCKS.register("dripstone_copper_ore",
            () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> 0)));

    public static final DeferredItem<BlockItem> DIORITE_COPPER_ITEM = ITEMS.registerSimpleBlockItem(DIORITE_COPPER);
    public static final DeferredItem<BlockItem> GRANITE_COPPER_ITEM = ITEMS.registerSimpleBlockItem(GRANITE_COPPER);
    public static final DeferredItem<BlockItem> ANDESITE_COPPER_ITEM = ITEMS.registerSimpleBlockItem(ANDESITE_COPPER);
    public static final DeferredItem<BlockItem> CALCITE_COPPER_ITEM = ITEMS.registerSimpleBlockItem(CALCITE_COPPER);
    public static final DeferredItem<BlockItem> S_COPPER_ITEM = ITEMS.registerSimpleBlockItem(S_COPPER);
    public static final DeferredItem<BlockItem> RS_COPPER_ITEM = ITEMS.registerSimpleBlockItem(RS_COPPER);
    public static final DeferredItem<BlockItem> SMS_COPPER_ITEM = ITEMS.registerSimpleBlockItem(SMS_COPPER);
    public static final DeferredItem<BlockItem> SMRS_COPPER_ITEM = ITEMS.registerSimpleBlockItem(SMRS_COPPER);
    public static final DeferredItem<BlockItem> TUFF_COPPER_ITEM = ITEMS.registerSimpleBlockItem(TUFF_COPPER);
    public static final DeferredItem<BlockItem> DRIPSTONE_COPPER_ITEM = ITEMS.registerSimpleBlockItem(DRIPSTONE_COPPER);

    public static final DeferredBlock<Block> DIORITE_IRON = BLOCKS.register("diorite_iron_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> GRANITE_IRON = BLOCKS.register("granite_iron_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> ANDESITE_IRON = BLOCKS.register("andesite_iron_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> CALCITE_IRON = BLOCKS.register("calcite_iron_ore",
            () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> S_IRON = BLOCKS.register("sandstone_iron_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> RS_IRON = BLOCKS.register("red_sandstone_iron_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMS_IRON = BLOCKS.register("sm_sandstone_iron_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMRS_IRON = BLOCKS.register("sm_red_sandstone_iron_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> TUFF_IRON = BLOCKS.register("tuff_iron_ore",
            () -> new Block(TUFF_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> DRIPSTONE_IRON = BLOCKS.register("dripstone_iron_ore",
            () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> 0)));

    public static final DeferredItem<BlockItem> DIORITE_IRON_ITEM = ITEMS.registerSimpleBlockItem(DIORITE_IRON);
    public static final DeferredItem<BlockItem> GRANITE_IRON_ITEM = ITEMS.registerSimpleBlockItem(GRANITE_IRON);
    public static final DeferredItem<BlockItem> ANDESITE_IRON_ITEM = ITEMS.registerSimpleBlockItem(ANDESITE_IRON);
    public static final DeferredItem<BlockItem> CALCITE_IRON_ITEM = ITEMS.registerSimpleBlockItem(CALCITE_IRON);
    public static final DeferredItem<BlockItem> S_IRON_ITEM = ITEMS.registerSimpleBlockItem(S_IRON);
    public static final DeferredItem<BlockItem> RS_IRON_ITEM = ITEMS.registerSimpleBlockItem(RS_IRON);
    public static final DeferredItem<BlockItem> SMS_IRON_ITEM = ITEMS.registerSimpleBlockItem(SMS_IRON);
    public static final DeferredItem<BlockItem> SMRS_IRON_ITEM = ITEMS.registerSimpleBlockItem(SMRS_IRON);
    public static final DeferredItem<BlockItem> TUFF_IRON_ITEM = ITEMS.registerSimpleBlockItem(TUFF_IRON);
    public static final DeferredItem<BlockItem> DRIPSTONE_IRON_ITEM = ITEMS.registerSimpleBlockItem(DRIPSTONE_IRON);

    public static final DeferredBlock<Block> DIORITE_LAPIS = BLOCKS.register("diorite_lapis_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> GRANITE_LAPIS = BLOCKS.register("granite_lapis_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> ANDESITE_LAPIS = BLOCKS.register("andesite_lapis_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> CALCITE_LAPIS = BLOCKS.register("calcite_lapis_ore",
            () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> S_LAPIS = BLOCKS.register("sandstone_lapis_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> RS_LAPIS = BLOCKS.register("red_sandstone_lapis_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMS_LAPIS = BLOCKS.register("sm_sandstone_lapis_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMRS_LAPIS = BLOCKS.register("sm_red_sandstone_lapis_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> TUFF_LAPIS = BLOCKS.register("tuff_lapis_ore",
            () -> new Block(TUFF_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> DRIPSTONE_LAPIS = BLOCKS.register("dripstone_lapis_ore",
            () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> 0)));

    public static final DeferredItem<BlockItem> DIORITE_LAPIS_ITEM = ITEMS.registerSimpleBlockItem(DIORITE_LAPIS);
    public static final DeferredItem<BlockItem> GRANITE_LAPIS_ITEM = ITEMS.registerSimpleBlockItem(GRANITE_LAPIS);
    public static final DeferredItem<BlockItem> ANDESITE_LAPIS_ITEM = ITEMS.registerSimpleBlockItem(ANDESITE_LAPIS);
    public static final DeferredItem<BlockItem> CALCITE_LAPIS_ITEM = ITEMS.registerSimpleBlockItem(CALCITE_LAPIS);
    public static final DeferredItem<BlockItem> S_LAPIS_ITEM = ITEMS.registerSimpleBlockItem(S_LAPIS);
    public static final DeferredItem<BlockItem> RS_LAPIS_ITEM = ITEMS.registerSimpleBlockItem(RS_LAPIS);
    public static final DeferredItem<BlockItem> SMS_LAPIS_ITEM = ITEMS.registerSimpleBlockItem(SMS_LAPIS);
    public static final DeferredItem<BlockItem> SMRS_LAPIS_ITEM = ITEMS.registerSimpleBlockItem(SMRS_LAPIS);
    public static final DeferredItem<BlockItem> TUFF_LAPIS_ITEM = ITEMS.registerSimpleBlockItem(TUFF_LAPIS);
    public static final DeferredItem<BlockItem> DRIPSTONE_LAPIS_ITEM = ITEMS.registerSimpleBlockItem(DRIPSTONE_LAPIS);

    public static final DeferredBlock<Block> DIORITE_REDSTONE = BLOCKS.register("diorite_redstone_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> GRANITE_REDSTONE = BLOCKS.register("granite_redstone_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> ANDESITE_REDSTONE = BLOCKS.register("andesite_redstone_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> CALCITE_REDSTONE = BLOCKS.register("calcite_redstone_ore",
            () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> S_REDSTONE = BLOCKS.register("sandstone_redstone_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> RS_REDSTONE = BLOCKS.register("red_sandstone_redstone_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMS_REDSTONE = BLOCKS.register("sm_sandstone_redstone_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMRS_REDSTONE = BLOCKS.register("sm_red_sandstone_redstone_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> TUFF_REDSTONE = BLOCKS.register("tuff_redstone_ore",
            () -> new Block(TUFF_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> DRIPSTONE_REDSTONE = BLOCKS.register("dripstone_redstone_ore",
            () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> 0)));

    public static final DeferredItem<BlockItem> DIORITE_REDSTONE_ITEM = ITEMS.registerSimpleBlockItem(DIORITE_REDSTONE);
    public static final DeferredItem<BlockItem> GRANITE_REDSTONE_ITEM = ITEMS.registerSimpleBlockItem(GRANITE_REDSTONE);
    public static final DeferredItem<BlockItem> ANDESITE_REDSTONE_ITEM = ITEMS.registerSimpleBlockItem(ANDESITE_REDSTONE);
    public static final DeferredItem<BlockItem> CALCITE_REDSTONE_ITEM = ITEMS.registerSimpleBlockItem(CALCITE_REDSTONE);
    public static final DeferredItem<BlockItem> S_REDSTONE_ITEM = ITEMS.registerSimpleBlockItem(S_REDSTONE);
    public static final DeferredItem<BlockItem> RS_REDSTONE_ITEM = ITEMS.registerSimpleBlockItem(RS_REDSTONE);
    public static final DeferredItem<BlockItem> SMS_REDSTONE_ITEM = ITEMS.registerSimpleBlockItem(SMS_REDSTONE);
    public static final DeferredItem<BlockItem> SMRS_REDSTONE_ITEM = ITEMS.registerSimpleBlockItem(SMRS_REDSTONE);
    public static final DeferredItem<BlockItem> TUFF_REDSTONE_ITEM = ITEMS.registerSimpleBlockItem(TUFF_REDSTONE);
    public static final DeferredItem<BlockItem> DRIPSTONE_REDSTONE_ITEM = ITEMS.registerSimpleBlockItem(DRIPSTONE_REDSTONE);

    public static final DeferredBlock<Block> DIORITE_GOLD = BLOCKS.register("diorite_gold_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> GRANITE_GOLD = BLOCKS.register("granite_gold_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> ANDESITE_GOLD = BLOCKS.register("andesite_gold_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> CALCITE_GOLD = BLOCKS.register("calcite_gold_ore",
            () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> S_GOLD = BLOCKS.register("sandstone_gold_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> RS_GOLD = BLOCKS.register("red_sandstone_gold_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMS_GOLD = BLOCKS.register("sm_sandstone_gold_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMRS_GOLD = BLOCKS.register("sm_red_sandstone_gold_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> TUFF_GOLD = BLOCKS.register("tuff_gold_ore",
            () -> new Block(TUFF_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> DRIPSTONE_GOLD = BLOCKS.register("dripstone_gold_ore",
            () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> 0)));

    public static final DeferredItem<BlockItem> DIORITE_GOLD_ITEM = ITEMS.registerSimpleBlockItem(DIORITE_GOLD);
    public static final DeferredItem<BlockItem> GRANITE_GOLD_ITEM = ITEMS.registerSimpleBlockItem(GRANITE_GOLD);
    public static final DeferredItem<BlockItem> ANDESITE_GOLD_ITEM = ITEMS.registerSimpleBlockItem(ANDESITE_GOLD);
    public static final DeferredItem<BlockItem> CALCITE_GOLD_ITEM = ITEMS.registerSimpleBlockItem(CALCITE_GOLD);
    public static final DeferredItem<BlockItem> S_GOLD_ITEM = ITEMS.registerSimpleBlockItem(S_GOLD);
    public static final DeferredItem<BlockItem> RS_GOLD_ITEM = ITEMS.registerSimpleBlockItem(RS_GOLD);
    public static final DeferredItem<BlockItem> SMS_GOLD_ITEM = ITEMS.registerSimpleBlockItem(SMS_GOLD);
    public static final DeferredItem<BlockItem> SMRS_GOLD_ITEM = ITEMS.registerSimpleBlockItem(SMRS_GOLD);
    public static final DeferredItem<BlockItem> TUFF_GOLD_ITEM = ITEMS.registerSimpleBlockItem(TUFF_GOLD);
    public static final DeferredItem<BlockItem> DRIPSTONE_GOLD_ITEM = ITEMS.registerSimpleBlockItem(DRIPSTONE_GOLD);

    public static final DeferredBlock<Block> DIORITE_DIAMOND = BLOCKS.register("diorite_diamond_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> GRANITE_DIAMOND = BLOCKS.register("granite_diamond_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> ANDESITE_DIAMOND = BLOCKS.register("andesite_diamond_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> CALCITE_DIAMOND = BLOCKS.register("calcite_diamond_ore",
            () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> S_DIAMOND = BLOCKS.register("sandstone_diamond_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> RS_DIAMOND = BLOCKS.register("red_sandstone_diamond_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMS_DIAMOND = BLOCKS.register("sm_sandstone_diamond_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMRS_DIAMOND = BLOCKS.register("sm_red_sandstone_diamond_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> TUFF_DIAMOND = BLOCKS.register("tuff_diamond_ore",
            () -> new Block(TUFF_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> DRIPSTONE_DIAMOND = BLOCKS.register("dripstone_diamond_ore",
            () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> 0)));

    public static final DeferredItem<BlockItem> DIORITE_DIAMOND_ITEM = ITEMS.registerSimpleBlockItem(DIORITE_DIAMOND);
    public static final DeferredItem<BlockItem> GRANITE_DIAMOND_ITEM = ITEMS.registerSimpleBlockItem(GRANITE_DIAMOND);
    public static final DeferredItem<BlockItem> ANDESITE_DIAMOND_ITEM = ITEMS.registerSimpleBlockItem(ANDESITE_DIAMOND);
    public static final DeferredItem<BlockItem> CALCITE_DIAMOND_ITEM = ITEMS.registerSimpleBlockItem(CALCITE_DIAMOND);
    public static final DeferredItem<BlockItem> S_DIAMOND_ITEM = ITEMS.registerSimpleBlockItem(S_DIAMOND);
    public static final DeferredItem<BlockItem> RS_DIAMOND_ITEM = ITEMS.registerSimpleBlockItem(RS_DIAMOND);
    public static final DeferredItem<BlockItem> SMS_DIAMOND_ITEM = ITEMS.registerSimpleBlockItem(SMS_DIAMOND);
    public static final DeferredItem<BlockItem> SMRS_DIAMOND_ITEM = ITEMS.registerSimpleBlockItem(SMRS_DIAMOND);
    public static final DeferredItem<BlockItem> TUFF_DIAMOND_ITEM = ITEMS.registerSimpleBlockItem(TUFF_DIAMOND);
    public static final DeferredItem<BlockItem> DRIPSTONE_DIAMOND_ITEM = ITEMS.registerSimpleBlockItem(DRIPSTONE_DIAMOND);

    public static final DeferredBlock<Block> DIORITE_EMERALD = BLOCKS.register("diorite_emerald_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> GRANITE_EMERALD = BLOCKS.register("granite_emerald_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> ANDESITE_EMERALD = BLOCKS.register("andesite_emerald_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> CALCITE_EMERALD = BLOCKS.register("calcite_emerald_ore",
            () -> new Block(CALCITE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> S_EMERALD = BLOCKS.register("sandstone_emerald_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> RS_EMERALD = BLOCKS.register("red_sandstone_emerald_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMS_EMERALD = BLOCKS.register("sm_sandstone_emerald_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> SMRS_EMERALD = BLOCKS.register("sm_red_sandstone_emerald_ore",
            () -> new Block(STONE_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> TUFF_EMERALD = BLOCKS.register("tuff_emerald_ore",
            () -> new Block(TUFF_BASED_ORE.lightLevel(s -> 0)));
    public static final DeferredBlock<Block> DRIPSTONE_EMERALD = BLOCKS.register("dripstone_emerald_ore",
            () -> new Block(DRIPSTONE_BASED_ORE.lightLevel(s -> 0)));

    public static final DeferredItem<BlockItem> DIORITE_EMERALD_ITEM = ITEMS.registerSimpleBlockItem(DIORITE_EMERALD);
    public static final DeferredItem<BlockItem> GRANITE_EMERALD_ITEM = ITEMS.registerSimpleBlockItem(GRANITE_EMERALD);
    public static final DeferredItem<BlockItem> ANDESITE_EMERALD_ITEM = ITEMS.registerSimpleBlockItem(ANDESITE_EMERALD);
    public static final DeferredItem<BlockItem> CALCITE_EMERALD_ITEM = ITEMS.registerSimpleBlockItem(CALCITE_EMERALD);
    public static final DeferredItem<BlockItem> S_EMERALD_ITEM = ITEMS.registerSimpleBlockItem(S_EMERALD);
    public static final DeferredItem<BlockItem> RS_EMERALD_ITEM = ITEMS.registerSimpleBlockItem(RS_EMERALD);
    public static final DeferredItem<BlockItem> SMS_EMERALD_ITEM = ITEMS.registerSimpleBlockItem(SMS_EMERALD);
    public static final DeferredItem<BlockItem> SMRS_EMERALD_ITEM = ITEMS.registerSimpleBlockItem(SMRS_EMERALD);
    public static final DeferredItem<BlockItem> TUFF_EMERALD_ITEM = ITEMS.registerSimpleBlockItem(TUFF_EMERALD);
    public static final DeferredItem<BlockItem> DRIPSTONE_EMERALD_ITEM = ITEMS.registerSimpleBlockItem(DRIPSTONE_EMERALD);
}
