package com.zombies.game;

public class turtles extends kingdom {
	private String action;
	
	public turtles(String newName, float newSpeed, float newX, String newAction) {
        super(newName, newSpeed, newX);
        this.action = newAction;
    }
	
	public String getAction() {
		return action;
	}
	 
	public void setAction(String newAction) {
		this.action = newAction;
	}
}
