package com.mygdx.mechwargame.screen.starsystem.facility;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.mechwargame.core.facility.Marketplace;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.screen.starsystem.MarketViewScreen;
import com.mygdx.mechwargame.text.MarketplaceDialogueCreator;

import static com.mygdx.mechwargame.Config.SCREEN_TRANSITION_DELAY;

public class MarketPlaceFactory {

    public static void addMarketPlace(Table dialogueTable,
                                      Table screenContentTable,
                                      Sector sector,
                                      Star star,
                                      Stage stage) {
        if (star.facilities.stream().anyMatch(facility -> facility instanceof Marketplace)) {

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

                    sequenceAction.addAction(new SetScreenAction(new MarketViewScreen(star, sector)));

                    stage.addAction(sequenceAction);
                    return true;
                }
            };

            FactoryCommons.addTextToDialogueBox(dialogueTable, "1", MarketplaceDialogueCreator.generate(star, sector), clickListener);
        }
    }
}
