package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.Settings;
import com.mygdx.game.frame.*;
import com.mygdx.game.screens.Screens;

import java.util.Iterator;
import java.util.Random;

public class World {
    public static float WIDTH = Screens.WORLD_SCREEN_WIDTH;
    public static final float HEIGHT = Screens.WORLD_SCREEN_HEIGHT;
    public static final int STATE_RUNNING = 0;
    public static final int STATE_GAME_OVER = 1;
    public static final int STATE_PAUSED = 2;
    int state;

    SpaceShip oSpaceShip;
    Array<Boost> boosts = new Array<>();
    Array<Missile> missiles = new Array<>();
    Array<Bullet> shipBullets = new Array<>();
    Array<Bullet> alienBullets = new Array<>();
    Array<AlienShip> alienShips = new Array<>();

    Random oRan;
    int score;
    int currentLevel = 0;
    int missileCount = 5;
    int extraChanceDrop;
    int maxMissiles, maxBullets;
    int levelBullet;
    float probs;
    float alienLevel;

    public World() {
        oSpaceShip = new SpaceShip(WIDTH / 2f, 9.5f);
        oSpaceShip.lives = 5;
        extraChanceDrop = 5;
        maxMissiles = 5;
        maxBullets = 5;
        score = 0;
        currentLevel = 0;
        probs = alienLevel = 0;
        oRan = new Random((long) Gdx.app.getGraphics().getDeltaTime() * 10000);
        state = STATE_RUNNING;
        aggregateAliens();
    }

    private void aggregateAliens() {
        currentLevel++;
        
        if (currentLevel % 2f == 0) {
            maxMissiles++;
            maxBullets++;
        }
        float x;
        float y = 21f;
        int life = 1;

        boolean lifeAlterable = false;
        if (currentLevel > 2) {
            lifeAlterable = true;
            probs += 0.2f;
            alienLevel += .02f;
        }

        for (int col = 0; col < 6; col++) {
            y += 3.8;
            x = 1.5f;
            for (int ren = 0; ren < 6; ren++) {
                if (lifeAlterable)
                    life = oRan.nextInt(3) + 1 + (int) probs;//
                alienShips.add(new AlienShip(life, alienLevel, x, y));
                x += 4.5f;
            }
        }

    }

    public void update(float deltaTime, float accelX, boolean setDispatch, boolean setDispatchMissil) {
        updateShip(deltaTime, accelX);
        updateAlienShip(deltaTime);
        updateBullet(deltaTime, setDispatch);
        updateMissil(deltaTime, setDispatchMissil);
        updateBulletAlien(deltaTime);
        updateBoost(deltaTime);

        if (oSpaceShip.state != SpaceShip.SHIP_STATE_EXPLODE) {
            checkCollision();
        }
        checkGameOver();
        checkLevelEnd();
    }

    private void updateShip(float deltaTime, float accelX) {
        if (oSpaceShip.state != SpaceShip.SHIP_STATE_EXPLODE) {
            oSpaceShip.velocity.x = -accelX / Settings.speedSensitivity * SpaceShip.SHIP_MOVE_SPEED;
        }
        oSpaceShip.update(deltaTime);
    }

    private void updateAlienShip(float deltaTime) {

        Iterator<AlienShip> it = alienShips.iterator();
        while (it.hasNext()) {
            AlienShip oAlienShip = it.next();
            oAlienShip.update(deltaTime);
            if (oRan.nextInt(5000) < (1 + probs) && oAlienShip.state != AlienShip.EXPLODING) {
                float x = oAlienShip.position.x;
                float y = oAlienShip.position.y;
                alienBullets.add(new Bullet(x, y));
            }
            if (oAlienShip.state == AlienShip.EXPLODING && oAlienShip.stateTime > AlienShip.FULL_EXPLODE) {
                it.remove();
            }
            if (oAlienShip.position.y < 9.5f) {
                state = STATE_GAME_OVER;
            }

        }

    }

    private void updateBulletAlien(float deltaTime) {
        Iterator<Bullet> it = alienBullets.iterator();
        while (it.hasNext()) {
            Bullet oAlienBullet = it.next();
            if (oAlienBullet.position.y < -2)
                oAlienBullet.destroyed();
            oAlienBullet.update(deltaTime);
            if (oAlienBullet.state == Bullet.STATE_EXPLODE) {
                it.remove();
            }

        }

    }

    private void updateBullet(float deltaTime, boolean setDispatch) {
        float x = oSpaceShip.position.x;
        float y = oSpaceShip.position.y + 1;

        if (setDispatch && shipBullets.size < maxBullets) {
            shipBullets.add(new Bullet(x, y, levelBullet));
        }

        Iterator<Bullet> it1 = shipBullets.iterator();
        while (it1.hasNext()) {
            Bullet oBullet = it1.next();
            if (oBullet.position.y > HEIGHT + 2)
                oBullet.destroyed();
            oBullet.update(deltaTime);
            if (oBullet.state == Bullet.STATE_EXPLODE) {
                it1.remove();
            }
        }
    }

