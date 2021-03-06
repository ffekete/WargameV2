package com.mygdx.mechwargame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.mechwargame.ui.AssetLoader;

import java.util.Arrays;
import java.util.List;

public class AssetManagerV2 extends AssetManager {

    public static final String TEXT_CURSOR = "ui/TextCursor.png";
    public static final String HELP_ICON = "ui/HelpIcon.png";

    public static final String CARGO_ITEM_BG = "ui/cargo/CargoItemBg.png";
    public static final String CARGO_SELECTED_ITEM_BG = "ui/cargo/CargoSelectedItemBg.png";

    public static final String SCROLL_PANE_KNOB = "ui/ScrollPaneKnob.png";

    public static final String HYDROGEN_CELL_FULL = "item/HydrogenCellFull.png";
    public static final String HYDROGEN_CELL_USED = "item/HydrogenCellUsed.png";

    public static final String POWER_ICON_EMPTY = "ui/PowerIconEmpty.png";
    public static final String POWER_ICON_FULL = "ui/PowerIconFull.png";
    public static final String POWER_ICON_PLUS = "ui/PowerIconPlus.png";
    public static final String POWER_ICON_PLUS_DOWN = "ui/PowerIconPlusDown.png";
    public static final String POWER_ICON_CAN_UPGRADE = "ui/PowerIconCanUpgrade.png";

    public static final String MOUSE_POINTER = "ui/MousePointer.png";
    public static final String BACK_BUTTON_UP = "ui/ship/BackButtonUp.png";
    public static final String BACK_BUTTON_DOWN = "ui/ship/BackButtonDown.png";

    public static final String LOGO_01 = "company/logo/Logo01.png";
    public static final String LOGO_02 = "company/logo/Logo02.png";

    public static final String PORTRAIT_01 = "portrait/Portrait01.png";
    public static final String PORTRAIT_02 = "portrait/Portrait02.png";
    public static final String PORTRAIT_03 = "portrait/Portrait03.png";

    public static final String PORTRAIT_FRAME = "portrait/Frame.png";

    public static final String BLACK_BEAR_IDLE_IMAGE = "mech/BlackBear.png";
    public static final String HELLFIRE_IDLE_IMAGE = "mech/Hellfire.png";
    public static final String INTERCEPTOR_IDLE_IMAGE = "mech/Interceptor.png";

    public static final String GM_STAR_01 = "galaxy/Star01.png";
    public static final String GM_STAR_02 = "galaxy/Star02.png";
    public static final String GM_STAR_03 = "galaxy/Star03.png";
    public static final String GM_STAR_04 = "galaxy/Star04.png";
    public static final String AREA_LAYER = "galaxy/AreaLayer.png";
    public static final String SECTOR_BOUNDARY = "galaxy/SectorBoundary.png";
    public static final String TARGET_MARKER = "galaxy/TargetMarker.png";
    public static final String CAPITOL_FRAME = "galaxy/CapitolFrame.png";

    public static final String MOVEMENT_PATH_MID = "effect/movementpath/PathMiddle.png";
    public static final String MOVEMENT_PATH_END = "effect/movementpath/PathEnd.png";

    public static final String SHIP = "ship/StarShip.png";

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
    public static final String CAPITOL_STATION_001 = "starsystem/background/station/CapitolStation.png";
    public static final String STATION_001 = "starsystem/background/station/Station001.png";
    public static final String STATION_002 = "starsystem/background/station/Station002.png";
    public static final String STATION_003 = "starsystem/background/station/Station003.png";
    public static final String STATION_004 = "starsystem/background/station/Station004.png";
    public static final String STATION_005 = "starsystem/background/station/Station005.png";
    public static final String PIRATE_STATION_001 = "starsystem/background/station/PirateStation.png";
    public static final String DECORATION_001 = "starsystem/background/decoration/Decoration001.png";
    public static final String DECORATION_002 = "starsystem/background/decoration/Decoration002.png";
    public static final String DECORATION_003 = "starsystem/background/decoration/Decoration003.png";
    public static final String DECORATION_004 = "starsystem/background/decoration/Decoration004.png";
    public static final String DECORATION_005 = "starsystem/background/decoration/Decoration005.png";
    public static final String DECORATION_006 = "starsystem/background/decoration/Decoration006.png";
    public static final String DECORATION_007 = "starsystem/background/decoration/Decoration007.png";

    public static final String TOOLTIP_BG = "ui/ToolTipBg.png";
    public static final String FRAME_BG = "ui/FrameBg.png";
    public static final String PANEL_FRAME_BG = "ui/PanelFrameBg.png";
    public static final String FRAME_DISABLED_BG = "ui/FrameDisabledBg.png";
    public static final String FRAME_BG_SMALL = "ui/FrameBgSmall.png";
    public static final String ASSIGN_BUTTON_UP = "ui/AssignButtonUp.png";
    public static final String ASSIGN_BUTTON_DOWN = "ui/AssignButtonDown.png";

