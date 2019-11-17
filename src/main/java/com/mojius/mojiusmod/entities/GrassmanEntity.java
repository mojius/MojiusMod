package com.mojius.mojiusmod.entities;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class GrassmanEntity extends CreatureEntity {

	public GrassmanEntity(EntityType<? extends CreatureEntity> type, World worldIn)
	{
		super (type, worldIn); 
	}

	protected void registerGoals() {
	      this.goalSelector.addGoal(1, new SwimGoal(this));
	      this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	      this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	      this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	      this.applyEntityAI();
	}
		protected void applyEntityAI() {
		      this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.5D, true));
		      this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setCallsForHelp());
		      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		   }

	
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0d);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.23F);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.5d);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0d);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0d);
	}
	
	
	public void livingTick() {
		  
		if (this.getEntityWorld().getDimension().isNether())
		{
            this.setFire(8);
        }

	      super.livingTick();
	   }
	
	
}