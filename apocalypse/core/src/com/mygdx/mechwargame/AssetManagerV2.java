package com.mygdx.mechwargame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.mechwargame.ui.AssetLoader;

import java.util.Arrays;
import java.util.List;

public class AssetManagerV2 extends AssetManager {

    public static final String MAIN_MENU_BUTTON_BG_FRAME = "mainmenu/MainMenuButtonBgUp.png";
    public static final String MAIN_MENU_BACKGROUND = "mainmenu/Background.png";
    public static final String MAIN_MENU_PARALLAX_ANIM = "mainmenu/Parallax.png";
    public static final String TEXT_CURSOR = "ui/TextCursor.png";

    public static final String LOGO_01 = "company/logo/Logo01.png";
    public static final String LOGO_02 = "company/logo/Logo02.png";

    public static final String PORTRAIT_01 = "portrait/Portrait01.png";
    public static final String PORTRAIT_02 = "portrait/Portrait02.png";
    public static final String PORTRAIT_03 = "portrait/Portrait03.png";

    public static final String PORTRAIT_FRAME = "portrait/Frame.png";

    public List<String> logos = Arrays.asList(
            LOGO_01,
            LOGO_02
    );

    public List<String> portraits = Arrays.asList(
            PORTRAIT_01,
            PORTRAIT_02,
            PORTRAIT_03
    );

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

    public void loadAll() {
        load(AssetManagerV2.MAIN_MENU_BUTTON_BG_FRAME, Texture.class);
        load(AssetManagerV2.MAIN_MENU_BACKGROUND, Texture.class);
        load(AssetManagerV2.MAIN_MENU_PARALLAX_ANIM, Texture.class);
        load(AssetManagerV2.TEXT_CURSOR, Texture.class);
        load(AssetManagerV2.LOGO_01, Texture.class);
        load(AssetManagerV2.LOGO_02, Texture.class);
        load(AssetManagerV2.PORTRAIT_01, Texture.class);
        load(AssetManagerV2.PORTRAIT_02, Texture.class);
        load(AssetManagerV2.PORTRAIT_03, Texture.class);
        load(AssetManagerV2.PORTRAIT_FRAME, Texture.class);
    }
}
