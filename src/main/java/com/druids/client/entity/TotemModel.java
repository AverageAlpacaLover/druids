package com.druids.client.entity;

import com.druids.Druids;
import com.druids.entity.TotemEntity;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;

public class TotemModel<T extends TotemEntity> extends GeoModel<TotemEntity> {

    @Override
    public Identifier getModelResource(TotemEntity reaver) {

        return new Identifier(Druids.MODID,"geo/totem.geo.json");
    }
    @Override
    public Identifier getTextureResource(TotemEntity reaver) {

        return new Identifier(Druids.MODID, "textures/mob/totem.png");

    }

    @Override
    public Identifier getAnimationResource(TotemEntity reaver) {
        return null;
    }

}
