package com.mojius.mojiusmod.entities.ai.goal;

import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.Difficulty;

public class BreakDoorGoalAnalysis extends InteractDoorGoalAnalysis {
   private final Predicate<Difficulty> difficultyPredicate;
   protected int breakingTime;
   protected int previousBreakProgress = -1;
   protected int someMaxVar = -1;

   public BreakDoorGoalAnalysis(MobEntity mobEntity, Predicate<Difficulty> difficultyPredicate2) {
      super(mobEntity); //TODO: super
      this.difficultyPredicate = difficultyPredicate2;
   }

   public BreakDoorGoalAnalysis(MobEntity mobEntity, int someWeirdVar, Predicate<Difficulty> difficultyPredicate3) {
      this(mobEntity, difficultyPredicate3); //Calling the previous constructor
      this.someMaxVar = someWeirdVar; //some weird zombie variable
   }

   protected int maxOfTwoDeterminingBreakTime() {
      return Math.max(240, this.someMaxVar);
   }

   /**
    * Returns whether the EntityAIBase should begin execution.
    */
   public boolean shouldExecute() {
      if (!super.shouldExecute()) { //TODO: super - interact
         return false;
      } else if (!net.minecraftforge.common.ForgeHooks.canEntityDestroy(this.entity.world, this.doorPosition, this.entity)) {
         return false;
      } else {
         return this.difficultyChecker(this.entity.world.getDifficulty()) && !this.canDestroy();
      }
   }

   /**
    * Execute a one shot task or start executing a continuous task
    */
   public void startExecuting() {
      super.startExecuting(); //TODO: super - interact
      this.breakingTime = 0; //Resets the breaking timer to zero.
   }

   /**
    * Returns whether an in-progress EntityAIBase should continue executing
    */
   public boolean shouldContinueExecuting() {
      return this.breakingTime <= this.maxOfTwoDeterminingBreakTime() //If the breaking time is less than or equal to the final break time, true
    		  && !this.canDestroy() //If canDestroy is false, true.
    		  && this.doorPosition.withinDistance(this.entity.getPositionVec(), 2.0D) //If the block position is still within 2 blocks? true
    		  && this.difficultyChecker(this.entity.world.getDifficulty()); //If it's still hard mode, true
   }

   /**
    * Reset the task's internal state. Called when this task is interrupted by another one
    */
   public void resetTask() {
      super.resetTask(); //TODO: super - goal - we can keep this i think
      this.entity.world.sendBlockBreakProgress(this.entity.getEntityId(), this.doorPosition, -1); //If the task is reset, the block breaking progress is back at good old zero.
   }

   /**
    * Keep ticking a continuous task that has already been started
    */
   public void tick() {
      super.tick(); //TODO: super - interact
      if (this.entity.getRNG().nextInt(20) == 0) { //I think what it basically is is - for every tick, if a number between 0 and 20 is zero, 
         this.entity.world.playEvent(1019, this.doorPosition, 0); //Breaking sound, I think.
         if (!this.entity.isSwingInProgress) {//These two... Read below
            this.entity.swingArm(this.entity.getActiveHand()); //These two are just to ensure the entity is swinging its arm as it's breaking it
         }
      }

      ++this.breakingTime; //Increment the breaking timer
      int i = (int)((float)this.breakingTime / (float)this.maxOfTwoDeterminingBreakTime() * 10.0F); //This converts the ratio of breakingTime and the max time to an integer so that data can be transferred to a block
      if (i != this.previousBreakProgress) {
         this.entity.world.sendBlockBreakProgress(this.entity.getEntityId(), this.doorPosition, i); //If I is not the same as it just was (hopefully,) register the blockBreakProgress of that block.
         this.previousBreakProgress = i; //Just setting the previous break progress to i before it increments again
      }

      if (this.breakingTime == this.maxOfTwoDeterminingBreakTime() && this.difficultyChecker(this.entity.world.getDifficulty())) { //This is the code that runs when the break time is up
         this.entity.world.removeBlock(this.doorPosition, false);
         this.entity.world.playEvent(1021, this.doorPosition, 0); //These are the breaking destruction sounds.
         this.entity.world.playEvent(2001, this.doorPosition, Block.getStateId(this.entity.world.getBlockState(this.doorPosition)));
      }

   }

   private boolean difficultyChecker(Difficulty difficultyType) { //Just checks the difficulty
      return this.difficultyPredicate.test(difficultyType);
   }
}