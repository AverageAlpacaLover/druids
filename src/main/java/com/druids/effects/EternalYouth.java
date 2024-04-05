package com.druids.effects;

import com.extraspellattributes.ReabsorptionInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class EternalYouth extends StatusEffect {
    public EternalYouth(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        entity.setOnFire(false);
        entity.setOnFireFor(0);
        entity.setAbsorptionAmount((float) entity.getAttributeValue(ReabsorptionInit.WARDING)/2);
        entity.setHealth(2);

    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
        entity.setHealth(entity.getMaxHealth()/2);
        entity.setAbsorptionAmount(0);

    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }


}
