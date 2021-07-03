package com.mygdx.mechwargame.screen.galaxy.event;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.action.ConditionalAction;
import com.mygdx.mechwargame.screen.action.ShowAction;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.ui.view.galaxy.StarLocalMenu;

import static com.mygdx.mechwargame.Config.UNIT_SIZE;

public class StarClickEvent {

    public static void handle(SequenceAction sequenceAction,
                              Star star,
                              Sector sector,
                              Stage stage,
                              float x,
                              float y) {

        StarLocalMenu starLocalMenu = new StarLocalMenu(star, sector, stage);
        GameData.starLocalMenu = starLocalMenu;

        ShowAction visibleAction = new ShowAction();
        visibleAction.setVisible(true);

        ConditionalAction conditionalAction = new ConditionalAction(visibleAction, () -> {

            float a = Math.abs((GameData.starShip.getX() + UNIT_SIZE / 2f) - x);
            float b = Math.abs((GameData.starShip.getY() + UNIT_SIZE / 2f) - y);

            return (Math.sqrt(a * a + b * b) < 128);
        });

        sequenceAction.addAction(conditionalAction);

        visibleAction.setActor(starLocalMenu);
        visibleAction.setTarget(starLocalMenu);
    }

}
