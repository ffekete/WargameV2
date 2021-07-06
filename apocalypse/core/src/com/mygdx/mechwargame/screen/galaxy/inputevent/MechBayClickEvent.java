package com.mygdx.mechwargame.screen.galaxy.inputevent;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.mechwargame.screen.action.ShowAction;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.ui.view.galaxy.MechBayViewWindow;

public class MechBayClickEvent {

    public static void handle(SequenceAction sequenceAction,
                              Stage stage) {

        MechBayViewWindow mechBayViewWindow = new MechBayViewWindow(stage);
        GameData.mechBayViewWindow = mechBayViewWindow;

        Camera camera = stage.getCamera();
        mechBayViewWindow.setVisible(false);

        mechBayViewWindow.setPosition(camera.position.x - mechBayViewWindow.getWidth() / 2f, camera.position.y - mechBayViewWindow.getHeight() / 2f);

        stage.addActor(mechBayViewWindow);

        ShowAction visibleAction = new ShowAction();
        visibleAction.setVisible(true);

        sequenceAction.addAction(visibleAction);

        visibleAction.setActor(mechBayViewWindow);
        visibleAction.setTarget(mechBayViewWindow);

    }

}
