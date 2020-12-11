package com.mojius.mojiusmod.entities;

import com.mojius.mojiusmod.entities.ai.goal.ModifiedMeleeAttackGoal;
import com.mojius.mojiusmod.entities.ai.goal.ReachPlayerBreakBlockGoal;
import com.mojius.mojiusmod.init.InitSound;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class GrassmanEntity extends MonsterAboveGroundEntity {
	
	public GrassmanEntity(EntityType<? extends MonsterAboveGroundEntity> type, World worldIn)
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
		      this.goalSelector.addGoal(1, new ReachPlayerBreakBlockGoal(this));
		      this.goalSelector.addGoal(2, new ModifiedMeleeAttackGoal(this, 1.5D, true));
		      this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setCallsForHelp(GrassmanEntity.class));
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
	
	   protected SoundEvent getAmbientSound() {
		      return InitSound.GRASSMAN_AMBIENT;
		   }

		   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		      return InitSound.GRASSMAN_HURT;
		   }

		   protected SoundEvent getDeathSound() { 
		      return InitSound.GRASSMAN_DEATH;
		   }
	
		   
		   protected void updateAITasks() {
			      if (this.isBurning()) {
			         this.attackEntityFrom(DamageSource.ON_FIRE, 2.0F);
			      }
		   }

	public void livingTick() {
		  
		if (this.getEntityWorld().getDimension().isNether())
		{
            this.setFire(8);
        }
	      super.livingTick();
	   }
	


}