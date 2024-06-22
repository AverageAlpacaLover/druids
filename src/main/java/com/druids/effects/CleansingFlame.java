package com.druids.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CleansingFlame extends StatusEffect {
    public CleansingFlame(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 10 == 0;
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(!entity.isOnFire()){
            entity.setOnFireFor(4);
        }
        super.applyUpdateEffect(entity, amplifier);

    }

}
