package com.zombies.game;

public class CollidableEntity {
	private String collision;
	
	public CollidableEntity(String newCollision) {
        this.collision = newCollision;
    }
	
	public String getCollision() {
		return collision;
	}
	
	public void setCollision(String newCollision) {
		this.collision = newCollision;
	}
}
