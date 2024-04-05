package com.druids.effects;

import com.druids.Druids;
import com.druids.entity.ChainLightning;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.SpellInfo;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.SpellRegistry;
import net.spell_engine.particle.ParticleHelper;
import net.spell_engine.utils.TargetHelper;
import net.spell_power.api.attributes.SpellAttributes;

import java.util.List;
import java.util.function.Predicate;

public class VoltaicBurst extends StatusEffect {
    public VoltaicBurst(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        if(duration == 1){return true;}
        return super.canApplyUpdateEffect(duration, amplifier);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.getWorld() instanceof ServerWorld server) {
            Predicate<Entity> selectionPredicate = (target) -> {
                return (TargetHelper.actionAllowed(TargetHelper.TargetingMode.AREA, TargetHelper.Intent.HARMFUL, entity, target));
            };
            Spell.Release.Target.Area area = new Spell.Release.Target.Area();
            area.angle_degrees = 360;
            Spell spell = SpellRegistry.getSpell(new Identifier(Druids.MODID, "voltaic_burst"));

            List<Entity> targets = TargetHelper.targetsFromArea(entity, entity.getPos(), spell.range, area, selectionPredicate);

            for (Entity target : targets) {
                SpellHelper.performImpacts(entity.getWorld(), entity, target, entity, new SpellInfo(spell, new Identifier(Druids.MODID, "voltaic_burst")), new SpellHelper.ImpactContext());
            }
            if(amplifier > 0) {
                entity.addStatusEffect(new StatusEffectInstance(Druids.VoltaicBurst, (int) ((double)40/(0.01*entity.getAttributeValue(SpellAttributes.HASTE.attribute))), amplifier - 1, false, false));

            }
            for(Spell.Impact impact : SpellRegistry.getSpell(new Identifier(Druids.MODID,"lightningblast")).impact) {
                ParticleHelper.sendBatches(entity, impact.particles,true);
            }
            server.playSound(null, BlockPos.ofFloored(entity.getPos()), SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.PLAYERS);
        }

        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {

        super.onRemoved(entity, attributes, amplifier);
    }
}
