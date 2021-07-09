package com.mygdx.mechwargame;

import com.badlogic.gdx.graphics.Color;

public class Config {

    public static final int SCREEN_WIDTH = 1920;
    public static final int SCREEN_HEIGHT = 1080;

    public static final int UNIT_SIZE = 128;

    public static final int MAX_UNIT_STAT_LEVEL = 8;
    public static final int MAX_WEAPON_STAT_LEVEL = 8;

    public static boolean SHOW_FPS = false;

    public static float SCREEN_TRANSITION_DELAY = 0.25f;

    // orders
    public static final int FUEL_ORDER = 1;
    public static final int WEAPON_ORDER = 2;
    public static final int MODIFICATION_ORDER = 3;

    public static Color TOOLTIP_COLOR = Color.valueOf("ffffffEF");
}
