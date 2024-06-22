package com.druids.client.entity;

import com.druids.entity.DruidCyclone;
import mod.azure.azurelib.cache.object.BakedGeoModel;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

public class DruidCycloneRenderer<T extends DruidCyclone> extends GeoEntityRenderer<DruidCyclone> {

    public DruidCycloneRenderer(EntityRendererFactory.Context context) {
        super(context, new DruidCycloneModel<>());

    }

    @Override
    public void preRender(MatrixStack poseStack, DruidCyclone animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

                this.scaleWidth = 2 * 1.75F;
                this.scaleHeight = 2 * 1.5F;





        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

}