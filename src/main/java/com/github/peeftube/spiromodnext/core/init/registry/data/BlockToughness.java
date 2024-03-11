package com.github.peeftube.spiromodnext.core.init.registry.data;

public enum BlockToughness
{
    PUNY(0.5f),
    FLIMSY(1f),
    WEAK(2f),
    NORMAL(3f),
    TOUGH(4.5f),
    STRONG(6f),
    IMPREGNABLE(9f);

    private float toughness;

    private BlockToughness(float str)
    { this.toughness = str; }

    public float getToughness()
    { return toughness; }
}