    private void updateMissil(float deltaTime, boolean setDispatchMissil) {
        int len = missiles.size;
        if (setDispatchMissil && missileCount > 0 && len < maxMissiles) {
            float x = oSpaceShip.position.x;
            float y = oSpaceShip.position.y + 1;
            missiles.add(new Missile(x, y));
            missileCount--;
            Assets.playSound(Assets.missilFire, 0.15f);
        }

        Iterator<Missile> it = missiles.iterator();
        while (it.hasNext()) {
            Missile oMissile = it.next();
            if (oMissile.position.y > HEIGHT + 2 && oMissile.state != Missile.STATE_EXPLODE)
                oMissile.hitTarget();
            oMissile.update(deltaTime);
            if (oMissile.state == Missile.STATE_EXPLODE && oMissile.stateTime > Missile.FULL_EXPLODE) {
                it.remove();

            }

        }
    }

    private void updateBoost(float deltaTime) {
        Iterator<Boost> it = boosts.iterator();
        while (it.hasNext()) {
            Boost oBoost = it.next();
            oBoost.update(deltaTime);
            if (oBoost.position.y < -2) {
                it.remove();
            }

        }
    }

    private void checkCollision() {
    	checkShipCollidedAliens();
    	checkCollidedAliens();
        checkCollisionAlienMissil();
        checkCollisionBoostSHIP();
    }

    private void checkShipCollidedAliens() {
        for (Bullet oAlienBullet : alienBullets) {
            if (Intersector.overlaps(oSpaceShip.boundsRectangle, oAlienBullet.boundsRectangle) && oSpaceShip.state != SpaceShip.SHIP_STATE_EXPLODE && oSpaceShip.state != SpaceShip.SHIP_STATE_BEING_HIT) {
                oSpaceShip.beingHit();
                oAlienBullet.hitTarget(1);
            }
        }
    }

    private void checkCollidedAliens() {
        for (Bullet oBullet : shipBullets) {
            for (AlienShip oAlien : alienShips) {
                if (Intersector.overlaps(oAlien.boundsCircle, oBullet.boundsRectangle) && (oAlien.state != AlienShip.EXPLODING)) {
                    oBullet.hitTarget(oAlien.moveLeft);
                    oAlien.beingHit();
                    if (oAlien.state == AlienShip.EXPLODING) {
                        score += oAlien.spacing;
                        aggregateBoost(oAlien.position.x, oAlien.position.y);
                        Assets.playSound(Assets.explosionSound, 0.6f);
                    }
                }

            }
        }
    }

    private void checkCollisionAlienMissil() {
        for (Missile oMissile : missiles) {
            for (AlienShip oAlien : alienShips) {
                if (oMissile.state == Missile.STATE_DISPATCH && Intersector.overlaps(oAlien.boundsCircle, oMissile.boundsRectangle) && oAlien.state != AlienShip.EXPLODING) {
                    oMissile.hitTarget();
                    oAlien.beingHit();
                    if (oAlien.state == AlienShip.EXPLODING) {
                        score += oAlien.spacing;
                        aggregateBoost(oAlien.position.x, oAlien.position.y);
                        Assets.playSound(Assets.explosionSound, 0.6f);
                    }
                }
                if (oMissile.state == Missile.STATE_EXPLODE && Intersector.overlaps(oAlien.boundsCircle, oMissile.boundsCircle) && oAlien.state != AlienShip.EXPLODING) {
                    oAlien.beingHit();
                    if (oAlien.state == AlienShip.EXPLODING) {
                        score += oAlien.spacing;
                        aggregateBoost(oAlien.position.x, oAlien.position.y);
                        Assets.playSound(Assets.explosionSound, 0.6f);
                    }
                }
            }
        }
    }

    private void checkCollisionBoostSHIP() {
        Iterator<Boost> it = boosts.iterator();
        while (it.hasNext()) {
            Boost oBoost = it.next();
            if (Intersector.overlaps(oBoost.boundsCircle, oSpaceShip.boundsRectangle) && oSpaceShip.state != SpaceShip.SHIP_STATE_EXPLODE) {
                switch (oBoost.type) {
                    case Boost.LIFE_EXTRA:
                        oSpaceShip.hitExtraLife();
                        break;
                    case Boost.UPGRADE_CRAFT:
                        levelBullet++;
                        break;
                    case Boost.MISSIL_EXTRA:
                        missileCount++;
                        break;
                    default:
                    case Boost.SHIELD:
                        oSpaceShip.hitCount();
                        break;
                }
                it.remove();
                Assets.playSound(Assets.coinSound);
            }

        }

    }

    private void aggregateBoost(float x, float y) {
        if (oRan.nextInt(100) < 5 + extraChanceDrop) {
            switch (oRan.nextInt(4)) {
                case Boost.LIFE_EXTRA:
                    boosts.add(new Boost(Boost.LIFE_EXTRA, x, y));
                    break;
                case 1:
                    boosts.add(new Boost(Boost.UPGRADE_CRAFT, x, y));
                    break;
                case Boost.MISSIL_EXTRA:
                    boosts.add(new Boost(Boost.MISSIL_EXTRA, x, y));
                    break;
                default:// Boost.SHIELD
                    boosts.add(new Boost(Boost.SHIELD, x, y));
                    break;
            }
        }
    }

    private void checkGameOver() {
        if (oSpaceShip.state == SpaceShip.SHIP_STATE_EXPLODE && oSpaceShip.stateTime > SpaceShip.FULL_EXPLODE) {
            oSpaceShip.position.x = 200;
            state = STATE_GAME_OVER;
        }
    }

    private void checkLevelEnd() {
        if (alienShips.size == 0) {
            shipBullets.clear();
            alienBullets.clear();
            aggregateAliens();
        }

    }

}
