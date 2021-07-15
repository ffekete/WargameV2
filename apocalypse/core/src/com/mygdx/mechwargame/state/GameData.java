package com.mygdx.mechwargame.state;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.mechwargame.core.MovementPathEffect;
import com.mygdx.mechwargame.core.character.Character;
import com.mygdx.mechwargame.core.faction.Faction;
import com.mygdx.mechwargame.core.unit.BaseUnit;
import com.mygdx.mechwargame.core.ship.BaseShip;
import com.mygdx.mechwargame.core.world.Galaxy;
import com.mygdx.mechwargame.screen.view.galaxy.CargoViewWindow;
import com.mygdx.mechwargame.screen.view.galaxy.HangarViewWindow;
import com.mygdx.mechwargame.screen.view.galaxy.ShipInfoView;
import com.mygdx.mechwargame.screen.view.galaxy.StarLocalMenu;

import java.util.List;

public class GameData {

    public static boolean isPaused = true;
    public static Character mainCharacter;
    public static BaseUnit mainCharacterBaseUnit;
    public static Galaxy galaxy;
    public static BaseShip starShip;
    public static Action galaxyMapPlayerAction;
    public static StarLocalMenu starLocalMenu;
    public static ShipInfoView shipInfoView;
    public static CargoViewWindow cargoViewWindow;
    public static HangarViewWindow hangarViewWindow;
    public static MovementPathEffect movementPathEffect;
    public static List<Faction> factions;

    public static boolean lockGameStage = false;

    public static Image[][] bgImages;

}
