package com.mygdx.mechwargame.screen.galaxy.inputevent;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.mechwargame.screen.action.ShowAction;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.screen.view.galaxy.ShipInfoView;

public class ShipClickEvent {

    public static void handle(SequenceAction sequenceAction,
                              Stage stage) {

        ShipInfoView shipInfoView = new ShipInfoView(stage);
        GameData.shipInfoView = shipInfoView;

        ShowAction visibleAction = new ShowAction();
        visibleAction.setVisible(true);

        sequenceAction.addAction(visibleAction);

        visibleAction.setActor(shipInfoView);
        visibleAction.setTarget(shipInfoView);
    }

}
