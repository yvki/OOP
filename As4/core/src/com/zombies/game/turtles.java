package com.zombies.game;

public class turtles extends kingdom<Object> implements iNonCollidable<String> {
	private String action;
	private String noncollision;
	
	public turtles(String newName, float newSpeed, float newX, String newAction, String newNonCollision) {
        super(newName, newSpeed, newX);
        this.action = newAction;
        this.noncollision = newNonCollision;
    }
	
	public String getAction() {
		return action;
	}
	 
	public void setAction(String newAction) {
		this.action = newAction;
	}
	
	public String getNonCollision() {
		return noncollision;
	}
	
	public void setNonCollision(String newNonCollision) {
		this.noncollision = newNonCollision;
	}
}
