package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {
    public static boolean drawDebugLines = false;
    public static boolean soundEnabled = false;
    public static boolean musicEnabled = false;
    public final static int[] highScores = new int[]{0, 0, 0, 0, 0};
    public static boolean isTiltControl = true;
    public static int speedSensitivity = 10;
    public static int sliderNum = 0;
    private final static Preferences pref = Gdx.app.getPreferences("com.tiarsoft.droid.Settings");

    public static void load() {
        isTiltControl = pref.getBoolean("isTiltControl", true);
        soundEnabled = pref.getBoolean("soundOn", false);
        musicEnabled = pref.getBoolean("musicOn", false);
        for (int i = 0; i < 5; i++) {
            highScores[i] = Integer.parseInt(pref.getString("spacing" + i, "0"));
        }
        speedSensitivity = pref.getInteger("speedSensitivity", 10);
        sliderNum = pref.getInteger("sliderNum", 0);
    }

    public static void save() {
        pref.putBoolean("isTiltControl", isTiltControl);
        pref.putBoolean("soundOn", soundEnabled);
        pref.putBoolean("musicOn", musicEnabled);

        for (int i = 0; i < 5; i++) {
            pref.putString("spacing" + i, String.valueOf(highScores[i]));
        }
        pref.putInteger("speedSensitivity", speedSensitivity);
        pref.putInteger("sliderNum", sliderNum);
        pref.flush();
    }

    public static void resetSpacing() {
        pref.clear();
        load();
    }

    public static void aggregateSpacing(int spacing) {
        for (int i = 0; i < 5; i++) {
            if (highScores[i] < spacing) {
                for (int j = 4; j > i; j--)
                    highScores[j] = highScores[j - 1];
                highScores[i] = spacing;
                break;
            }
        }
    }

}
