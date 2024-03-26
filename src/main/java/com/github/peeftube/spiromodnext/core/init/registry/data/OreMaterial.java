package com.github.peeftube.spiromodnext.core.init.registry.data;

public enum OreMaterial
{
    // Vanilla.
    COAL("coal", false), IRON("iron", false), COPPER("copper", false),
    GOLD("gold", false), LAPIS("lapis", true),
    REDSTONE("redstone", true), EMERALD("emerald", true), DIAMOND("diamond", true),
    QUARTZ("quartz", true),

    // Modded.
    RUBY("ruby", true);

    private final String name;

    // NOTE: May not actually be a gem, this just "asks" whether the material behaves similarly.
    private final boolean isGem;

    OreMaterial(String name, boolean isGem)
    { this.name = name; this.isGem = isGem; }

    public String get()
    { return name; }

    public boolean isGem()
    { return isGem; }
}
