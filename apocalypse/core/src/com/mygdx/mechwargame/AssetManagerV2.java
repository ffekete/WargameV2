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
    public static final String TEXT_CURSOR = "ui/TextCursor.png";
    public static final String ROUND_SMALL_BUTTON_UP = "ui/RoundSmallButtonUp.png";
    public static final String ROUND_SMALL_BUTTON_DOWN = "ui/RoundSmallButtonDown.png";
    public static final String CHARACTER_INFO_FRAME = "ui/CharacterInfoFrame.png";
    public static final String CHARACTER_ATTRIBUTES_FRAME = "ui/CharacterAttributesFrame.png";
    public static final String TEXT_FIELD_BG = "ui/TextFieldBg.png";
    public static final String MECH_DESCRIPTION_FRAME = "ui/gearselection/MechDescriptionFrame.png";
    public static final String SHIP_INFO_DESCRIPTION_PANEL = "ui/ship/ShipInfoDescriptionPanel.png";
    public static final String HELP_ICON = "ui/HelpIcon.png";

    public static final String POWER_ICON_EMPTY = "ui/PowerIconEmpty.png";
    public static final String POWER_ICON_FULL = "ui/PowerIconFull.png";

    public static final String MOUSE_POINTER = "ui/MousePointer.png";
    public static final String BACK_BUTTON_UP = "ui/ship/BackButtonUp.png";
    public static final String BACK_BUTTON_DOWN = "ui/ship/BackButtonDown.png";

    public static final String STAR_LOCAL_MENU = "ui/StarLocalMenu.png";

    public static final String LOGO_01 = "company/logo/Logo01.png";
    public static final String LOGO_02 = "company/logo/Logo02.png";

    public static final String PORTRAIT_01 = "portrait/Portrait01.png";
    public static final String PORTRAIT_02 = "portrait/Portrait02.png";
    public static final String PORTRAIT_03 = "portrait/Portrait03.png";

    public static final String PORTRAIT_FRAME = "portrait/Frame.png";

    public static final String BLACK_BEAR_IMAGE = "mech/BlackBear.png";
    public static final String HELLFIRE_IMAGE = "mech/Hellfire.png";
    public static final String INTERCEPTOR_IMAGE = "mech/Interceptor.png";

    public static final String GM_STAR_01 = "galaxy/Star01.png";
    public static final String GM_STAR_02 = "galaxy/Star02.png";
    public static final String GM_STAR_03 = "galaxy/Star03.png";
    public static final String GM_STAR_04 = "galaxy/Star04.png";
    public static final String AREA_LAYER = "galaxy/AreaLayer.png";
    public static final String SECTOR_BOUNDARY = "galaxy/SectorBoundary.png";
    public static final String TARGET_MARKER = "galaxy/TargetMarker.png";

    public static final String MOVEMENT_PATH_MID = "effect/movementpath/PathMiddle.png";
    public static final String MOVEMENT_PATH_END = "effect/movementpath/PathEnd.png";

    public static final String SHIP = "ship/StarShip.png";

    public static final String LARGE_VIEW_FRAME = "ui/ViewLarge.png";

    public static final String STAR_001 = "starsystem/background/star/Star001.png";
    public static final String STAR_002 = "starsystem/background/star/Star002.png";
    public static final String STAR_003 = "starsystem/background/star/Star003.png";
    public static final String STAR_004 = "starsystem/background/star/Star004.png";
    public static final String STAR_005 = "starsystem/background/star/Star005.png";
    public static final String STAR_006 = "starsystem/background/star/Star006.png";
    public static final String STAR_007 = "starsystem/background/star/Star007.png";
    public static final String STAR_008 = "starsystem/background/star/Star008.png";
    public static final String PLANET_001 = "starsystem/background/planet/Planet001.png";
    public static final String PLANET_002 = "starsystem/background/planet/Planet002.png";
    public static final String PLANET_003 = "starsystem/background/planet/Planet003.png";
    public static final String PLANET_004 = "starsystem/background/planet/Planet004.png";
    public static final String STATION_001 = "starsystem/background/station/Station001.png";
    public static final String STATION_002 = "starsystem/background/station/Station002.png";
    public static final String STATION_003 = "starsystem/background/station/Station003.png";
    public static final String STATION_004 = "starsystem/background/station/Station004.png";
    public static final String DECORATION_001 = "starsystem/background/decoration/Decoration001.png";
    public static final String DECORATION_002 = "starsystem/background/decoration/Decoration002.png";
    public static final String DECORATION_003 = "starsystem/background/decoration/Decoration003.png";
    public static final String DECORATION_004 = "starsystem/background/decoration/Decoration004.png";
    public static final String DECORATION_005 = "starsystem/background/decoration/Decoration005.png";
    public static final String DECORATION_006 = "starsystem/background/decoration/Decoration006.png";
    public static final String DECORATION_007 = "starsystem/background/decoration/Decoration007.png";


    public List<String> logos = Arrays.asList(
            LOGO_01,
            LOGO_02
    );

    public List<String> portraits = Arrays.asList(
            PORTRAIT_01,
            PORTRAIT_02,
            PORTRAIT_03
    );

    public List<String> twinStars = Arrays.asList(
            STAR_006,
            STAR_007

    );

    public List<String> stars = Arrays.asList(
            STAR_001,
            STAR_002,
            STAR_003,
            STAR_004,
            STAR_005,
            STAR_008
    );

    public List<String> planets = Arrays.asList(
            PLANET_001,
            PLANET_002,
            PLANET_003,
            PLANET_004
    );

    public List<String> stations = Arrays.asList(
            STATION_001,
            STATION_002,
            STATION_003,
            STATION_004
    );

    public List<String> decorations = Arrays.asList(
            DECORATION_001,
            DECORATION_002,
            DECORATION_003,
            DECORATION_004,
            DECORATION_005,
            DECORATION_006,
            DECORATION_007
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
        load(AssetManagerV2.SHIP_INFO_DESCRIPTION_PANEL, Texture.class);
        load(AssetManagerV2.BLACK_BEAR_IMAGE, Texture.class);
        load(AssetManagerV2.HELLFIRE_IMAGE, Texture.class);
        load(AssetManagerV2.INTERCEPTOR_IMAGE, Texture.class);
        load(AssetManagerV2.GM_STAR_01, Texture.class);
        load(AssetManagerV2.GM_STAR_02, Texture.class);
        load(AssetManagerV2.GM_STAR_03, Texture.class);
        load(AssetManagerV2.GM_STAR_04, Texture.class);
        load(AssetManagerV2.AREA_LAYER, Texture.class);
        load(AssetManagerV2.SECTOR_BOUNDARY, Texture.class);
        load(AssetManagerV2.TARGET_MARKER, Texture.class);
        load(AssetManagerV2.SHIP, Texture.class);
        load(STAR_LOCAL_MENU, Texture.class);
        load(MOUSE_POINTER, Texture.class);
        load(BACK_BUTTON_UP, Texture.class);
        load(BACK_BUTTON_DOWN, Texture.class);
        load(MOVEMENT_PATH_MID, Texture.class);
        load(MOVEMENT_PATH_END, Texture.class);
        load(HELP_ICON, Texture.class);
        load(LARGE_VIEW_FRAME, Texture.class);
        load(STAR_001, Texture.class);
        load(STAR_002, Texture.class);
        load(STAR_003, Texture.class);
        load(STAR_004, Texture.class);
        load(STAR_005, Texture.class);
        load(STAR_006, Texture.class);
        load(STAR_007, Texture.class);
        load(STAR_008, Texture.class);
        load(STATION_001, Texture.class);
        load(STATION_002, Texture.class);
        load(STATION_003, Texture.class);
        load(STATION_004, Texture.class);
        load(PLANET_001, Texture.class);
        load(PLANET_002, Texture.class);
        load(PLANET_003, Texture.class);
        load(PLANET_004, Texture.class);
        load(DECORATION_001, Texture.class);
        load(DECORATION_002, Texture.class);
        load(DECORATION_003, Texture.class);
        load(DECORATION_004, Texture.class);
        load(DECORATION_005, Texture.class);
        load(DECORATION_006, Texture.class);
        load(DECORATION_007, Texture.class);
    }
}
