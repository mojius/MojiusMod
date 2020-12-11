package com.mojius.mojiusmod.entities.ai.goal;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ReachPlayerBreakBlockGoal extends Goal {
   protected int breakingTime;
   protected MobEntity entity;
   protected int previousBreakProgress = -1;
   private BlockPos blockPosition = BlockPos.ZERO;

   //FROM INTERACTDOOR - FINE FOR THE TIME BEING FOR USE
public ReachPlayerBreakBlockGoal(MobEntity entityIn) { //Removed the difficulty predicate for now
    this.entity = entityIn;
    if (!(entityIn.getNavigator() instanceof GroundPathNavigator)) {
       throw new IllegalArgumentException("Unsupported mob type for ReachPlayerBreakBlockGoal");
       
    } 
 }

//FROM BREAKDOOR
protected int maxOfTwoDeterminingBreakTime() {
    return 40; //Just setting it to that for now.
 }

public boolean canJumpInSpace()
{
	BlockState aboveHead = this.entity.world.getBlockState(this.entity.getPosition().up(2));
	return aboveHead.isAir(this.entity.world, this.entity.getPosition().up(2));

}

//NEW FUNCTION BASED FROM INTERACTDOOR
public boolean shouldExecuteProper() { //It all starts here! Original use is to check if a door is there
	if (this.entity.isAggressive())
	{
	BlockPos entityPos = this.entity.getPosition();
	GroundPathNavigator groundpathnavigator = (GroundPathNavigator)this.entity.getNavigator();
    Path path = groundpathnavigator.getPath();
    if (path != null)
     {
	     if (path.isFinished())
	     {
	    	 int lookYDifference = ((int)this.entity.getLookController().getLookPosY() - (int)(this.entity.posY + 1));
			 if (lookYDifference > 0) //So, the target entity is above
			 {
				 if (!isAir(entityPos.up(2))) //If the block above your head is solid (hopefully dirt)
				 {
					 BlockPos trivialBlock = (entityPos.up(2));
					 if (makeSureIsBreakableBlock(this.entity.world, trivialBlock))
					 {
						 this.blockPosition = trivialBlock;
						 return true;
					 }
					 else return false;
				 } else {
					 for (int i = 2; i > 0; i--)
					 {
						 BlockPos trivialBlock = entityPos.up(i).add
								 (
								 (int)Math.signum((int)this.entity.getLookController().getLookPosX() - (int)this.entity.posX)
								 , 
								 0
								 ,
								 (int)Math.signum((int)this.entity.getLookController().getLookPosZ() - (int)this.entity.posZ)
								 ); 
						 if (makeSureIsBreakableBlock(this.entity.world, trivialBlock))
						 {
							 this.blockPosition = trivialBlock;
							 this.entity.setPosition(this.entity.posX, this.entity.posY, this.entity.posZ);
							 return true;
						 }
						 else
						 {
							 continue;
						 }
						
					 }
				 }

					 
			 } else if (lookYDifference == 0) //The entity is below the player
			 {
				 for (int i = 1; i > -1; i--)
				 {
					 BlockPos trivialBlock = entityPos.up(i).add
							 (
							 (int)Math.signum((int)this.entity.getLookController().getLookPosX() - (int)this.entity.posX)
							 , 
							 0
							 ,
							 (int)Math.signum((int)this.entity.getLookController().getLookPosZ() - (int)this.entity.posZ)
							 ); 
					 if (makeSureIsBreakableBlock(this.entity.world, trivialBlock))
					 {
						 this.blockPosition = trivialBlock;
						 this.entity.setPosition(this.entity.posX, this.entity.posY, this.entity.posZ);
						 return true;
					 }
					 else
					 {
						 continue;
					 }
					
				 }
				 
				 
			 } else if (lookYDifference < 0) //The entity is below the player
			 {
				 for (int i = 1; i > -2; i--)
				 {
					 BlockPos trivialBlock = entityPos.up(i).add
							 (
							 (int)Math.signum((int)this.entity.getLookController().getLookPosX() - (int)this.entity.posX)
							 , 
							 0
							 ,
							 (int)Math.signum((int)this.entity.getLookController().getLookPosZ() - (int)this.entity.posZ)
							 ); 
					 if (makeSureIsBreakableBlock(this.entity.world, trivialBlock))
					 {
						 this.blockPosition = trivialBlock;
						 this.entity.setPosition(this.entity.posX, this.entity.posY, this.entity.posZ);
						 return true;
					 }
					 else
					 {
						 continue;
					 }
					
				 }
	     } 
     } else
     {
    	//This is the no path scenario code.
    	 return false;
     }
     
	} else
	{
		//Non aggro code
		return false;
	}

	}
	return false;

}
	
	        	//We need to keep this code for if the grassman has no path
//	        	this.blockPosition = this.entity.getPosition().offset(this.entity.getHorizontalFacing(), 1).up();
//	        	if (this.blockPosition.manhattanDistance(this.entity.getPosition().offset(this.entity.getHorizontalFacing())) <= 1){
//	        	this.blockCheck = makeSureIsBreakableBlock(this.entity.world, this.blockPosition);

//	  return false;
//	}




//FROM BREAKDOOR
public boolean shouldContinueExecuting() {
	return (this.breakingTime < this.maxOfTwoDeterminingBreakTime());
//  return  //If the breaking time is less than or equal to the final break time, true
//	  //&& !this.canDestroy() //If canDestroy is false, true.a
//	  && this.blockPosition.withinDistance(this.entity.getPositionVec(), 2.0D)
}

  

//FROM INTERACTDOOR
public boolean makeSureIsBreakableBlock(World world, BlockPos blockpos) {
	      BlockState blockstate = world.getBlockState(blockpos);
	      return (blockstate.getBlock() == Blocks.DIRT || blockstate.getBlock() == Blocks.GRASS_BLOCK);
	   }

public boolean isAir(BlockPos blockpos) {
    BlockState blockstate = this.entity.world.getBlockState(blockpos);
    return (blockstate.isAir(this.entity.world, blockpos));
 }



//FROM BREAKDOOR
public boolean shouldExecute() {
    if (!shouldExecuteProper()) { 
       return false;
    } else if (!net.minecraftforge.common.ForgeHooks.canEntityDestroy(this.entity.world, this.blockPosition, this.entity)) {
       return false;
    } else {
       return true; //!this.canDestroy(); //&& this.difficultyChecker(this.entity.world.getDifficulty())
    }
 }

//FROM BREAKDOOR
public void startExecuting() {
    startExecutingProper();

    this.breakingTime = 0; //Resets the breaking timer to zero.
 }

//RENAMED - FROM INTERACTDOOR
public void startExecutingProper() {
//	      this.hasStoppedblockChecking = false;
//	      this.entityPositionX = (float)((double)((float)this.blockPosition.getX() + 0.5F) - this.entity.posX); //Not sure what these are - lining up the mob, maybe? That sounds about right
//	      this.entityPositionZ = (float)((double)((float)this.blockPosition.getZ() + 0.5F) - this.entity.posZ);
	   }



    

//FROM BREAKDOOR - THIS I DON'T THINK NEEDS TO BE MODIFIED
public void resetTask() {
    super.resetTask(); 
    this.entity.world.sendBlockBreakProgress(this.entity.getEntityId(), this.blockPosition, -1); //If the task is reset, the block breaking progress is back at good old zero.
 }

//FROM BREAKDOOR
public void tick() {

	this.entity.setMotion(0, 0, 0);
//    float f = (float)((double)((float)this.blockPosition.getX() + 0.5F) - this.entity.posX); //These, I assume are just to check whether or not 
//    float f1 = (float)((double)((float)this.blockPosition.getZ() + 0.5F) - this.entity.posZ);//The entity walking through or breaking down the door is done with it.
//    float f2 = this.entityPositionX * f + this.entityPositionZ * f1; //Maybe I could model an example scenario with graph paper
//    if (f2 < 0.0F) {
//       this.hasStoppedblockChecking = true; //we could probably also apply this to block interaction. I wish it wasn't used
//    }
	
    if (this.breakingTime % 4 == 0) { //I think what it basically is is - for every tick, if a number between 0 and 20 is zero, 
       this.entity.playSound(entity.world.getBlockState(blockPosition).getSoundType().getHitSound(), 1.0f, 0.0f);//Breaking sound, I think.
       if (!this.entity.isSwingInProgress) {//These two... Read below
          this.entity.swingArm(this.entity.getActiveHand()); //These two are just to ensure the entity is swinging its arm as it's breaking it
       }
    }

    ++this.breakingTime; //Increment the breaking timer
    int i = (int)((float)this.breakingTime / (float)this.maxOfTwoDeterminingBreakTime() * 10.0F); //This converts the ratio of breakingTime and the max time to an integer so that data can be transferred to a block
    if (i != this.previousBreakProgress) {
       this.entity.world.sendBlockBreakProgress(this.entity.getEntityId(), this.blockPosition, i); //If I is not the same as it just was (hopefully,) register the blockBreakProgress of that block.
       this.previousBreakProgress = i; //Just setting the previous break progress to i before it increments again
    }

    if (this.breakingTime == this.maxOfTwoDeterminingBreakTime()) { //This is the code that runs when the break time is up // && this.difficultyChecker(this.entity.world.getDifficulty())
       this.entity.world.destroyBlock(this.blockPosition, true);
       this.entity.playSound(entity.world.getBlockState(blockPosition).getSoundType().getBreakSound(), 1.0f, 0.0f);
 }

}


   //FROM INTERACTDOOR
//protected boolean canDestroy() { //This is important to us. On second thought, not particularly, since It's just doing a check to see if the door is already open or not
//    if (!this.blockCheck) {  //If it's not a door at all - or, it's just gone now
//       return false;
//    } else {
//       BlockState blockstate = this.entity.world.getBlockState(this.blockPosition);
//       if (!(blockstate.getBlock() instanceof DoorBlock)) { //One more check to see if it's the door
//          this.blockCheck = false; //Just like, another check I guess? Not sure.
//          return false;
//       } else {
//          return blockstate.get(DoorBlock.OPEN); //This returns a boolean - so if it's open, it can't destroy it - but that would be true? Don't really get it, but I get the concept.
//       }
//    }
// }
//Still don't know if this is important or not


   

   //FROM INTERACTDOOR - INTEGRATED
//   public void tick() {
//	      float f = (float)((double)((float)this.blockPosition.getX() + 0.5F) - this.entity.posX); //These, I assume are just to check whether or not 
//	      float f1 = (float)((double)((float)this.blockPosition.getZ() + 0.5F) - this.entity.posZ);//The entity walking through or breaking down the door is done with it.
//	      float f2 = this.entityPositionX * f + this.entityPositionZ * f1; //Maybe I could model an example scenario with graph paper
//	      if (f2 < 0.0F) {
//	         this.hasStoppedblockCheck = true; //we could probably also apply this to block interaction. I wish it wasn't used
//	      }
//
//	   }

}

