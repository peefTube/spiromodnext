package com.github.peeftube.spiromodnext.core.init.registry.data;

public enum OreMaterial
{
    COAL("coal"), IRON("iron"), COPPER("copper"), GOLD("gold"),
    LAPIS("lapis"), REDSTONE("redstone"), EMERALD("emerald"), DIAMOND("diamond"),
    QUARTZ("quartz");

    private final String name;

    OreMaterial(String name)
    { this.name = name; }

    public String get()
    { return name; }
}
