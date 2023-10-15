package com.zombies.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Screen;


public class menuScreen extends InputAdapter implements Screen {
	Stage stage1, stage2, stage3;
	SpriteBatch batch;
	Texture player1, player2, player3;
	
	dinosaurs Yoshi = new dinosaurs();
	turtles Koopa = new turtles();
	kings Bowser = new kings();
	
	float player1x = Yoshi.getX();
	float player2x = Koopa.getX();
	float player3x = Bowser.getX();
	
	//float player1y = Yoshi.getY();
	//float player2y = Koopa.getY();
	//float player3y = Bowser.getY();
	
	float player1y = 30;
	float player2y = 170;
	float player3y = 310;
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		player1 = new Texture(Gdx.files.internal("still.png"));
		Yoshi.setSpeed(60.0f);
		Yoshi.setName("Yoshi");
		Yoshi.setX(10);
		stage1 = new Stage();
		Gdx.input.setInputProcessor(stage1);
		
		player2 = new Texture(Gdx.files.internal("still2.png"));
		Koopa.setSpeed(30.0f);
		Koopa.setName("Koopa");
		Koopa.setX(10);
		stage2 = new Stage();
		Gdx.input.setInputProcessor(stage2);
		
		player3 = new Texture(Gdx.files.internal("still3.png"));
		Bowser.setSpeed(90.0f);
		Bowser.setName("Bowser");
		Bowser.setX(10);
		stage3 = new Stage();
		Gdx.input.setInputProcessor(stage3);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(208/255f, 248/255f, 153/255f, 1);
		ScreenUtils.clear(208/255f, 248/255f, 153/255f, 1);
		batch.begin();
		stage1.draw();
		batch.draw(player1, player1x, player1y);
		stage2.draw();
		batch.draw(player2, player2x, player2y);
		stage3.draw();
		batch.draw(player3, player3x, player3y);
		
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			System.out.println(Yoshi.getName()+Koopa.getName()+Bowser.getName()+" go up");
			player1y += Gdx.graphics.getDeltaTime()*Yoshi.getSpeed();
			player2y += Gdx.graphics.getDeltaTime()*Koopa.getSpeed();
			player3y += Gdx.graphics.getDeltaTime()*Bowser.getSpeed();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			System.out.println(Yoshi.getName()+Koopa.getName()+Bowser.getName()+" go down");
			player1y -= Gdx.graphics.getDeltaTime()*Yoshi.getSpeed();
			player2y -= Gdx.graphics.getDeltaTime()*Koopa.getSpeed();
			player3y -= Gdx.graphics.getDeltaTime()*Bowser.getSpeed();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			System.out.println(Yoshi.getName()+Koopa.getName()+Bowser.getName()+" go left");
			player1x -= Gdx.graphics.getDeltaTime()*Yoshi.getSpeed();
			player2x -= Gdx.graphics.getDeltaTime()*Koopa.getSpeed();
			player3x -= Gdx.graphics.getDeltaTime()*Bowser.getSpeed();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			System.out.println(Yoshi.getName()+Koopa.getName()+Bowser.getName()+" go right");
			player1x += Gdx.graphics.getDeltaTime()*Yoshi.getSpeed();
			player2x += Gdx.graphics.getDeltaTime()*Koopa.getSpeed();
			player3x += Gdx.graphics.getDeltaTime()*Bowser.getSpeed();
		}
		
		batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}
	
	@Override
	public void hide() {
		
	}
	
	@Override
	public void dispose() {
		
	}
}
