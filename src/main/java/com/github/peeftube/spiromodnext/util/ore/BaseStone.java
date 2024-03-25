package com.github.peeftube.spiromodnext.util.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

import static com.github.peeftube.spiromodnext.core.init.Registry.*;

public enum BaseStone
{
    STONE("", STONE_BASED_ORE, () -> Blocks.STONE),
    ANDESITE("andesite_", STONE_BASED_ORE, () -> Blocks.ANDESITE),
    DIORITE("diorite_", STONE_BASED_ORE, () -> Blocks.DIORITE),
    GRANITE("granite_", STONE_BASED_ORE, () -> Blocks.GRANITE),
    CALCITE("calcite_", CALCITE_BASED_ORE, () -> Blocks.CALCITE),
    SMSAST("smooth_sandstone_", STONE_BASED_ORE, () -> Blocks.SMOOTH_SANDSTONE),
    SMRSAST("smooth_red_sandstone_", STONE_BASED_ORE, () -> Blocks.SMOOTH_RED_SANDSTONE),
    DEEPSLATE("deepslate_", DEEPSLATE_BASED_ORE, () -> Blocks.DEEPSLATE),
    TUFF("tuff_", TUFF_BASED_ORE, () -> Blocks.TUFF),
    DRIPSTONE("dripstone_", DRIPSTONE_BASED_ORE, () -> Blocks.DRIPSTONE_BLOCK),
    NETHERRACK("nether_", NETHER_BASED_ORE, () -> Blocks.NETHERRACK),
    BASALT("basalt_", BASALT_BASED_ORE, () -> Blocks.SMOOTH_BASALT),
    ENDSTONE("end_", STONE_BASED_ORE, () -> Blocks.END_STONE);

    private final String                    name;
    private final BlockBehaviour.Properties props;
    private final Supplier<Block>           associatedBlock;

    BaseStone(String name, BlockBehaviour.Properties props, Supplier<Block> associatedBlock)
    { this.name = name; this.props = props; this.associatedBlock = associatedBlock; }

    public String get()
    { return name; }

    public BlockBehaviour.Properties getProps()
    { return props; }

    public Supplier<Block> getAssociatedBlock()
    { return associatedBlock; }
}
