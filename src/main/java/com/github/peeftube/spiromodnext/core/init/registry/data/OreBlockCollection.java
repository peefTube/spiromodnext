package com.github.peeftube.spiromodnext.core.init.registry.data;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public record OreBlockCollection(String name,
                                 DeferredBlock<Block> defaultOre, DeferredItem<BlockItem> defaultItem,
                                 DeferredBlock<Block> andesiteOre, DeferredItem<BlockItem> andesiteItem,
                                 DeferredBlock<Block> dioriteOre, DeferredItem<BlockItem> dioriteItem,
                                 DeferredBlock<Block> graniteOre, DeferredItem<BlockItem> graniteItem,
                                 DeferredBlock<Block> calciteOre, DeferredItem<BlockItem> calciteItem,
                                 DeferredBlock<Block> saStOre, DeferredItem<BlockItem> saStItem,
                                 DeferredBlock<Block> smtSaStOre, DeferredItem<BlockItem> smtSaStItem,
                                 DeferredBlock<Block> redSaStOre, DeferredItem<BlockItem> redSaStItem,
                                 DeferredBlock<Block> smtRedSaStOre, DeferredItem<BlockItem> smtRedSaStItem,
                                 DeferredBlock<Block> deepslateOre, DeferredItem<BlockItem> deepslateItem,
                                 DeferredBlock<Block> tuffOre, DeferredItem<BlockItem> tuffItem,
                                 DeferredBlock<Block> dripstoneOre, DeferredItem<BlockItem> dripstoneItem,
                                 DeferredBlock<Block> blackstoneOre, DeferredItem<BlockItem> blackstoneItem,
                                 DeferredBlock<Block> netherrackOre, DeferredItem<BlockItem> netherrackItem,
                                 DeferredBlock<Block> basaltOre, DeferredItem<BlockItem> basaltItem,
                                 DeferredBlock<Block> smtBasaltOre, DeferredItem<BlockItem> smtBasaltItem,
                                 DeferredBlock<Block> endstoneOre, DeferredItem<BlockItem> endstoneItem)
{
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
}
