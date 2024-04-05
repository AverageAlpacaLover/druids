package com.druids;

import com.druids.client.entity.DruidCycloneRenderer;
import com.druids.client.entity.DruidLightningRenderer;
import com.druids.client.entity.TotemRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class DruidsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Druids.CYCLONEENTITY, DruidCycloneRenderer::new);
        EntityRendererRegistry.register(Druids.DRUIDLIGHTNING, DruidLightningRenderer::new);

        EntityRendererRegistry.register(Druids.TOTEM    , TotemRenderer::new);
        EntityRendererRegistry.register(Druids.SoulFlare, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(Druids.ChainLightning, FlyingItemEntityRenderer::new);


    }
}
