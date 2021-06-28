package com.mygdx.mechwargame.screen.event.galaxyscreen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.action.ShowAction;
import com.mygdx.mechwargame.ui.view.galaxy.StarLocalMenu;

public class StarClickEvent {

    public static void handle(SequenceAction sequenceAction,
                              Star star,
                              Stage stage) {

        StarLocalMenu starLocalMenu = new StarLocalMenu(star, stage);

        ShowAction visibleAction = new ShowAction();
        visibleAction.setVisible(true);

        sequenceAction.addAction(visibleAction);
        visibleAction.setActor(starLocalMenu);
        visibleAction.setTarget(starLocalMenu);
    }

}
