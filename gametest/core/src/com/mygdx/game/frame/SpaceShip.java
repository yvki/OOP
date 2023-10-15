package com.mygdx.game.frame;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.game.World;

public class SpaceShip extends DynamicGameObject {
    public static final float DRAW_WIDTH = 4.5f;
    public static final float DRAW_HEIGHT = 3.6f;
    public static final float WIDTH = 4f;
    public static final float HEIGHT = 2.5f;
    public static final float SHIP_MOVE_SPEED = 50;
    public static final int SHIP_STATE_NORMAL = 0;
    public static final int SHIP_STATE_EXPLODE = 1;
    public static final int SHIP_STATE_BEING_HIT = 2;
    public static final float FULL_EXPLODE = 0.05f * 19;
    public static final float FULL_BEING_HIT = 0.05f * 21; 
    public int livesCount;
    public int lives;
    public int state;
    public float stateTime;

    public SpaceShip(float x, float y) {
        super(x, y, WIDTH, HEIGHT);
        lives = 3;
        livesCount = 1;
        state = SHIP_STATE_NORMAL;
        Gdx.app.log("Player", "Normal spacecraft simulation");
    }

    public void update(float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        boundsRectangle.x = position.x - boundsRectangle.width / 2;
        boundsRectangle.y = position.y - boundsRectangle.height / 2;

        if (state == SHIP_STATE_BEING_HIT && stateTime > FULL_BEING_HIT) {
            state = SHIP_STATE_NORMAL;
            stateTime = 0;
            Gdx.app.log("Player", "Hit by Alien Bullet");
        }

        if (position.x < WIDTH / 2)
            position.x = WIDTH / 2;
        if (position.x > World.WIDTH - WIDTH / 2)
            position.x = World.WIDTH - WIDTH / 2;
        stateTime += deltaTime;
    }

    public void beingHit() {
        if (livesCount > 0) {
        	livesCount--;
        } else {
        	lives--;
            if (lives <= 0) {
                state = SHIP_STATE_EXPLODE;
                stateTime = 0;
                velocity.set(0, 0);
            } else {
                state = SHIP_STATE_BEING_HIT;
                stateTime = 0;
            }
        }
    }

    public void hitExtraLife() {
        if (lives < 99) {
        	lives++;
        }
    }

    public void hitCount() {
        stateTime = 0;
        livesCount = 3;
    }

}