    public static final String LASER_CANNON = "weapon/LaserCannon.png";
    public static final String LARGE_LASER_CANNON = "weapon/LargeLaserCannon.png";
    public static final String AA_MISSILE = "weapon/AirToAirMissile.png";
    public static final String AG_MISSILE = "weapon/AirToGroundMissile.png";
    public static final String SR_MISSILE = "weapon/ShortRangeMissile.png";
    public static final String LR_MISSILE = "weapon/LongRangeMissile.png";

    public static final String ARMOR_PLATE = "item/ArmorPlate.png";

    public static final String TARGETING_MODULE = "item/mod/targetingModule.png";
    public static final String ENHANCED_SENSORS_MODULE = "item/mod/EnhancedSensors.png";
    public static final String REPEATER = "item/mod/Repeater.png";
    public static final String SMALL_POWERBANK = "item/mod/SmallPowerBank.png";
    public static final String IMPROVED_LENSES = "item/mod/ImprovedLenses.png";

    public static final String SINGLE_SHOT_ICON = "ui/weapon/SingleShotIcon.png";
    public static final String BURST_SHOT_ICON = "ui/weapon/BurstShotIcon.png";
    public static final String AREA_ATTACK_ICON = "ui/weapon/AreaAttackIcon.png";
    public static final String MODE_BUTTON_DOWN = "ui/weapon/ModeButtonDown.png";

    public static final String DELETE_ICON_UP = "ui/DeleteIconUp.png";
    public static final String DELETE_ICON_DOWN = "ui/DeleteIconDown.png";

    public static final String GALAXY_TILE_BG_01 = "galaxy/Bg01.png";
    public static final String GALAXY_TILE_BG_02 = "galaxy/Bg02.png";
    public static final String GALAXY_TILE_BG_03 = "galaxy/Bg03.png";
    public static final String GALAXY_TILE_BG_04 = "galaxy/Bg04.png";
    public static final String GALAXY_TILE_BG_05 = "galaxy/Bg05.png";

    public static final String STAR_SYSTEM_BUILDING_01 = "starsystem/city/Building01.png";
    public static final String STAR_SYSTEM_BUILDING_02 = "starsystem/city/Building02.png";
    public static final String STAR_SYSTEM_BUILDING_03 = "starsystem/city/Building03.png";
    public static final String STAR_SYSTEM_BUILDING_04 = "starsystem/city/Building04.png";
    public static final String STAR_SYSTEM_BUILDING_05 = "starsystem/city/Building05.png";
    public static final String STAR_SYSTEM_MARKET = "starsystem/city/Market.png";
    public static final String STAR_SYSTEM_FACTORY = "starsystem/city/Factory.png";
    public static final String STAR_SYSTEM_DECORATION_01 = "starsystem/city/Decoration01.png";
    public static final String STAR_SYSTEM_DECORATION_02 = "starsystem/city/Decoration02.png";
    public static final String STAR_SYSTEM_DECORATION_03 = "starsystem/city/Decoration03.png";
    public static final String STAR_SYSTEM_BG_01 = "starsystem/city/BackGround01.png";
    public static final String STAR_SYSTEM_BG_02 = "starsystem/city/BackGround02.png";
    public static final String STAR_SYSTEM_BG_03 = "starsystem/city/BackGround03.png";
    public static final String STAR_SYSTEM_BG_04 = "starsystem/city/BackGround04.png";
    public static final String STAR_SYSTEM_BG_05 = "starsystem/city/BackGround05.png";
    public static final String STAR_SYSTEM_BG_06 = "starsystem/city/BackGround06.png";
    public static final String STAR_SYSTEM_BG_07 = "starsystem/city/BackGround07.png";
    public static final String STAR_SYSTEM_BG_08 = "starsystem/city/BackGround08.png";
    public static final String STAR_SYSTEM_BG_09 = "starsystem/city/BackGround09.png";
    public static final String STAR_SYSTEM_BG_10 = "starsystem/city/BackGround10.png";
    public static final String STAR_SYSTEM_ASTEROID_BG_01 = "starsystem/asteroid/BackGround.png";
    public static final String STAR_SYSTEM_ASTEROID_BUILDING_01 = "starsystem/asteroid/Building01.png";
    public static final String STAR_SYSTEM_ASTEROID_DECORATION_01 = "starsystem/asteroid/Decoration01.png";
    public static final String STAR_SYSTEM_ASTEROID_DECORATION_02 = "starsystem/asteroid/Decoration02.png";
    public static final String STAR_SYSTEM_ASTEROID_DECORATION_03 = "starsystem/asteroid/Decoration03.png";
    public static final String STAR_SYSTEM_ASTEROID_DECORATION_04 = "starsystem/asteroid/Decoration04.png";
    public static final String STAR_SYSTEM_ASTEROID_DECORATION_05 = "starsystem/asteroid/Decoration05.png";
    public static final String STAR_SYSTEM_ASTEROID_DECORATION_06 = "starsystem/asteroid/Decoration06.png";
    public static final String STAR_SYSTEM_ASTEROID_DECORATION_07 = "starsystem/asteroid/Decoration07.png";
    public static final String STAR_SYSTEM_ASTEROID_DECORATION_08 = "starsystem/asteroid/Decoration08.png";
    public static final String STAR_SYSTEM_ASTEROID_BLACK_MARKET = "starsystem/asteroid/BlackMarket.png";
    public static final String STAR_SYSTEM_FOLIAGE_01 = "starsystem/city/Foliage01.png";
    public static final String STAR_SYSTEM_FOLIAGE_02 = "starsystem/city/Foliage02.png";
    public static final String STAR_SYSTEM_FOLIAGE_03 = "starsystem/city/Foliage03.png";
    public static final String STAR_SYSTEM_FOLIAGE_04 = "starsystem/city/Foliage04.png";
    public static final String STAR_SYSTEM_FOLIAGE_05 = "starsystem/city/Foliage05.png";

