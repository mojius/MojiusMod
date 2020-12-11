package com.mojius.mojiusmod.entities.ai.goal;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class InteractDoorGoalAnalysis extends Goal {
   protected MobEntity entity;
   protected BlockPos doorPosition = BlockPos.ZERO;
   protected boolean doorInteract;
   private boolean hasStoppedDoorInteraction;
   private float entityPositionX;
   private float entityPositionZ;

   public InteractDoorGoalAnalysis(MobEntity entityIn) {
      this.entity = entityIn;
      if (!(entityIn.getNavigator() instanceof GroundPathNavigator)) {
         throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
      }
   }

   protected boolean canDestroy() { //This is important to us. On second thought, not particularly, since It's just doing a check to see if the door is already open or not
      if (!this.doorInteract) {  //If it's not a door at all - or, it's just gone now
         return false;
      } else {
         BlockState blockstate = this.entity.world.getBlockState(this.doorPosition);
         if (!(blockstate.getBlock() instanceof DoorBlock)) { //One more check to see if it's the door
            this.doorInteract = false; //Just like, another check I guess? Not sure.
            return false;
         } else {
            return blockstate.get(DoorBlock.OPEN); //This returns a boolean - so if it's open, it can't destroy it - but that would be true? Don't really get it, but I get the concept.
         }
      }
   }

   protected void toggleDoor(boolean open) { //NOBODY CARES! woah woah woah
      if (this.doorInteract) {
         BlockState blockstate = this.entity.world.getBlockState(this.doorPosition);
         if (blockstate.getBlock() instanceof DoorBlock) {
            ((DoorBlock)blockstate.getBlock()).toggleDoor(this.entity.world, this.doorPosition, open);
         }
      }

   }

   /**
    * Returns whether the EntityAIBase should begin execution.
    */
   public boolean shouldExecute() { //It all starts here!
      if (!this.entity.collidedHorizontally) { //If the grassman hasn't actually touched the block it's gonna break, or the villager/zombie isn't touching the door they want to go into
         return false;
      } else {
         GroundPathNavigator groundpathnavigator = (GroundPathNavigator)this.entity.getNavigator(); //Create a new groundpathnavigator based on this entity's navigator.
         Path path = groundpathnavigator.getPath(); //Get a path for the entity to follow.
         if (path != null && !path.isFinished() && groundpathnavigator.getEnterDoors()) { //Something about entering doors. Otherwise, if there IS a path and if it's not finshed
            for(int i = 0; i < Math.min(path.getCurrentPathIndex() + 2, path.getCurrentPathLength()); ++i) { //Not sure what this does, but it's a for loop based on a min of 2 numbers
               PathPoint pathpoint = path.getPathPointFromIndex(i); //Yeah, This takes a path point from i in the loop - the first out of the small selection.
               this.doorPosition = new BlockPos(pathpoint.x, pathpoint.y + 1, pathpoint.z); //The door COULD BE at this blockpos.
               if (!(this.entity.getDistanceSq((double)this.doorPosition.getX(), this.entity.posY, (double)this.doorPosition.getZ()) > 2.25D)) { //If you're close enough to the door(?) block,
                  this.doorInteract = makeSureIsDoor(this.entity.world, this.doorPosition); //check if it's a door. 
                  if (this.doorInteract) {// If it is,
                     return true; //execute!
                  }
               }
            }
            //This part of the code is for the entity being right next to it, I guess - not sure.
            this.doorPosition = (new BlockPos(this.entity)).up(); //I don't get this, but I assume it's just trying to round up to the next block if the blockpos of the entity is the ground(?)
            this.doorInteract = makeSureIsDoor(this.entity.world, this.doorPosition); //Just checking to see if it's the door
            return this.doorInteract; 
         } else {
            return false;
         }
      }
   }

   /**
    * Returns whether an in-progress EntityAIBase should continue executing
    */
   public boolean shouldContinueExecuting() {
      return !this.hasStoppedDoorInteraction;
   }

   /**
    * Execute a one shot task or start executing a continuous task
    */
   public void startExecuting() {
      this.hasStoppedDoorInteraction = false;
      this.entityPositionX = (float)((double)((float)this.doorPosition.getX() + 0.5F) - this.entity.posX); //Not sure what these are - lining up the mob, maybe? That sounds about right
      this.entityPositionZ = (float)((double)((float)this.doorPosition.getZ() + 0.5F) - this.entity.posZ);
   }

   /**
    * Keep ticking a continuous task that has already been started
    */
   public void tick() {
      float f = (float)((double)((float)this.doorPosition.getX() + 0.5F) - this.entity.posX); //These, I assume are just to check whether or not 
      float f1 = (float)((double)((float)this.doorPosition.getZ() + 0.5F) - this.entity.posZ);//The entity walking through or breaking down the door is done with it.
      float f2 = this.entityPositionX * f + this.entityPositionZ * f1; //Maybe I could model an example scenario with graph paper
      if (f2 < 0.0F) {
         this.hasStoppedDoorInteraction = true; //we could probably also apply this to block interaction
      }

   }

   public static boolean makeSureIsDoor(World world, BlockPos blockpos) {
      BlockState blockstate = world.getBlockState(blockpos);
      return blockstate.getBlock() instanceof DoorBlock && blockstate.getMaterial() == Material.WOOD; //Bam. Make sure it's a damn door!
   }
}