package com.mojius.mojiusmod.client.models;


import com.mojius.mojiusmod.entities.GrassmanEntity;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrassmanModel extends BipedModel<GrassmanEntity> {

	static float mysteryVar = 0.0f;
	   public GrassmanModel(float modelSize) {
		      super(0.0f, mysteryVar, 64, 32);
		   }


	public void render(GrassmanEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		      super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	   }

    public void setRotationAngles(GrassmanEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    	super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
    }
}
	
	   