package com.github.peeftube.spiromodnext.datagen.modules.loot;

import com.github.peeftube.spiromodnext.datagen.modules.loot.subprov.BlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class LootTableDataProv extends LootTableProvider
{
    public LootTableDataProv(PackOutput output)
    {
        super(output, Set.of(), List.of(new LootTableProvider
                .SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK)));
    }
}
