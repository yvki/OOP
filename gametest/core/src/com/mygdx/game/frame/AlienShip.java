package com.mygdx.game.frame;

public class AlienShip extends DynamicGameObject {
	public static final float RADIUS = 1.5f;
	public static final float DRAW_WIDTH = 3.5f;
	public static final float DRAW_HEIGHT = 3.5f;
	public static final int MOVE_SIDES = 0;
	public static final int MOVE_DOWN = 2;
	public static final int EXPLODING = 3;
	public static final float SPEED = 4f;
	public static final float SPEED_DOWN = -3.5f;
	public static final float MOVE_RANGE_SIDES = 6.7f;
	public static final float MOVE_RANGE_DOWN = 1.2f;
	public static final float FULL_EXPLODE = 0.05f * 19;
	public final int spacing_SIMPLE = 10;
	public int moveLeft;
	public int spacing;
	public float stateTime;
	public int state;
	float movedDistance;
	float rateIncrease;

	public AlienShip(int vida, float rateIncrease, float x, float y) {
		super(x, y, RADIUS);
		stateTime = 0;
		state = MOVE_SIDES;
		velocity.set(SPEED, SPEED_DOWN);
		movedDistance = 0;
		spacing = spacing_SIMPLE;
		moveLeft = vida;
		this.rateIncrease = 1 + rateIncrease;
	}

	public void update(float deltaTime) {
		if (state != EXPLODING) {
			switch (state) {
				case MOVE_SIDES:
					position.x += velocity.x * deltaTime * rateIncrease;
					movedDistance += Math.abs(velocity.x * deltaTime) * rateIncrease;
					if (movedDistance > MOVE_RANGE_SIDES) {
						state = MOVE_DOWN;
						velocity.x *= -1;
						movedDistance = 0;
					}
					break;
				case MOVE_DOWN:
					position.y += velocity.y * deltaTime * rateIncrease;
					movedDistance += Math.abs(velocity.x * deltaTime) * rateIncrease;
					if (movedDistance > MOVE_RANGE_DOWN) {
						state = MOVE_SIDES;
						movedDistance = 0;
					}
					break;
			}
		}

		boundsCircle.x = position.x;
		boundsCircle.y = position.y;
		stateTime += deltaTime;
	}

	public void beingHit(int poderBala) {
		moveLeft--;
		if (moveLeft <= 0) {
			state = EXPLODING;
			velocity.add(0, 0);
			stateTime = 0;
		}
	}

	public void beingHit() {
		beingHit(1);
	}
}
