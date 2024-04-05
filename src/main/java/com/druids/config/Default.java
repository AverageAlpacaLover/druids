package com.druids.config;

import com.druids.items.Weapons;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.loot.LootConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Default {
    public final static ItemConfig itemConfig;
    public final static LootConfig lootConfig;
    static {
        itemConfig = new ItemConfig();
        for (var weapon: Weapons.entries) {
            itemConfig.weapons.put(weapon.name(), weapon.defaults());
        }

        lootConfig = new LootConfig();

        lootConfig.item_groups.put("wands_tier_1", new LootConfig.ItemGroup(List.of(
                Weapons.lightningWand.id().toString()),
                0.4F,
                0F,
                1
        ).chance(0.3F));

        var staves_tier_2 = "staves_tier_2";
        lootConfig.item_groups.put(staves_tier_2, new LootConfig.ItemGroup(List.of(
                Weapons.lightningStaff.id().toString()),
                1
        ).chance(0.3F));

        lootConfig.item_groups.put("staves_tier_2_enchanted", new LootConfig.ItemGroup(
                new ArrayList(lootConfig.item_groups.get(staves_tier_2).ids),
                1
        ).chance(0.3F).enchant());



        List.of("minecraft:chests/desert_pyramid",
                        "minecraft:chests/bastion_bridge",
                        "minecraft:chests/jungle_temple",
                        "minecraft:chests/pillager_outpost",
                        "minecraft:chests/simple_dungeon",
                        "minecraft:chests/stronghold_crossing")
                .forEach(id -> lootConfig.loot_tables.put(id, List.of("wands_tier_1","staves_tier_2")));




        List.of("minecraft:chests/bastion_other",
                        "minecraft:chests/nether_bridge",
                        "minecraft:chests/underwater_ruin_small")
                .forEach(id -> lootConfig.loot_tables.put(id, List.of("staves_tier_2")));

        List.of("minecraft:chests/end_city_treasure",
                        "minecraft:chests/bastion_treasure",
                        "minecraft:chests/ancient_city",
                        "minecraft:chests/stronghold_library")
                .forEach(id -> lootConfig.loot_tables.put(id, List.of("staves_tier_2_enchanted")));


    }

    @SafeVarargs
    private static <T> List<T> joinLists(List<T>... lists) {
        return Arrays.stream(lists).flatMap(Collection::stream).collect(Collectors.toList());
    }
}