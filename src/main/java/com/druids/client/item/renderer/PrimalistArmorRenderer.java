package com.druids.client.item.renderer;

import com.druids.client.item.model.PrimalistArmorModel;
import com.druids.items.Armors.PrimalistArmor;
import com.spellbladenext.client.item.model.MagisterArmorModel;
import com.spellbladenext.items.armor.MagisterArmor;
import mod.azure.azurelib.renderer.GeoArmorRenderer;

public class PrimalistArmorRenderer extends GeoArmorRenderer<PrimalistArmor> {

    public PrimalistArmorRenderer() {
        super(new PrimalistArmorModel());

    }
}
