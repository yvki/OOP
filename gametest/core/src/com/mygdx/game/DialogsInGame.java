package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

public class DialogsInGame {
    Stage stage;
    final MyGdxGame game;

    Dialog dialogSignIn, dialogRate;

    public DialogsInGame(MyGdxGame game, Stage stage) {
        this.stage = stage;
        this.game = game;
    }

    public boolean isDialogShown() {
        return stage.getActors().contains(dialogRate, true) || stage.getActors().contains(dialogSignIn, true);
    }

    public void dismissAll() {
        if (stage.getActors().contains(dialogRate, true))
            dialogRate.hide();

        if (stage.getActors().contains(dialogSignIn, true))
            dialogSignIn.hide();

    }
}
