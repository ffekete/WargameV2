package com.mygdx.mechwargame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.mechwargame.ui.AssetLoader;

import java.util.Arrays;
import java.util.List;

public class AssetManagerV2 extends AssetManager {

    public static final String MAIN_MENU_BUTTON_BG_FRAME = "mainmenu/MainMenuButtonBgUp.png";
    public static final String MAIN_MENU_BUTTON_BG_DOWN_FRAME = "mainmenu/MainMenuButtonBgDown.png";
    public static final String MAIN_MENU_BACKGROUND = "mainmenu/Background.png";
    public static final String MAIN_MENU_PARALLAX_ANIM = "mainmenu/Parallax.png";
    public static final String TEXT_CURSOR = "ui/TextCursor.png";
    public static final String ROUND_SMALL_BUTTON_UP = "ui/RoundSmallButtonUp.png";
    public static final String ROUND_SMALL_BUTTON_DOWN = "ui/RoundSmallButtonDown.png";
    public static final String CHARACTER_INFO_FRAME = "ui/CharacterInfoFrame.png";
    public static final String CHARACTER_ATTRIBUTES_FRAME = "ui/CharacterAttributesFrame.png";
    public static final String TEXT_FIELD_BG = "ui/TextFieldBg.png";
    public static final String MECH_DESCRIPTION_FRAME = "ui/gearselection/MechDescriptionFrame.png";

    public static final String POWER_ICON_EMPTY = "ui/PowerIconEmpty.png";
    public static final String POWER_ICON_FULL = "ui/PowerIconFull.png";

    public static final String LOGO_01 = "company/logo/Logo01.png";
    public static final String LOGO_02 = "company/logo/Logo02.png";

    public static final String PORTRAIT_01 = "portrait/Portrait01.png";
    public static final String PORTRAIT_02 = "portrait/Portrait02.png";
    public static final String PORTRAIT_03 = "portrait/Portrait03.png";

    public static final String PORTRAIT_FRAME = "portrait/Frame.png";

    public static final String BLACK_BEAR_IMAGE = "mech/BlackBear.png";
    public static final String HELLFIRE_IMAGE = "mech/Hellfire.png";
    public static final String INTERCEPTOR_IMAGE = "mech/Interceptor.png";

    public static final String STAR_SMALL = "galaxy/StarSmall.png";
    public static final String STAR_MEDIUM = "galaxy/StarMedium.png";
    public static final String STAR_LARGE = "galaxy/StarLarge.png";

    public static final String SHIP = "ship/StarShip.png";

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
        load(AssetManagerV2.MAIN_MENU_BUTTON_BG_DOWN_FRAME, Texture.class);
        load(AssetManagerV2.MAIN_MENU_BACKGROUND, Texture.class);
        load(AssetManagerV2.MAIN_MENU_PARALLAX_ANIM, Texture.class);
        load(AssetManagerV2.TEXT_CURSOR, Texture.class);
        load(AssetManagerV2.LOGO_01, Texture.class);
        load(AssetManagerV2.LOGO_02, Texture.class);
        load(AssetManagerV2.PORTRAIT_01, Texture.class);
        load(AssetManagerV2.PORTRAIT_02, Texture.class);
        load(AssetManagerV2.PORTRAIT_03, Texture.class);
        load(AssetManagerV2.PORTRAIT_FRAME, Texture.class);
        load(AssetManagerV2.CHARACTER_INFO_FRAME, Texture.class);
        load(AssetManagerV2.CHARACTER_ATTRIBUTES_FRAME, Texture.class);
        load(AssetManagerV2.ROUND_SMALL_BUTTON_UP, Texture.class);
        load(AssetManagerV2.ROUND_SMALL_BUTTON_DOWN, Texture.class);
        load(AssetManagerV2.TEXT_FIELD_BG, Texture.class);
        load(AssetManagerV2.MECH_DESCRIPTION_FRAME, Texture.class);
        load(AssetManagerV2.POWER_ICON_EMPTY, Texture.class);
        load(AssetManagerV2.POWER_ICON_FULL, Texture.class);

        load(AssetManagerV2.BLACK_BEAR_IMAGE, Texture.class);
        load(AssetManagerV2.HELLFIRE_IMAGE, Texture.class);
        load(AssetManagerV2.INTERCEPTOR_IMAGE, Texture.class);

        load(AssetManagerV2.STAR_SMALL, Texture.class);
        load(AssetManagerV2.STAR_MEDIUM, Texture.class);
        load(AssetManagerV2.STAR_LARGE, Texture.class);

        load(AssetManagerV2.SHIP, Texture.class);
    }
}
