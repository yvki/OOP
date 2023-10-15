package com.zombies.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Screen;

public class menuScreen extends InputAdapter implements Screen {
	Stage stage1, stage2, stage3, stage4;
	SpriteBatch batch;
	Texture player1, player2, player3;
	Texture brick;
	
	
	dinosaurs Yoshi = new dinosaurs("Yoshi",60.0f,10, "Flutter Kick", "Collided");
	turtles Koopa = new turtles("Koopa",30.0f,10,"Troopa Dance", "Never Collided");
	kings Bowser = new kings("Bowser",90.0f,10,"Grrrrrrrrr", "Collided");
	//CollidableEntity dkc = new CollidableEntity("Collided");
	//NonCollidableEntity tnc = new NonCollidableEntity("Never Collided");
	
	Rectangle dino_rect;
	Rectangle turt_rect;
	Rectangle king_rect;
	Rectangle brick_rect;
	
	float prevdx;
	float prevdy;
	//float prevtx;
	//float prevty;
	float prevkx;
	float prevky;
	
	float player1x = Yoshi.getX();
	float player2x = Koopa.getX();
	float player3x = Bowser.getX();
	
	//float player1y = Yoshi.getY();
	//float player2y = Koopa.getY();
	//float player3y = Bowser.getY();
	
	float player1y = 30;
	float player2y = 170;
	float player3y = 310;
	float brickx = 250;
    float bricky = 250;
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		player1 = new Texture(Gdx.files.internal("still.png"));
		dino_rect = new Rectangle(player1x, player1y, player1.getWidth(), player1.getHeight());
		prevdx = 0;
		prevdy = 0;
		//Yoshi.setSpeed(60.0f);
		//Yoshi.setName("Yoshi");
		//Yoshi.setX(10);
		//Yoshi.setMoveset("Flutter Kick");
		//dkc.setCollision("Collided");
		stage1 = new Stage();
		Gdx.input.setInputProcessor(stage1);
		
		player2 = new Texture(Gdx.files.internal("still2.png"));
		turt_rect = new Rectangle(player2x, player2y, player2.getWidth(), player2.getHeight());
		//prevtx = player2x;
	    //prevty = player2y;
		//Koopa.setSpeed(30.0f);
		//Koopa.setName("Koopa");
		//Koopa.setX(10);
		//Koopa.setAction("Troopa Dance");
		//tnc.setNonCollision("Never Collided");
		stage2 = new Stage();
		Gdx.input.setInputProcessor(stage2);
		
		player3 = new Texture(Gdx.files.internal("still3.png"));
		king_rect = new Rectangle(player3x, player3y, player3.getWidth(), player3.getHeight());
		prevkx = 0;
		prevky = 0;
		//Bowser.setSpeed(90.0f);
		//Bowser.setName("Bowser");
		//Bowser.setX(10);
		//Bowser.setSound("Grrrrrrrrr");
		//dkc.setCollision("Collided");
		stage3 = new Stage();
		Gdx.input.setInputProcessor(stage3);
		
		brick = new Texture(Gdx.files.internal("still4.png"));
		brick_rect = new Rectangle(brickx, bricky, brick.getWidth(), brick.getHeight());
		stage4 = new Stage();
		Gdx.input.setInputProcessor(stage4);
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
		stage4.draw();
		batch.draw(brick, brickx, bricky);
		
		if (brick_rect.overlaps(dino_rect)) {
			System.out.println(Yoshi.getName()+" "+Yoshi.getCollision());
			player1y = prevdy;
			player1x = prevdx;
		}
		if (brick_rect.overlaps(turt_rect)) {
			System.out.println(Koopa.getName()+" "+Koopa.getNonCollision());
		}
		if (brick_rect.overlaps(king_rect)) { 
			System.out.println(Bowser.getName()+" "+Bowser.getCollision());
			player3y = prevky;
			player3x = prevkx;
		}	
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			System.out.println(Yoshi.getName()+", "+Koopa.getName()+", "+Bowser.getName()+" go up");
			prevdy = player1y;
			//prevty = player2y;
			prevky = player3y;
			player1y += Gdx.graphics.getDeltaTime()*Yoshi.getSpeed();
			player2y += Gdx.graphics.getDeltaTime()*Koopa.getSpeed();
			player3y += Gdx.graphics.getDeltaTime()*Bowser.getSpeed();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			System.out.println(Yoshi.getName()+", "+Koopa.getName()+", "+Bowser.getName()+" go down");
			prevdy = player1y;
			//prevty = player2y;
			prevky = player3y;
			player1y -= Gdx.graphics.getDeltaTime()*Yoshi.getSpeed();
			player2y -= Gdx.graphics.getDeltaTime()*Koopa.getSpeed();
			player3y -= Gdx.graphics.getDeltaTime()*Bowser.getSpeed();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			System.out.println(Yoshi.getName()+", "+Koopa.getName()+", "+Bowser.getName()+" go left");
			prevdx = player1x;
			//prevtx = player2x;
			prevkx = player3x;
			player1x -= Gdx.graphics.getDeltaTime()*Yoshi.getSpeed();
			player2x -= Gdx.graphics.getDeltaTime()*Koopa.getSpeed();
			player3x -= Gdx.graphics.getDeltaTime()*Bowser.getSpeed();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			System.out.println(Yoshi.getName()+", "+Koopa.getName()+", "+Bowser.getName()+" go right");
			prevdx = player1x;
			//prevtx = player2x;
			prevkx = player3x;
			player1x += Gdx.graphics.getDeltaTime()*Yoshi.getSpeed();
			player2x += Gdx.graphics.getDeltaTime()*Koopa.getSpeed();
			player3x += Gdx.graphics.getDeltaTime()*Bowser.getSpeed();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
			System.out.println(Yoshi.getName()+" used "+Yoshi.getMoveset());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.K)) {
			System.out.println(Koopa.getName()+" did a "+Koopa.getAction());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.B)) {
			System.out.println(Bowser.getName()+" yelled "+Bowser.getSound());
		}
		dino_rect = new Rectangle(player1x, player1y, player1.getWidth(), player1.getHeight());
		turt_rect = new Rectangle(player2x, player2y, player2.getWidth(), player2.getHeight());
		king_rect = new Rectangle(player3x, player3y, player3.getWidth(), player3.getHeight());
		brick_rect = new Rectangle(brickx, bricky, brick.getWidth(), brick.getHeight());
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
