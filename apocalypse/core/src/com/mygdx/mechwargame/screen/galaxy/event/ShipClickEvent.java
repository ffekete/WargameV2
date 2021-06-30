package com.mygdx.mechwargame.screen.galaxy.event;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.mechwargame.screen.action.ShowAction;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.ui.view.galaxy.ShipInfoLocalMenu;

public class ShipClickEvent {

    public static void handle(SequenceAction sequenceAction,
                              Stage stage) {

        ShipInfoLocalMenu shipInfoLocalMenu = new ShipInfoLocalMenu(stage);
        GameData.shipInfoLocalMenu = shipInfoLocalMenu;

        ShowAction visibleAction = new ShowAction();
        visibleAction.setVisible(true);

        sequenceAction.addAction(visibleAction);

        visibleAction.setActor(shipInfoLocalMenu);
        visibleAction.setTarget(shipInfoLocalMenu);
    }

}
