package com.mojius.mojiusmod.client.renders;

import javax.annotation.Nullable;

import com.mojius.mojiusmod.MojiusMod;
import com.mojius.mojiusmod.client.models.GrassmanModel;
import com.mojius.mojiusmod.entities.GrassmanEntity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;


public class GrassmanRender extends MobRenderer<GrassmanEntity, GrassmanModel>{

	private static final ResourceLocation TEXTURE = new ResourceLocation(MojiusMod.MOD_ID, "textures/entity/grassman.png");
	
	  public GrassmanRender(EntityRendererManager manager) {
	        super(manager, new GrassmanModel(0.0f), 0.5f);	
	    }

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(GrassmanEntity entity) {
		
		return TEXTURE;
	}
	
	
	public static class RenderFactory implements IRenderFactory<GrassmanEntity>
	{

		@Override
		public EntityRenderer<? super GrassmanEntity> createRenderFor(EntityRendererManager manager) {

			return new GrassmanRender(manager);
		}
		
	}


}
