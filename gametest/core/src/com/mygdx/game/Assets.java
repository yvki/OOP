package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.badlogic.gdx.utils.I18NBundle;
import com.mygdx.game.parallax.ParallaxBackground;
import com.mygdx.game.parallax.ParallaxLayer;

public class Assets {
    public static I18NBundle idioms;
    private static final GlyphLayout glyphLayout = new GlyphLayout();

    public static AtlasRegion aRegion;
    public static ParallaxBackground parallaxRegion;

    public static AtlasRegion moveLeft;
    public static AtlasRegion moveRight;
    public static AtlasRegion move;

    public static BitmapFont font60;
    public static BitmapFont font45;
    public static BitmapFont font15;
    public static BitmapFont font10;

    public static AtlasRegion elipseMenu;
    public static NinePatchDrawable titleMenu;

    public static NinePatchDrawable InGameStatus;
    public static TextureRegionDrawable btLeft;
    public static TextureRegionDrawable btRight;
    public static TextureRegionDrawable btFire;
    public static TextureRegionDrawable btFireDown;
    public static TextureRegionDrawable btMissil;
    public static TextureRegionDrawable btMissilDown;

    public static AtlasRegion help;
    public static AtlasRegion userClick;

    public static TextureRegionDrawable buttonSoundOn;
    public static TextureRegionDrawable buttonSoundOff;
    public static TextureRegionDrawable buttonMusicOn;
    public static TextureRegionDrawable buttonMusicOff;

    public static AtlasRegion bulletNormal;
    public static Animation<TextureRegion> misil;
    public static Animation<TextureRegion> animate;
    public static AtlasRegion bulletLevel1;
    public static AtlasRegion bulletLevel2;
    public static AtlasRegion bulletLevel3;
    public static AtlasRegion bulletLevel4;

    public static AtlasRegion boost1;
    public static AtlasRegion boost2;
    public static AtlasRegion boost3;
    public static AtlasRegion upgLife;

    public static Animation<TextureRegion> explosion;
    public static Animation<TextureRegion> shield;

    public static AtlasRegion bulletNormalEnemy;
    public static AtlasRegion alien1;
    public static AtlasRegion alien2;
    public static AtlasRegion alien3;
    public static AtlasRegion alien4;

    public static Music music;
    public static Sound coinSound;
    public static Sound clickSound;
    public static Sound explosionSound;
    public static Sound missilFire;

    public static TextButtonStyle styleTextButtonMenu;
    public static TextButtonStyle styleTextButtonBack;
    public static TextButtonStyle styleTextButton;

    public static WindowStyle styleDialogPause;

    public static LabelStyle styleLabel;

    public static LabelStyle styleLabelDialog;
    public static SliderStyle styleSlider;

