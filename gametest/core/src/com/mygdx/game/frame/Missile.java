package com.mygdx.game.frame;

//import java.util.ArrayList;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.utils.Array;

public class Missile extends DynamicGameObject {
    public static final float WIDTH = 0.4f;
    public static final float HEIGHT = 1.4f;
    public static final float RADIO_EXPLOSION = 7.5f;
    public final float Velo = 30;
    public static final float FULL_EXPLODE = 0.05f * 19;
    public final static int STATE_DISPATCH = 0;
    public final static int STATE_EXPLODE = 1;
    public float stateTime;
    public int state;

    public Missile(float x, float y) {
        super(x, y, WIDTH, HEIGHT);
        boundsCircle = new Circle(position.x, position.y, RADIO_EXPLOSION);
        state = STATE_DISPATCH;
        stateTime = 0;
        velocity.set(0, Velo);
    }

    public void update(float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
        boundsRectangle.x = position.x - WIDTH / 2;
        boundsRectangle.y = position.y - HEIGHT / 2;
        boundsCircle.x = position.x;
        boundsCircle.y = position.y;
        stateTime += deltaTime;
    }

    public void hitTarget() {
        velocity.set(0, 0);
        stateTime = 0;
        state = STATE_EXPLODE;
    }

    public Vector2 getVelocity() {
        Vector2 vel = new Vector2(velocity.x, velocity.y);
        return vel;
    }

}
