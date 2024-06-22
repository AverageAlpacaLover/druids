package com.druids.client.item.model;

import com.druids.Druids;
import com.druids.items.Shard;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;
import net.spell_power.api.SpellSchools;

public class  ShardModel extends GeoModel<Shard> {


    @Override
    public Identifier getModelResource(Shard orb) {
        return new Identifier(Druids.MODID,"geo/shard.geo.json");
    }

    @Override
    public Identifier getTextureResource(Shard orb) {
        if(orb.getSchool() == SpellSchools.FIRE) {
            return new Identifier(Druids.MODID, "textures/item/shard_fire.png");
        }
        if(orb.getSchool() == SpellSchools.FROST) {
            return new Identifier(Druids.MODID, "textures/item/shard_frost.png");
        }
        if(orb.getSchool() == SpellSchools.LIGHTNING) {
            return new Identifier(Druids.MODID, "textures/item/shard_lightning.png");
        }
        return new Identifier(Druids.MODID, "textures/item/shard_arcane.png");
    }

    @Override
    public Identifier getAnimationResource(Shard orb) {
        return new Identifier(Druids.MODID,"animations/shard.animations.json");
    }
}