package com.druids.items.Armors;

import com.druids.client.item.renderer.PrimalistArmorRenderer;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.client.RenderProvider;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.spell_engine.api.item.armor.Armor;
import net.spell_power.api.SpellSchools;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.extraspellattributes.ReabsorptionInit.RECOUP;

public class PrimalistArmor extends CustomArmor implements GeoItem {

    public PrimalistArmor(Armor.CustomMaterial material, Type type, Settings settings) {
        super(material, type, settings);

    }
    private Multimap<EntityAttribute, EntityAttributeModifier> attributes;



    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (attributes == null) {
            return super.getAttributeModifiers(slot);
        }
        return slot == this.type.getEquipmentSlot() ? this.attributes : super.getAttributeModifiers(slot);
    }
    private static final EnumMap<Type, UUID> MODIFIERS = (EnumMap) Util.make(new EnumMap(Type.class), (uuidMap) -> {
        uuidMap.put(Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        uuidMap.put(Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        uuidMap.put(Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        uuidMap.put(Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });
    private final List<SpellSchools> magicschool = new ArrayList<>();

    @Override
    public void setAttributes(Multimap<EntityAttribute, EntityAttributeModifier> attributes) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        // builder.putAll(super.getAttributeModifiers(this.slot));
        builder.putAll(attributes);
        UUID uuid = (UUID)MODIFIERS.get(this.type);
        builder.put(RECOUP,new EntityAttributeModifier(uuid,"determination",.15, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        this.attributes = builder.build();
    }

    // MARK: GeoItem
    public List<SpellSchools> getMagicschool() {
        return magicschool;
    }
    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);


    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private PrimalistArmorRenderer renderer;

            @Override
            public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
                if (this.renderer == null) {
                    this.renderer = new PrimalistArmorRenderer();
                }
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }

        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
