package com.mojius.mojiusmod.entities;

import com.mojius.mojiusmod.init.InitEntity;
import com.mojius.mojiusmod.init.InitItem;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.ProjectileImpactEvent.Arrow;
import net.minecraftforge.fml.network.NetworkHooks;

public class TNTArrowEntity extends AbstractArrowEntity{

	   public TNTArrowEntity(EntityType<? extends TNTArrowEntity> type, World worldIn) {
		      super(type, worldIn);
	}

	   public TNTArrowEntity(World worldIn, double x, double y, double z) {
		      super(InitEntity.TNTARROW, x, y, z, worldIn);
		   }

		   public TNTArrowEntity(World worldIn, LivingEntity shooter) {
		      super(InitEntity.TNTARROW, shooter, worldIn);
		   }


		   @Override
		    public IPacket<?> createSpawnPacket() {
		        return NetworkHooks.getEntitySpawningPacket(this);
		    }
		   
	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(InitItem.tnt_arrow);
	}

	public static void explode(Arrow event) {
		if (!event.getArrow().world.isRemote)
		{
			if (event.getArrow().getType() == InitEntity.TNTARROW)
			{
				if ((event.getRayTraceResult().getType() == Type.BLOCK || event.getRayTraceResult().getType() == Type.ENTITY)
					&& event.getArrow().getShooter().getEntity().getType() == EntityType.PLAYER)
				{
					event.getArrow().world.createExplosion(event.getArrow(), event.getArrow().posX, event.getArrow().posY, event.getArrow().posZ, 2.0F, Explosion.Mode.BREAK);
					event.getArrow().remove();
				}
				else {}
			}
		}
		
	}

	

}
