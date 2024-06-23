package com.druids.client.item.model;

import com.druids.Druids;
import com.druids.items.Armors.PrimalistArmor;
import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;

public class PrimalistArmorModel extends GeoModel<PrimalistArmor> {


    @Override
    public Identifier getModelResource(PrimalistArmor animatable) {

        return new Identifier(Druids.MODID,"geo/primalist.geo.json");
    }

    @Override
    public Identifier getTextureResource(PrimalistArmor animatable) {


        return new Identifier(Druids.MODID,"textures/armor/primalist.png");
    }

    @Override
    public Identifier getAnimationResource(PrimalistArmor animatable) {
        return null;
    }
}
