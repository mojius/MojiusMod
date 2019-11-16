package com.mojius.mojiusmod.items;

import com.mojius.mojiusmod.MojiusMod;
import com.mojius.mojiusmod.entities.TNTArrowEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TNTArrowItem extends ArrowItem {

	public TNTArrowItem(Properties builder) {
		super(builder);
		setRegistryName(MojiusMod.MOD_ID, "tnt_arrow");
	}
	
   public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
	   return new TNTArrowEntity(worldIn, shooter);
	   }
	
	  
	
}
