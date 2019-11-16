package com.mojius.mojiusmod.client.renders;

import com.mojius.mojiusmod.entities.TNTArrowEntity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class TNTArrowRender extends ArrowRenderer<TNTArrowEntity>{
	
	public static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/projectiles/arrow.png");
	
	   public TNTArrowRender(EntityRendererManager manager) {
		      super(manager);
	    }

	@Override
	protected ResourceLocation getEntityTexture(TNTArrowEntity entity) {
		return TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<TNTArrowEntity>
	{

		@Override
		public EntityRenderer<? super TNTArrowEntity> createRenderFor(EntityRendererManager manager) {

			return new TNTArrowRender(manager);
		}
		
	}



}
