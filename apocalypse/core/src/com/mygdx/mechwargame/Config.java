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

    public static final int ARMOR_PRICE = 2000;

    public static final int TOOLTIP_MAX_WIDTH = 900;

    // orders
    public static final int FUEL_ORDER = 1;
    public static final int WEAPON_ORDER = 2;
    public static final int MODIFICATION_ORDER = 3;
    public static final int ARMOR_ORDER = 4;

    public static int CITY_WIDTH = 25;
    public static int CITY_HEIGHT = 7;

    public static Color TOOLTIP_COLOR = Color.valueOf("ffffffEF");
}
