package com.mojius.mojiusmod.entities;

import java.util.Random;

import com.mojius.mojiusmod.init.InitSound;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class GrassmanEntity extends MonsterEntity {

	public GrassmanEntity(EntityType<? extends MonsterEntity> type, World worldIn)
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
		   
		   public static boolean func_223325_c(EntityType<? extends MonsterEntity> p_223325_0_, IWorld p_223325_1_, SpawnReason p_223325_2_, BlockPos p_223325_3_, Random p_223325_4_) {
			   return p_223325_1_.getDifficulty() != Difficulty.PEACEFUL && func_223323_a(p_223325_1_, p_223325_3_, p_223325_4_) && func_223315_a(p_223325_0_, p_223325_1_, p_223325_2_, p_223325_3_, p_223325_4_);
			   }

			   public static boolean func_223324_d(EntityType<? extends MonsterEntity> p_223324_0_, IWorld p_223324_1_, SpawnReason p_223324_2_, BlockPos p_223324_3_, Random p_223324_4_) {
			      return p_223324_1_.getDifficulty() != Difficulty.PEACEFUL && func_223315_a(p_223324_0_, p_223324_1_, p_223324_2_, p_223324_3_, p_223324_4_);
			   }
		   
	public void livingTick() {
		  
		if (this.getEntityWorld().getDimension().isNether())
		{
            this.setFire(8);
        }

	      super.livingTick();
	   }
	
	
}