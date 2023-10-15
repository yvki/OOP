package com.zombies.game;

public class dinosaurs extends kingdom {
	private String moveset;
	
	public dinosaurs(String newName, float newSpeed, float newX, String newMoveset) {
        super(newName, newSpeed, newX);
        this.moveset = newMoveset;
    }
	
	public String getMoveset() {
		return moveset;
	}
	 
	public void setMoveset(String newMoveset) {
		this.moveset = newMoveset;
	}
}
