package com.mygdx.mechwargame.state;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygdx.mechwargame.core.MovementPathEffect;
import com.mygdx.mechwargame.core.character.Character;
import com.mygdx.mechwargame.core.faction.Faction;
import com.mygdx.mechwargame.core.mech.Mech;
import com.mygdx.mechwargame.core.ship.BaseShip;
import com.mygdx.mechwargame.core.world.Galaxy;
import com.mygdx.mechwargame.ui.view.common.CargoViewWindow;
import com.mygdx.mechwargame.ui.view.galaxy.ShipInfoLocalMenu;
import com.mygdx.mechwargame.ui.view.galaxy.StarLocalMenu;

import java.util.List;

public class GameData {

    public static boolean isPaused = true;
    public static Character mainCharacter;
    public static Mech mainCharacterMech;
    public static Galaxy galaxy;
    public static BaseShip starShip;
    public static Action galaxyMapPlayerAction;
    public static StarLocalMenu starLocalMenu;
    public static ShipInfoLocalMenu shipInfoLocalMenu;
    public static CargoViewWindow cargoViewWindow;
    public static MovementPathEffect movementPathEffect;
    public static List<Faction> factions;

    public static boolean lockGameStage = false;

}
