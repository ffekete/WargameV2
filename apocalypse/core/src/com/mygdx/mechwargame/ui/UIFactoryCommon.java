package com.mygdx.mechwargame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class UIFactoryCommon {

    public static BitmapFont fontSmall;
    public static BitmapFont fontMedium;
    public static BitmapFont fontLarge;

    static {

        fontSmall = getFont("font/font_small.fnt");
        fontMedium = getFont("font/font_med.fnt");
        fontLarge = getFont("font/font_huge.fnt");
    }

    private static BitmapFont getFont(String fontPath) {

        BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal(fontPath));

        return bitmapFont;
    }


}
