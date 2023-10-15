package com.zombies.game;

public class kings extends kingdom {
	private String sound;
	
	public kings(String newName, float newSpeed, float newX, String newSound) {
        super(newName, newSpeed, newX);
        this.sound = newSound;
    }
	
	public String getSound() {
		return sound;
	}
 
	public void setSound(String newSound) {
		this.sound = newSound;
	}
}
