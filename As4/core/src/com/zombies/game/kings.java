package com.zombies.game;

public class kings extends kingdom<Object> implements iCollidable<String> {
	private String sound;
	private String collision;
	
	public kings(String newName, float newSpeed, float newX, String newSound, String newCollision) {
        super(newName, newSpeed, newX);
        this.sound = newSound;
        this.collision = newCollision;
    }
	
	public String getSound() {
		return sound;
	}
 
	public void setSound(String newSound) {
		this.sound = newSound;
	}
	
	public String getCollision() {
		return collision;
	}
	
	public void setCollision(String newCollision) {
		this.collision = newCollision;
	}
}