    public static final String NEBULA_01 = "galaxy/decoration/Nebula01.png";
    public static final String NEBULA_02 = "galaxy/decoration/Nebula02.png";
    public static final String NEBULA_03 = "galaxy/decoration/Nebula03.png";

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
            STATION_004,
            STATION_005
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
        load(AssetManagerV2.TEXT_CURSOR, Texture.class);
        load(AssetManagerV2.LOGO_01, Texture.class);
        load(AssetManagerV2.LOGO_02, Texture.class);
        load(AssetManagerV2.PORTRAIT_01, Texture.class);
        load(AssetManagerV2.PORTRAIT_02, Texture.class);
        load(AssetManagerV2.PORTRAIT_03, Texture.class);
        load(AssetManagerV2.PORTRAIT_FRAME, Texture.class);
        load(AssetManagerV2.POWER_ICON_EMPTY, Texture.class);
        load(AssetManagerV2.POWER_ICON_FULL, Texture.class);
        load(AssetManagerV2.POWER_ICON_PLUS, Texture.class);
        load(AssetManagerV2.POWER_ICON_PLUS_DOWN, Texture.class);
        load(AssetManagerV2.POWER_ICON_CAN_UPGRADE, Texture.class);
        load(AssetManagerV2.BLACK_BEAR_IDLE_IMAGE, Texture.class);
        load(AssetManagerV2.HELLFIRE_IDLE_IMAGE, Texture.class);
        load(AssetManagerV2.INTERCEPTOR_IDLE_IMAGE, Texture.class);
        load(AssetManagerV2.GM_STAR_01, Texture.class);
        load(AssetManagerV2.GM_STAR_02, Texture.class);
        load(AssetManagerV2.GM_STAR_03, Texture.class);
        load(AssetManagerV2.GM_STAR_04, Texture.class);
        load(AssetManagerV2.AREA_LAYER, Texture.class);
        load(AssetManagerV2.SECTOR_BOUNDARY, Texture.class);
        load(AssetManagerV2.TARGET_MARKER, Texture.class);
        load(AssetManagerV2.SHIP, Texture.class);
        load(MOUSE_POINTER, Texture.class);
        load(BACK_BUTTON_UP, Texture.class);
        load(BACK_BUTTON_DOWN, Texture.class);
        load(MOVEMENT_PATH_MID, Texture.class);
        load(MOVEMENT_PATH_END, Texture.class);
        load(HELP_ICON, Texture.class);
        load(STAR_001, Texture.class);
        load(STAR_002, Texture.class);
        load(STAR_003, Texture.class);
        load(STAR_004, Texture.class);
        load(STAR_005, Texture.class);
        load(STAR_006, Texture.class);
        load(STAR_007, Texture.class);
        load(STAR_008, Texture.class);
        load(CAPITOL_STATION_001, Texture.class);
        load(STATION_001, Texture.class);
        load(STATION_002, Texture.class);
        load(STATION_003, Texture.class);
        load(STATION_004, Texture.class);
        load(STATION_005, Texture.class);
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
        load(CAPITOL_FRAME, Texture.class);
        load(PIRATE_STATION_001, Texture.class);
        load(HYDROGEN_CELL_FULL, Texture.class);
        load(HYDROGEN_CELL_USED, Texture.class);
        load(CARGO_ITEM_BG, Texture.class);
        load(SCROLL_PANE_KNOB, Texture.class);
        load(TOOLTIP_BG, Texture.class);
        load(FRAME_BG, Texture.class);
        load(FRAME_DISABLED_BG, Texture.class);
        load(FRAME_BG_SMALL, Texture.class);
        load(LASER_CANNON, Texture.class);
        load(LARGE_LASER_CANNON, Texture.class);
        load(AA_MISSILE, Texture.class);
        load(AG_MISSILE, Texture.class);
        load(SR_MISSILE, Texture.class);
        load(LR_MISSILE, Texture.class);
        load(CARGO_SELECTED_ITEM_BG, Texture.class);
        load(TARGETING_MODULE, Texture.class);
        load(ENHANCED_SENSORS_MODULE, Texture.class);
        load(SINGLE_SHOT_ICON, Texture.class);
        load(BURST_SHOT_ICON, Texture.class);
        load(AREA_ATTACK_ICON, Texture.class);
        load(DELETE_ICON_UP, Texture.class);
        load(DELETE_ICON_DOWN, Texture.class);
        load(GALAXY_TILE_BG_01, Texture.class);
        load(GALAXY_TILE_BG_02, Texture.class);
        load(GALAXY_TILE_BG_03, Texture.class);
        load(GALAXY_TILE_BG_04, Texture.class);
        load(GALAXY_TILE_BG_05, Texture.class);
        load(ARMOR_PLATE, Texture.class);
        load(MODE_BUTTON_DOWN, Texture.class);
        load(REPEATER, Texture.class);
        load(SMALL_POWERBANK, Texture.class);
        load(ASSIGN_BUTTON_UP, Texture.class);
        load(ASSIGN_BUTTON_DOWN, Texture.class);
        load(STAR_SYSTEM_BUILDING_01, Texture.class);
        load(STAR_SYSTEM_BUILDING_02, Texture.class);
        load(STAR_SYSTEM_BUILDING_03, Texture.class);
        load(STAR_SYSTEM_BUILDING_04, Texture.class);
        load(STAR_SYSTEM_BUILDING_05, Texture.class);
        load(STAR_SYSTEM_FACTORY, Texture.class);
        load(STAR_SYSTEM_MARKET, Texture.class);
        load(STAR_SYSTEM_DECORATION_01, Texture.class);
        load(STAR_SYSTEM_DECORATION_02, Texture.class);
        load(STAR_SYSTEM_DECORATION_03, Texture.class);
        load(STAR_SYSTEM_BG_01, Texture.class);
        load(STAR_SYSTEM_BG_02, Texture.class);
        load(STAR_SYSTEM_BG_03, Texture.class);
        load(STAR_SYSTEM_BG_04, Texture.class);
        load(STAR_SYSTEM_BG_05, Texture.class);
        load(STAR_SYSTEM_BG_06, Texture.class);
        load(STAR_SYSTEM_BG_07, Texture.class);
        load(STAR_SYSTEM_BG_08, Texture.class);
        load(STAR_SYSTEM_BG_09, Texture.class);
        load(STAR_SYSTEM_BG_10, Texture.class);
        load(STAR_SYSTEM_ASTEROID_BG_01, Texture.class);
        load(STAR_SYSTEM_ASTEROID_BLACK_MARKET, Texture.class);
        load(STAR_SYSTEM_ASTEROID_BUILDING_01, Texture.class);
        load(STAR_SYSTEM_ASTEROID_DECORATION_01, Texture.class);
        load(STAR_SYSTEM_ASTEROID_DECORATION_02, Texture.class);
        load(STAR_SYSTEM_ASTEROID_DECORATION_03, Texture.class);
        load(STAR_SYSTEM_ASTEROID_DECORATION_04, Texture.class);
        load(STAR_SYSTEM_ASTEROID_DECORATION_05, Texture.class);
        load(STAR_SYSTEM_ASTEROID_DECORATION_06, Texture.class);
        load(STAR_SYSTEM_ASTEROID_DECORATION_07, Texture.class);
        load(STAR_SYSTEM_ASTEROID_DECORATION_08, Texture.class);
        load(IMPROVED_LENSES, Texture.class);
        load(PANEL_FRAME_BG, Texture.class);
        load(NEBULA_01, Texture.class);
        load(NEBULA_02, Texture.class);
        load(NEBULA_03, Texture.class);
        load(STAR_SYSTEM_FOLIAGE_01, Texture.class);
        load(STAR_SYSTEM_FOLIAGE_02, Texture.class);
        load(STAR_SYSTEM_FOLIAGE_03, Texture.class);
        load(STAR_SYSTEM_FOLIAGE_04, Texture.class);
        load(STAR_SYSTEM_FOLIAGE_05, Texture.class);
    }
}
