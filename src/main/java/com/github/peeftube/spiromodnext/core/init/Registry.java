package com.github.peeftube.spiromodnext.core.init;

import com.github.peeftube.spiromodnext.SpiroMod;
import com.github.peeftube.spiromodnext.core.TierMod;
import com.github.peeftube.spiromodnext.core.init.creativetabs.CreativeTabProcessor;
import com.github.peeftube.spiromodnext.core.init.registry.data.BlockToughness;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreCollection;
import com.github.peeftube.spiromodnext.core.init.registry.data.OreMaterial;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class Registry
{
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    //public static final DeferredRegister.Items            ITEMS              = DeferredRegister.createItems(MOD_ID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SpiroMod.MOD_ID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    // public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    // public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);

    // Creates a new food item with the id "examplemod:example_id", nutrition 1 and saturation 2
    // public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
    // .alwaysEat().nutrition(1).saturationMod(2f).build()));
    // ==[EXAMPLES END]==

    public static void init()
    {
        IEventBus bus = ModLoadingContext.get().getActiveContainer().getEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        CREATIVE_MODE_TABS.register(bus);

        TierMod.fire();
    }

    public static final BlockBehaviour.Properties STONE_BASED_ORE     =
            BlockBehaviour.Properties.of().strength(BlockToughness.NORMAL.get()).sound(SoundType.STONE);
    public static final BlockBehaviour.Properties TUFF_BASED_ORE      =
            BlockBehaviour.Properties.of().strength(BlockToughness.NORMAL.get()).sound(SoundType.TUFF);
    public static final BlockBehaviour.Properties DRIPSTONE_BASED_ORE =
            BlockBehaviour.Properties.of().strength(BlockToughness.NORMAL.get()).sound(SoundType.DRIPSTONE_BLOCK);
    public static final BlockBehaviour.Properties DEEPSLATE_BASED_ORE =
            BlockBehaviour.Properties.of().strength(BlockToughness.TOUGH.get()).sound(SoundType.DEEPSLATE);
    public static final BlockBehaviour.Properties CALCITE_BASED_ORE =
            BlockBehaviour.Properties.of().strength(BlockToughness.WEAK.get()).sound(SoundType.CALCITE);
    public static final BlockBehaviour.Properties NETHER_BASED_ORE =
            BlockBehaviour.Properties.of().strength(BlockToughness.WEAK.get()).sound(SoundType.NETHERRACK);
    public static final BlockBehaviour.Properties BASALT_BASED_ORE =
            BlockBehaviour.Properties.of().strength(BlockToughness.WEAK.get()).sound(SoundType.BASALT);
    public static final BlockBehaviour.Properties RAW_ORE =
            BlockBehaviour.Properties.of().strength(BlockToughness.NORMAL.get()).sound(SoundType.METAL);

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SpiroMod.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SpiroMod.MOD_ID);

    // Based on Nyfaria's code:
    // https://shorturl.at/bktNR
    public static <B extends Block> DeferredBlock<B> regBlock(String name, Supplier<B> block)
    { return BLOCKS.register(name, block); }

    public static <I extends Item> DeferredItem<I> regSimpleBlockItem(DeferredBlock<Block> block)
    { return (DeferredItem<I>) ITEMS.registerSimpleBlockItem(block); }

    public static final OreCollection COAL_ORES = OreCollection.registerCollection(OreMaterial.COAL);
    public static final OreCollection IRON_ORES = OreCollection.registerCollection(OreMaterial.IRON);
    public static final OreCollection COPPER_ORES = OreCollection.registerCollection(OreMaterial.COPPER);
    public static final OreCollection GOLD_ORES = OreCollection.registerCollection(OreMaterial.GOLD);
    public static final OreCollection LAPIS_ORES = OreCollection.registerCollection(OreMaterial.LAPIS);
    public static final OreCollection REDSTONE_ORES = OreCollection.registerCollection(OreMaterial.REDSTONE);
    public static final OreCollection DIAMOND_ORES = OreCollection.registerCollection(OreMaterial.DIAMOND);
    public static final OreCollection EMERALD_ORES = OreCollection.registerCollection(OreMaterial.EMERALD);
    public static final OreCollection QUARTZ_ORES = OreCollection.registerCollection(OreMaterial.QUARTZ);
    public static final OreCollection RUBY_ORES = OreCollection.registerCollection(OreMaterial.RUBY);

    // Language key for creative tabs
    public static final String TAB_TITLE_KEY_FORMULAIC = "itemGroup." + SpiroMod.MOD_ID;

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MINERALS_TAB = CREATIVE_MODE_TABS.register("minerals_tab",
            () -> CreativeModeTab.builder().title(Component.translatable(TAB_TITLE_KEY_FORMULAIC + ".minerals_tab"))
     .withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> RUBY_ORES.getRawOre().getRawItem().get().getDefaultInstance())
     .displayItems((parameters, output) -> { output.acceptAll(CreativeTabProcessor.precacheMineralsTab()); })
     .build());
}
