package com.zombies.game;

public class NonCollidableEntity {
	private String noncollision;
	
	public NonCollidableEntity(String newNonCollision) {
        this.noncollision = newNonCollision;
    }
		
	public String getNonCollision() {
		return noncollision;
	}
		
	public void setNonCollision(String newNonCollision) {
		this.noncollision = newNonCollision;
	}
}
