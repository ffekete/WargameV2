package com.mygdx.mechwargame;

import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.mechwargame.ui.AssetLoader;

public class AssetManagerV2 extends AssetManager {

    public static final String MAIN_MENU_BUTTON_BG_FRAME = "mainmenu/MainMenuButtonBgUp.png";
    public static final String MAIN_MENU_PARALLAX_ANIM = "mainmenu/Parallax.png";
    public static final String TEXT_CURSOR = "ui/TextCursor.png";

    @Override
    public synchronized <T> T get(String fileName,
                                  Class<T> type) {
        String resolvedName = AssetLoader.resolve(fileName);
        return super.get(resolvedName, type);
    }

    @Override
    public synchronized <T> void load(String fileName,
                                      Class<T> type) {
        String resolvedName = AssetLoader.resolve(fileName);
        super.load(resolvedName, type);
    }
}
