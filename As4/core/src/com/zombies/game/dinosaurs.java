package com.zombies.game; 

public class dinosaurs extends kingdom<Object> implements iCollidable<String> {
	private String moveset;
	private String collision;
	
	public dinosaurs(String newName, float newSpeed, float newX, String newMoveset, String newCollision) {
        super(newName, newSpeed, newX);
        this.moveset = newMoveset;
        this.collision = newCollision;
    }
	
	public String getMoveset() {
		return moveset;
	}
	 
	public void setMoveset(String newMoveset) {
		this.moveset = newMoveset;
	}
	
	public String getCollision() {
		return collision;
	}
	
	public void setCollision(String newCollision) {
		this.collision = newCollision;
	}
}