    public static ImageButtonStyle styleImageButtonPause;
    public static ImageButtonStyle styleImageButtonStyleCheckBox;

    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
    }


    static private void loadFont(TextureAtlas atlas) {
        font60 = new BitmapFont(Gdx.files.internal("data/font35.fnt"), atlas.findRegion("font35"), false);
        font45 = new BitmapFont(Gdx.files.internal("data/font35.fnt"), atlas.findRegion("font35"), false);
        font15 = new BitmapFont(Gdx.files.internal("data/font15.fnt"), atlas.findRegion("font15"), false);
        font10 = new BitmapFont(Gdx.files.internal("data/font15.fnt"), atlas.findRegion("font15"), false);

        font60.setColor(Color.GREEN);
        font45.setColor(Color.GREEN);
        font15.setColor(Color.GREEN);
        font10.setColor(Color.GREEN);
    }

    static private void loadSceneStyles(TextureAtlas atlas) {
        NinePatchDrawable recuadroLogIn = new NinePatchDrawable(atlas.createPatch("LogIn"));
        AtlasRegion dialogDim = atlas.findRegion("aRegionWhole");
        styleDialogPause = new WindowStyle(font45, Color.GREEN, recuadroLogIn);
        styleDialogPause.stageBackground = new NinePatchDrawable(new NinePatch(dialogDim));
        styleLabelDialog = new LabelStyle(font15, Color.GREEN);

        NinePatchDrawable default_round_down = new NinePatchDrawable(atlas.createPatch("buttonDown"));
        NinePatchDrawable default_round = new NinePatchDrawable(atlas.createPatch("button"));
        styleTextButton = new TextButtonStyle(default_round, default_round_down, null, font15);
        styleTextButton.fontColor = Color.GREEN;

        NinePatchDrawable buttonMenu = new NinePatchDrawable(atlas.createPatch("buttonMenu"));
        NinePatchDrawable buttonMenuDown = new NinePatchDrawable(atlas.createPatch("buttonMenuPressed"));
        styleTextButtonMenu = new TextButtonStyle(buttonMenu, buttonMenuDown, null, font45);
        styleTextButtonMenu.fontColor = Color.GREEN;

        styleLabel = new LabelStyle(font15, Color.GREEN);

        NinePatchDrawable default_slider = new NinePatchDrawable(atlas.createPatch("default-slider"));
        TextureRegionDrawable default_slider_knob = new TextureRegionDrawable(atlas.findRegion("default-slider-knob"));

        styleSlider = new SliderStyle(default_slider, default_slider_knob);

        TextureRegionDrawable btBackUp = new TextureRegionDrawable(atlas.findRegion("btBack"));
        TextureRegionDrawable btBackDown = new TextureRegionDrawable(atlas.findRegion("btBackDown"));

        styleTextButtonBack = new TextButtonStyle(btBackUp, btBackDown, null, font15);
        styleTextButtonBack.fontColor = Color.GREEN;

        TextureRegionDrawable btPauseUp = new TextureRegionDrawable(atlas.findRegion("btPause"));
        TextureRegionDrawable btPauseDown = new TextureRegionDrawable(atlas.findRegion("btPauseDown"));
        styleImageButtonPause = new ImageButtonStyle(btPauseUp, btPauseDown, null, null, null, null);

        TextureRegionDrawable checked = new TextureRegionDrawable(atlas.findRegion("checkBoxDown"));
        TextureRegionDrawable unchecked = new TextureRegionDrawable(atlas.findRegion("checkBox"));

        styleImageButtonStyleCheckBox = new ImageButtonStyle(unchecked, checked, checked, null, null, null);
    }

    public static void load() {
        idioms = I18NBundle.createBundle(Gdx.files.internal("strings/strings"));

        TextureAtlas atlas1 = new TextureAtlas(Gdx.files.internal("data/atlasMap.txt"));

        loadFont(atlas1);
        loadSceneStyles(atlas1);

        elipseMenu = atlas1.findRegion("elipseMenu");
        titleMenu = new NinePatchDrawable(atlas1.createPatch("titleMenu"));

        InGameStatus = new NinePatchDrawable(atlas1.createPatch("InGameStatus"));
        btLeft = new TextureRegionDrawable(atlas1.findRegion("btLeft"));
        btRight = new TextureRegionDrawable(atlas1.findRegion("btRight"));
        btFire = new TextureRegionDrawable(atlas1.findRegion("btFire"));
        btFireDown = new TextureRegionDrawable(atlas1.findRegion("btFire"));
        btMissil = new TextureRegionDrawable(atlas1.findRegion("btMissil"));
        btMissilDown = new TextureRegionDrawable(atlas1.findRegion("btMissil"));

        aRegion = atlas1.findRegion("aRegion");

        help = atlas1.findRegion("help");
        userClick = atlas1.findRegion("userClick");

        buttonMusicOn = new TextureRegionDrawable(atlas1.findRegion("btMusic"));
        buttonMusicOff = new TextureRegionDrawable(atlas1.findRegion("btNoMusic"));
        buttonSoundOn = new TextureRegionDrawable(atlas1.findRegion("btSound"));
        buttonSoundOff = new TextureRegionDrawable(atlas1.findRegion("btNoSound"));

        moveRight = atlas1.findRegion("moveRight");
        moveLeft = atlas1.findRegion("moveLeft");
        move = atlas1.findRegion("move");

        AtlasRegion shield0 = atlas1.findRegion("shield0");
        AtlasRegion shield1 = atlas1.findRegion("shield1");
        AtlasRegion shield2 = atlas1.findRegion("shield2");
        AtlasRegion shield3 = atlas1.findRegion("shield3");
        AtlasRegion shield4 = atlas1.findRegion("shield4");
        AtlasRegion shield5 = atlas1.findRegion("shield5");
        AtlasRegion shield6 = atlas1.findRegion("shield6");
        AtlasRegion shield7 = atlas1.findRegion("shield7");
        AtlasRegion shield8 = atlas1.findRegion("shield9");
        AtlasRegion shield9 = atlas1.findRegion("shield9");
        AtlasRegion shield10 = atlas1.findRegion("shield10");
        AtlasRegion shield11 = atlas1.findRegion("shield11");
        shield = new Animation<TextureRegion>(.1f, shield0, shield1, shield2, shield3, shield4, shield5, shield6, shield7, shield8, shield9, shield10, shield11);

        alien1 = atlas1.findRegion("alien1");
        alien2 = atlas1.findRegion("alien2");
        alien3 = atlas1.findRegion("alien3");
        alien4 = atlas1.findRegion("alien4");

        boost1 = atlas1.findRegion("upgLaser");
        boost2 = atlas1.findRegion("upgBomb");
        boost3 = atlas1.findRegion("upgShield");
        upgLife = atlas1.findRegion("upgLife");

        bulletNormal = atlas1.findRegion("bulletNormal");
        bulletNormalEnemy = atlas1.findRegion("bulletNormalEnemy");

        AtlasRegion misil1 = atlas1.findRegion("misil1");
        AtlasRegion misil2 = atlas1.findRegion("misil2");
        misil = new Animation<TextureRegion>(0.2f, misil1, misil2);

        AtlasRegion animate1 = atlas1.findRegion("animate1");
        AtlasRegion animate2 = atlas1.findRegion("animate2");
        animate = new Animation<TextureRegion>(0.2f, animate1, animate2);

        bulletLevel1 = atlas1.findRegion("dispatch1");
        bulletLevel2 = atlas1.findRegion("dispatch2");
        bulletLevel3 = atlas1.findRegion("dispatch3");
        bulletLevel4 = atlas1.findRegion("dispatch4");

        AtlasRegion newExpl1 = atlas1.findRegion("newExplosion1");
        AtlasRegion newExpl2 = atlas1.findRegion("newExplosion2");
        AtlasRegion newExpl3 = atlas1.findRegion("newExplosion3");
        AtlasRegion newExpl4 = atlas1.findRegion("newExplosion4");
        AtlasRegion newExpl5 = atlas1.findRegion("newExplosion5");
        AtlasRegion newExpl6 = atlas1.findRegion("newExplosion6");
        AtlasRegion newExpl7 = atlas1.findRegion("newExplosion7");
        AtlasRegion newExpl8 = atlas1.findRegion("newExplosion8");
        AtlasRegion newExpl9 = atlas1.findRegion("newExplosion9");
        AtlasRegion newExpl10 = atlas1.findRegion("newExplosion10");
        AtlasRegion newExpl11 = atlas1.findRegion("newExplosion11");
        AtlasRegion newExpl12 = atlas1.findRegion("newExplosion12");
        AtlasRegion newExpl13 = atlas1.findRegion("newExplosion13");
        AtlasRegion newExpl14 = atlas1.findRegion("newExplosion14");
        AtlasRegion newExpl15 = atlas1.findRegion("newExplosion15");
        AtlasRegion newExpl16 = atlas1.findRegion("newExplosion16");
        AtlasRegion newExpl17 = atlas1.findRegion("newExplosion17");
        AtlasRegion newExpl18 = atlas1.findRegion("newExplosion18");
        AtlasRegion newExpl19 = atlas1.findRegion("newExplosion19");
        explosion = new Animation<TextureRegion>(0.05f, newExpl1, newExpl2, newExpl3, newExpl4, newExpl5, newExpl6, newExpl7, newExpl8, newExpl9, newExpl10, newExpl11, newExpl12, newExpl13, newExpl14, newExpl15, newExpl16, newExpl17, newExpl18, newExpl19);

        ParallaxLayer para1 = new ParallaxLayer(aRegion, new Vector2(0, 50), new Vector2(0, 0));
        ParallaxLayer arr[] = new ParallaxLayer[]{para1};
        parallaxRegion = new ParallaxBackground(arr, 320, 480, new Vector2(0, 1));

        music = Gdx.audio.newMusic(Gdx.files.internal("data/sounds/bgmusic.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        coinSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/coin.ogg"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/click.ogg"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/sound_explode.ogg"));
        missilFire = Gdx.audio.newSound(Gdx.files.internal("data/sounds/missilFire3.ogg"));

        Settings.load();
        if (Settings.musicEnabled)
            music.play();

    }

    public static void playSound(Sound sound, float volume) {
        if (Settings.soundEnabled)
            sound.play(volume);
    }

    public static void playSound(Sound sound) {
        if (Settings.soundEnabled)
            sound.play(1);
    }

    public static float getTextWidth(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.width;
    }
}
