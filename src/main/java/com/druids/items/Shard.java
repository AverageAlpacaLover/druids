package com.druids.items;

import com.druids.client.item.renderer.ShardRenderer;
import com.extraspellattributes.ReabsorptionInit;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.client.RenderProvider;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.Animation;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.spell_engine.api.item.weapon.StaffItem;
import net.spell_power.api.MagicSchool;
import net.spell_power.api.SpellPower;
import net.spell_power.api.attributes.SpellAttributes;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Shard extends StaffItem implements GeoItem {

    public Shard(ToolMaterial material, Settings settings,MagicSchool school) {
        super(material, settings);
        this.school = school;

    }
    MagicSchool school = MagicSchool.PHYSICAL_MELEE;

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private ShardRenderer renderer;

            @Override
            public BuiltinModelItemRenderer getCustomRenderer() {
                if (renderer == null) return new ShardRenderer();
                return this.renderer;
            }
        });
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();

        var modifiers = super.getAttributeModifiers(stack, slot);
        builder.putAll(modifiers);
        if(slot.equals(EquipmentSlot.OFFHAND)){
            builder.put(SpellAttributes.POWER.get(MagicSchool.SOUL).attribute, new EntityAttributeModifier(UUID.fromString("38c504e2-d5f9-4128-8349-8049163a895c"), "druids:soul", 4, EntityAttributeModifier.Operation.ADDITION));
            builder.put(SpellAttributes.POWER.get(this.school).attribute, new EntityAttributeModifier(UUID.fromString("1255d7d7-3cbf-4204-b031-bfe790c1155f"), "druids:shard", 0.5, EntityAttributeModifier.Operation.MULTIPLY_BASE));

        }
        return builder.build();
    }

    private AnimatableInstanceCache factory = AzureLibUtil.createInstanceCache(this);
    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");

    public MagicSchool getSchool() {
        return school;
    }
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<Shard>(this,"idle",
                0,animationState -> {
            animationState.getController().setAnimation(RawAnimation.begin().then(
                    "idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;})
        );
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }


    public Supplier<Object> getRenderProvider() {
        return this.renderProvider;
    }
}
