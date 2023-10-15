package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.screens.Screens;

public class MyGdxGame extends Game {

    public Stage stage;
    public Assets oAssets;
    public SpriteBatch spriteBatch;
    public DialogsInGame dialogs;

    @Override
    public void create() {
        stage = new Stage(new StretchViewport(Screens.SCREEN_WIDTH, Screens.SCREEN_HEIGHT));
        spriteBatch = new SpriteBatch();
        dialogs = new DialogsInGame(this, stage);
        Assets.load();
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        getScreen().dispose();
    }

}
