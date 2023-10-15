package com.zombies.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	public Screen menuScreen,mainScreen;
	
	@Override
	public void create() {
		menuScreen = new menuScreen();
		setScreen(menuScreen);
	}
}
