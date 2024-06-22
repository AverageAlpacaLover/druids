package com.druids.items.Armors;

import com.druids.Druids;
import com.druids.items.ItemsInit;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.item.armor.Armor;
import net.spell_power.api.SpellSchools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Armors {
    public static final ArrayList<Armor.Entry> entries = new ArrayList<>();
    private static Armor.Entry create(Armor.CustomMaterial material, ItemConfig.ArmorSet defaults) {
        return new Armor.Entry(material, null, defaults);
    }

    public static final Armor.Set primalist =
            create(
                    new Armor.CustomMaterial(
                            "primalist",
                            35,
                            20,
                            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,
                            () -> Ingredient.ofItems(Items.LEATHER,Items.IRON_INGOT)
                    ),
                    ItemConfig.ArmorSet.with(
                            new ItemConfig.ArmorSet.Piece(2)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply((SpellSchools.SOUL.id), 0.15F),
                                            ItemConfig.Attribute.multiply((SpellSchools.LIGHTNING.id), 0.15F)

                                    )),
                            new ItemConfig.ArmorSet.Piece(6)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply((SpellSchools.SOUL.id), 0.15F),
                                            ItemConfig.Attribute.multiply((SpellSchools.LIGHTNING.id), 0.15F)

                                    )),
                            new ItemConfig.ArmorSet.Piece(4)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply((SpellSchools.SOUL.id), 0.15F),
                                            ItemConfig.Attribute.multiply((SpellSchools.LIGHTNING.id), 0.15F)

                                    )),
                            new ItemConfig.ArmorSet.Piece(2)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply((SpellSchools.SOUL.id), 0.15F),
                                            ItemConfig.Attribute.multiply((SpellSchools.LIGHTNING.id), 0.15F)

                                    ))
                    ))   .bundle(material -> new Armor.Set(Druids.MODID,
                            new PrimalistArmor(material, ArmorItem.Type.HELMET, new Item.Settings()),
                            new PrimalistArmor(material, ArmorItem.Type.CHESTPLATE, new Item.Settings()),
                            new PrimalistArmor(material, ArmorItem.Type.LEGGINGS, new Item.Settings()),
                            new PrimalistArmor(material, ArmorItem.Type.BOOTS, new Item.Settings())
                    ))
                    .put(entries).armorSet();;

    public static void register(Map<String, ItemConfig.ArmorSet> configs) {
        Armor.register(configs, entries, ItemsInit.KEY);

    }
}