package com.mygdx.mechwargame.screen.galaxy.inputevent;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.mechwargame.screen.action.ShowAction;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.ui.view.galaxy.HangarViewWindow;

public class MechBayClickEvent {

    public static void handle(SequenceAction sequenceAction,
                              Stage stage) {

        HangarViewWindow hangarViewWindow = new HangarViewWindow(stage);
        GameData.hangarViewWindow = hangarViewWindow;

        Camera camera = stage.getCamera();
        hangarViewWindow.setVisible(false);

        hangarViewWindow.setPosition(camera.position.x - hangarViewWindow.getWidth() / 2f, camera.position.y - hangarViewWindow.getHeight() / 2f);

        stage.addActor(hangarViewWindow);

        ShowAction visibleAction = new ShowAction();
        visibleAction.setVisible(true);

        sequenceAction.addAction(visibleAction);

        visibleAction.setActor(hangarViewWindow);
        visibleAction.setTarget(hangarViewWindow);

    }

}
