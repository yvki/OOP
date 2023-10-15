package com.mygdx.game.frame;

public class Bullet extends DynamicGameObject {
    public static final float WIDTH = 2.1f;
    public static final float HEIGHT = 1.5f;
    public final float SPEED = 30;
    public final static int STATE_DISPATCH = 0;
    public final static int STATE_EXPLODE = 1;
    public int level = 1;
    public float stateTime;
    public int state;
    
    //Spaceship bullet
    public Bullet(float x, float y, int boostLevel) {
        super(x, y, WIDTH, HEIGHT);
        state = STATE_DISPATCH;
        stateTime = 0;
        velocity.set(0, SPEED);
        this.level += boostLevel;
    }
    
    //Alienship bullet
    public Bullet(float x, float y) {
        super(x, y, WIDTH, HEIGHT);
        state = STATE_DISPATCH;
        stateTime = 0;
        velocity.set(0, -SPEED);
    }

    public void update(float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        boundsRectangle.x = position.x - WIDTH / 2;
        boundsRectangle.y = position.y - HEIGHT / 2;
        stateTime += deltaTime;
    }

    public void hitTarget(int lifeTarget) {
        level -= lifeTarget;
        if (level <= 0) {
            velocity.set(0, 0);
            stateTime = 0;
            state = STATE_EXPLODE;
        }
    }

    public void destroyed() {
        velocity.set(0, 0);
        stateTime = 0;
        state = STATE_EXPLODE;
    }

}
