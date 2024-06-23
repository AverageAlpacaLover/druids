package com.druids.items;

import com.druids.Druids;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.spell_engine.api.item.trinket.SpellBooks;
import net.spell_engine.api.item.weapon.Weapon;
import net.spell_power.api.SpellSchools;

public class ItemsInit {
    public static ItemGroup DRUIDS;

    public static Item FROSTSHARD;
    public static Item ARCANESHARD;
    public static Item FIRESHARD;
    public static Item LIGHTNINGSHARD;

    public static RegistryKey<ItemGroup> KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),new Identifier(Druids.MODID,"generic"));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Druids.MODID,name),item);
    }
    private static void addItemToGroup(Item item){
        ItemGroupEvents.modifyEntriesEvent(KEY).register((content) -> {
            content.add(item);
        });
    }
    public static void register() {

        ARCANESHARD = registerItem("shard_arcane",new Shard(Weapon.CustomMaterial.matching(ToolMaterials.DIAMOND,
                () -> Ingredient.ofItems(Items.AMETHYST_SHARD)), new FabricItemSettings(), SpellSchools.ARCANE));
        FROSTSHARD = registerItem("shard_frost",new Shard(Weapon.CustomMaterial.matching(ToolMaterials.DIAMOND,
                () -> Ingredient.ofItems(Items.PRISMARINE_CRYSTALS)), new FabricItemSettings(), SpellSchools.FROST));
        FIRESHARD = registerItem("shard_fire",new Shard(Weapon.CustomMaterial.matching(ToolMaterials.DIAMOND,
                () -> Ingredient.ofItems(Items.AMETHYST_SHARD)), new FabricItemSettings(), SpellSchools.FIRE));
        LIGHTNINGSHARD = registerItem("shard_lightning",new Shard(Weapon.CustomMaterial.matching(ToolMaterials.DIAMOND,
                () -> Ingredient.ofItems(Items.AMETHYST_SHARD)), new FabricItemSettings(), SpellSchools.LIGHTNING));

        DRUIDS = FabricItemGroup.builder()
                .icon(() -> new ItemStack(ARCANESHARD))
                .displayName(Text.translatable("itemGroup.druids.general"))
                .build();
        Registry.register(Registries.ITEM_GROUP, KEY, DRUIDS);
        SpellBooks.createAndRegister(new Identifier(Druids.MODID,"aetherial_druid"),KEY);
        SpellBooks.createAndRegister(new Identifier(Druids.MODID,"storm_druid"),KEY);
        addItemToGroup(ARCANESHARD);
        addItemToGroup(FROSTSHARD);
        addItemToGroup(FIRESHARD);
        addItemToGroup(LIGHTNINGSHARD);

    }
}
