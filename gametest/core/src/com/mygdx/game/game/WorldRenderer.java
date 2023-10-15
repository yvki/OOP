package com.mygdx.game.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Assets;
import com.mygdx.game.Settings;
import com.mygdx.game.frame.*;
import com.mygdx.game.screens.Screens;


public class WorldRenderer {
    static final float MIN_SCREEN_WIDTH = Screens.WORLD_SCREEN_WIDTH;
    static final float MIN_SCREEN_HEIGHT = Screens.WORLD_SCREEN_HEIGHT;

    World oWorld;
    OrthographicCamera cam;
    SpriteBatch batch;

    public WorldRenderer(SpriteBatch batch, World oWorld) {
        this.oWorld = oWorld;
        this.cam = new OrthographicCamera(MIN_SCREEN_WIDTH, MIN_SCREEN_HEIGHT);
        this.cam.position.set(MIN_SCREEN_WIDTH / 2f, MIN_SCREEN_HEIGHT / 2f, 0);
        this.batch = batch;
    }

    public void render(float deltaTime) {
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        renderBackground(deltaTime);
        renderObjects();
    }

    private void renderBackground(float deltaTime) {
        batch.disableBlending();
        batch.begin();
        batch.end();
        if (oWorld.state == World.STATE_RUNNING) {
            Assets.parallaxRegion.render(deltaTime);
        } else {
            Assets.parallaxRegion.render(0);
        }

    }

    private void renderObjects() {
        batch.enableBlending();
        batch.begin();
        renderNave();
        renderAliens();
        renderShipBullet();
        renderAlienBullet();
        renderMissil();
        renderBoost();
        batch.end();
        if (Settings.drawDebugLines) {
            renderDebugBounds();
        }
    }

    private void renderNave() {
        TextureRegion keyFrame;
        if (oWorld.oSpaceShip.state == SpaceShip.SHIP_STATE_NORMAL) {
            if (oWorld.oSpaceShip.velocity.x < -3)
                keyFrame = Assets.moveLeft;
            else if (oWorld.oSpaceShip.velocity.x > 3)
                keyFrame = Assets.moveRight;
            else
                keyFrame = Assets.move;
        } else {
            keyFrame = Assets.explosion.getKeyFrame(oWorld.oSpaceShip.stateTime, false);
        }
        batch.draw(keyFrame, oWorld.oSpaceShip.position.x - SpaceShip.DRAW_WIDTH / 2f, oWorld.oSpaceShip.position.y - SpaceShip.DRAW_HEIGHT
                / 2f, SpaceShip.DRAW_WIDTH, SpaceShip.DRAW_HEIGHT);

        if (oWorld.oSpaceShip.livesCount > 0) {
            batch.draw(Assets.shield.getKeyFrame(oWorld.oSpaceShip.stateTime, true), oWorld.oSpaceShip.position.x - 5.5f,
                    oWorld.oSpaceShip.position.y - 5.5f, 11, 11);
        }

    }

    private void renderAliens() {
        int len = oWorld.alienShips.size;
        for (int i = 0; i < len; i++) {
            AlienShip oAlienShip = oWorld.alienShips.get(i);
            TextureRegion keyFrame;
            if (oAlienShip.state == AlienShip.EXPLODING) {
                keyFrame = Assets.explosion.getKeyFrame(oAlienShip.stateTime, false);
            } else {
                if (oAlienShip.moveLeft >= 10)
                    keyFrame = Assets.alien4;
                else if (oAlienShip.moveLeft >= 5)
                    keyFrame = Assets.alien3;
                else if (oAlienShip.moveLeft >= 2)
                    keyFrame = Assets.alien2;
                else
                    keyFrame = Assets.alien1;
            }

            batch.draw(keyFrame, oAlienShip.position.x - AlienShip.DRAW_WIDTH / 2f, oAlienShip.position.y
                    - AlienShip.DRAW_HEIGHT / 2f, AlienShip.DRAW_WIDTH, AlienShip.DRAW_HEIGHT);
        }

    }

