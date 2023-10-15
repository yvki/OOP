package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Settings;
import com.mygdx.game.game.GameScreen;

public class MainMenuScreen extends Screens {

    TextButton btPlay, btSettings;

    Label lbHighestScore;

    ImageButton btSound, btMusic;
    Image elipseIzq;

    public MainMenuScreen(final MyGdxGame game) {
        super(game);

        Table titleSpacing = new Table();
        titleSpacing.setBackground(Assets.titleMenu);
        Label title = new Label(Assets.idioms.get("game_title"), new LabelStyle(Assets.font60, Color.GREEN));
        title.setAlignment(Align.center);
        titleSpacing.setSize(265, 100);
        titleSpacing.setPosition((SCREEN_WIDTH - 265) / 2f, SCREEN_HEIGHT - 110);
        titleSpacing.add(title).expand().center();

        lbHighestScore = new Label("", new LabelStyle(Assets.font10, Color.GREEN));
        lbHighestScore.setWidth(SCREEN_WIDTH);
        lbHighestScore.setAlignment(Align.center);
        lbHighestScore.setPosition(0, SCREEN_HEIGHT - 120);

        btPlay = new TextButton(Assets.idioms.get("play"), Assets.styleTextButtonMenu);
        btPlay.setSize(250, 50);
        btPlay.setPosition(0, 280);
        btPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new GameScreen(game));

            }
        });

        btSettings = new TextButton(Assets.idioms.get("settings"), Assets.styleTextButtonMenu);
        btSettings.setSize(300, 50);
        btSettings.setPosition(0, 210);
        btSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new SettingsScreen(game));
            }
        });
        
        btSound = new ImageButton(Assets.buttonSoundOn, Assets.buttonSoundOff, Assets.buttonSoundOff);
        btSound.setSize(40, 40);
        btSound.setPosition(2, 2);
        if (!Settings.soundEnabled)
            btSound.setChecked(true);
        btSound.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Settings.soundEnabled = !Settings.soundEnabled;
                Assets.playSound(Assets.clickSound);
                btSound.setChecked(!Settings.soundEnabled);
            }
        });

        btMusic = new ImageButton(Assets.buttonMusicOn, Assets.buttonMusicOff, Assets.buttonMusicOff);
        btMusic.setSize(40, 40);
        btMusic.setPosition(44, 2);
        if (!Settings.musicEnabled)
            btMusic.setChecked(true);
        btMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Settings.musicEnabled = !Settings.musicEnabled;
                Assets.playSound(Assets.clickSound);
                if (!Settings.musicEnabled) {
                    btMusic.setChecked(true);
                    Assets.music.pause();
                } else {
                    btMusic.setChecked(false);
                    Assets.music.play();
                }
            }
        });

        elipseIzq = new Image(Assets.elipseMenu);
        elipseIzq.setSize(18.5f, 292.5f);
        elipseIzq.setPosition(0, 60);

        stage.addActor(titleSpacing);
        stage.addActor(lbHighestScore);

        stage.addActor(btPlay);
        stage.addActor(btSettings);
        stage.addActor(elipseIzq);
        stage.addActor(btSound);
        stage.addActor(btMusic);
    }

    @Override
    public void update(float delta) {
        lbHighestScore.setText(Assets.idioms.format("highest_score", String.valueOf(Settings.highScores[0])));
    }

    @Override
    public void draw(float delta) {
        oCam.update();
        batcher.setProjectionMatrix(oCam.combined);

        batcher.disableBlending();
        Assets.parallaxRegion.render(delta);
    }

    @Override
    public boolean keyDown(int alpha) {
        if (alpha == Keys.BACK || alpha == Keys.ESCAPE) {
            Assets.playSound(Assets.clickSound);
            if (game.dialogs.isDialogShown()) {
                game.dialogs.dismissAll();
            } else {

                Gdx.app.exit();
            }
            return true;
        }
        return false;
    }
}
