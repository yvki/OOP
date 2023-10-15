package com.zombies.game;

public class kingdom {
	  private String name;
	  private float speed;
	  private float xcoords;
	  
	  public String getName() {
			return name;
	  }
	  
	  public float getSpeed() {
	      return speed;
	  }
	  
	  public float getX() {
		  return xcoords;
	  }
	  
	  public void setName(String newName) {
		  this.name = newName;
	  }
	  
	  public void setSpeed(float newSpeed) {
	      this.speed = newSpeed;
	  }
	  
	  public void setX(float newX) {
		  this.xcoords = newX;
	  } 
}

//class dinosaurs extends kingdom {
//	 private String moveset;
//	 
//	 public String getMoveset() {
//		return moveset;
//	 }
//	 
//	 public void setMoveset(String newMoveset) {
//		this.moveset = newMoveset;
//	 }
//}

//class turtles extends kingdom {
//	private String action;
//	 
//	public String getAction() {
//		return action;
//	}
//	 
//	public void setAction(String newAction) {
//		this.action = newAction;
//	}
//}

//class kings extends kingdom {
//	private String sound;
//	 
//	public String getSound() {
//		return sound;
//	}
//	 
//	public void setSound(String newSound) {
//		this.sound = newSound;
//	}
//}