    private void renderShipBullet() {
        for (int i = 0; i < oWorld.shipBullets.size; i++) {
            Bullet bullet = oWorld.shipBullets.get(i);

            if (bullet.level <= 1) {
                batch.draw(Assets.bulletNormal, bullet.position.x - 0.15f, bullet.position.y - 0.45f, 0.3f, 0.9f);
            } else if (bullet.level == 2) {
                batch.draw(Assets.bulletLevel1, bullet.position.x - 1.05f, bullet.position.y - 0.75f, 2.1f, 1.5f);
            } else if (bullet.level == 3) {
                batch.draw(Assets.bulletLevel2, bullet.position.x - 1.05f, bullet.position.y - 0.75f, 2.1f, 1.5f);
            } else if (bullet.level == 4) {
                batch.draw(Assets.bulletLevel3, bullet.position.x - 1.05f, bullet.position.y - 0.75f, 2.1f, 1.5f);
            } else {
                batch.draw(Assets.bulletLevel4, bullet.position.x - 1.05f, bullet.position.y - 0.75f, 2.1f, 1.5f);
            }

        }
    }


    private void renderAlienBullet() {
        int len = oWorld.alienBullets.size;
        for (int i = 0; i < len; i++) {
            Bullet oAlienBullet = oWorld.alienBullets.get(i);
            batch.draw(Assets.bulletNormalEnemy, oAlienBullet.position.x - 0.15f, oAlienBullet.position.y - 0.45f, 0.3f,
                    0.9f);
        }
    }

    private void renderMissil() {
        int len = oWorld.missiles.size;
        for (int i = 0; i < len; i++) {
            Missile oMissile = oWorld.missiles.get(i);
            float widht, heigth;
            TextureRegion keyFrame;
            switch (oMissile.state) {
                case Missile.STATE_DISPATCH:
                    keyFrame = Assets.misil.getKeyFrame(oMissile.stateTime, true);
                    widht = .8f;
                    heigth = 2.5f;
                    break;
                default:
                case Missile.STATE_EXPLODE:
                    keyFrame = Assets.explosion.getKeyFrame(oMissile.stateTime, false);
                    widht = heigth = 15.0f;
                    break;

            }
            batch.draw(keyFrame, oMissile.position.x - widht / 2f, oMissile.position.y - heigth / 2f, widht, heigth);

        }
    }

    private void renderBoost() {
        int len = oWorld.boosts.size;
        for (int i = 0; i < len; i++) {
            Boost oBoost = oWorld.boosts.get(i);
            TextureRegion keyFrame;

            switch (oBoost.type) {
                case Boost.LIFE_EXTRA:
                    keyFrame = Assets.upgLife;
                    break;
                case Boost.UPGRADE_CRAFT:
                    keyFrame = Assets.boost1;
                    break;
                case Boost.MISSIL_EXTRA:
                    keyFrame = Assets.boost2;
                    break;
                default:
                    keyFrame = Assets.boost3;

            }

            batch.draw(keyFrame, oBoost.position.x - Boost.DRAW_SIZE / 2f, oBoost.position.y - Boost.DRAW_SIZE / 2f,
                    Boost.DRAW_SIZE, Boost.DRAW_SIZE);
        }
    }

    private void renderDebugBounds() {
        ShapeRenderer render = new ShapeRenderer();
        render.setProjectionMatrix(cam.combined);
        render.begin(ShapeType.Line);

        Rectangle shipBoundary = oWorld.oSpaceShip.boundsRectangle;
        render.rect(shipBoundary.x, shipBoundary.y, shipBoundary.width, shipBoundary.height);

        for (AlienShip obj : oWorld.alienShips) {
            Circle objBounds = obj.boundsCircle;
            render.circle(objBounds.x, objBounds.y, objBounds.radius);
        }

        for (Boost obj : oWorld.boosts) {
            Circle objBounds = obj.boundsCircle;
            render.circle(objBounds.x, objBounds.y, objBounds.radius);
        }

        render.end();

    }

}
