package com.mygdx.mechwargame.screen.starsystem.facility;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.mechwargame.core.facility.BlackMarket;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.screen.starsystem.BlackMarketViewScreen;

import static com.mygdx.mechwargame.Config.SCREEN_TRANSITION_DELAY;

public class BlackMarketClickHandler {

    public static void addClickListener(Table screenContentTable,
                                        Sector sector,
                                        Star star,
                                        Stage stage) {
        star.facilities.stream().filter(facility -> facility instanceof BlackMarket).forEach(f -> {

            ClickListener clickListener = new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event,
                                         float x,
                                         float y,
                                         int pointer,
                                         int button) {
                    // show market screen
                    SequenceAction sequenceAction = new SequenceAction();
                    AlphaAction alphaAction = new AlphaAction();
                    sequenceAction.addAction(alphaAction);
                    alphaAction.setAlpha(0);
                    alphaAction.setDuration(SCREEN_TRANSITION_DELAY);
                    alphaAction.setActor(screenContentTable);

                    sequenceAction.addAction(new SetScreenAction(new BlackMarketViewScreen(star, sector)));

                    stage.addAction(sequenceAction);
                    return true;
                }
            };

            f.actor.addListener(clickListener);
        });
    }
}
