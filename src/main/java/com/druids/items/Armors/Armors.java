package com.druids.items.Armors;

import com.druids.Druids;
import com.druids.items.ItemsInit;
import com.spellbladenext.Spellblades;
import com.spellbladenext.items.armor.HeraldArmor;
import com.spellbladenext.items.armor.MagisterArmor;
import com.spellbladenext.items.armor.MagusArmor;
import com.spellbladenext.items.armor.RunicArmor;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.item.armor.Armor;
import net.spell_power.api.MagicSchool;
import net.spell_power.api.attributes.SpellAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Armors {
    private static final Supplier<Ingredient> WOOL_INGREDIENTS = () -> { return Ingredient.ofItems(
            Items.WHITE_WOOL,
            Items.ORANGE_WOOL,
            Items.MAGENTA_WOOL,
            Items.LIGHT_BLUE_WOOL,
            Items.YELLOW_WOOL,
            Items.LIME_WOOL,
            Items.PINK_WOOL,
            Items.GRAY_WOOL,
            Items.LIGHT_GRAY_WOOL,
            Items.CYAN_WOOL,
            Items.PURPLE_WOOL,
            Items.BLUE_WOOL,
            Items.BROWN_WOOL,
            Items.GREEN_WOOL,
            Items.RED_WOOL,
            Items.BLACK_WOOL);
    };

    public static final ArrayList<Armor.Entry> entries = new ArrayList<>();
    public static final ArrayList<Armor.Entry> runeentries = new ArrayList<>();
    public static final ArrayList<Armor.Entry> magisterentries = new ArrayList<>();

    private static Armor.Entry create(Armor.CustomMaterial material, ItemConfig.ArmorSet defaults) {
        return new Armor.Entry(material, null, defaults);
    }



    private static final float specializedRobeSpellPower = 0.25F;
    private static final float specializedRobeCritDamage = 0.1F;
    private static final float specializedRobeCritChance = 0.02F;
    private static final float specializedRobeHaste = 0.03F;

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
                                            ItemConfig.Attribute.multiply(SpellAttributes.POWER.get(MagicSchool.SOUL), 0.15F),
                                            ItemConfig.Attribute.multiply(SpellAttributes.POWER.get(MagicSchool.LIGHTNING), 0.15F)

                                    )),
                            new ItemConfig.ArmorSet.Piece(6)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(SpellAttributes.POWER.get(MagicSchool.SOUL), 0.15F),
                                            ItemConfig.Attribute.multiply(SpellAttributes.POWER.get(MagicSchool.LIGHTNING), 0.15F)

                                    )),
                            new ItemConfig.ArmorSet.Piece(4)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(SpellAttributes.POWER.get(MagicSchool.SOUL), 0.15F),
                                            ItemConfig.Attribute.multiply(SpellAttributes.POWER.get(MagicSchool.LIGHTNING), 0.15F)

                                    )),
                            new ItemConfig.ArmorSet.Piece(2)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(SpellAttributes.POWER.get(MagicSchool.SOUL), 0.15F),
                                            ItemConfig.Attribute.multiply(SpellAttributes.POWER.get(MagicSchool.LIGHTNING), 0.15F)

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
       /* for (Armor.Entry entry : entries){
            for(Object pieces : entry.armorSet().pieces()) {
                if(pieces instanceof List list){
                    for(Object item : list){
                        if(item instanceof ArmorItem item1){
                            if(item1 instanceof MagisterArmor armor){
                                UUID uuid = (UUID) MagisterArmor.MODIFIERS.get(armor.getType());
                                System.out.println("Registering magister");
                                armor.getAttributeModifiers(armor.getSlotType())
                                        .put(Spellblades.WARDING,new EntityAttributeModifier(uuid,"warding",3, EntityAttributeModifier.Operation.ADDITION));
                            }
                            if(item1 instanceof RunicArmor armor){
                                UUID uuid = (UUID) MagisterArmor.MODIFIERS.get(armor.getType());
                                System.out.println("Registering runic");

                                armor.getAttributeModifiers(armor.getSlotType())
                                        .put(Spellblades.WARDING,new EntityAttributeModifier(uuid,"warding",3, EntityAttributeModifier.Operation.ADDITION));
                            }
                            if(item1 instanceof MagusArmor armor){
                                UUID uuid = (UUID) MagisterArmor.MODIFIERS.get(armor.getType());
                                System.out.println("Registering Magus");

                                armor.getAttributeModifiers(armor.getSlotType())
                                        .put(Spellblades.WARDING,new EntityAttributeModifier(uuid,"warding",3, EntityAttributeModifier.Operation.ADDITION));
                            }
                        }
                    }
                }
            }
        }*/

    }
}