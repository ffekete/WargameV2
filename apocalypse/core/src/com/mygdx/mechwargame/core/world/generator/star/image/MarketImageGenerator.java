package com.mygdx.mechwargame.core.world.generator.star.image;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.facility.Marketplace;
import com.mygdx.mechwargame.ui.AnimatedDrawable;

import static com.mygdx.mechwargame.core.world.generator.star.image.AreaCleaner.addBuilding;

public class MarketImageGenerator {

    public static void generateMarketImage(com.mygdx.mechwargame.core.world.Star star) {
        star.facilities.stream().filter(f -> f instanceof Marketplace).forEach(f -> {

            Image marketImage = new Image(new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_MARKET, 32, 32, 0.2f));
            star.cityView.actors[5][5] = marketImage;
            marketImage.setSize(128, 128);
            marketImage.setPosition(5 * 64, 5 * 64);

            f.actor = marketImage;

            // market
            addBuilding(star, 5, 5);

            marketImage.addListener(new InputListener() {

                @Override
                public void enter(InputEvent event,
                                  float x,
                                  float y,
                                  int pointer,
                                  Actor fromActor) {
                    super.enter(event, x, y, pointer, fromActor);
                    ParallelAction parallelAction = new ParallelAction();
                    parallelAction.addAction(Actions.sizeTo(136, 136, 0.1f));
                    parallelAction.addAction(Actions.moveTo(5 * 64 - 4, 5 * 64 - 4, 0.1f));
                    marketImage.addAction(parallelAction);
                }

                @Override
                public void exit(InputEvent event,
                                 float x,
                                 float y,
                                 int pointer,
                                 Actor toActor) {
                    super.exit(event, x, y, pointer, toActor);
                    ParallelAction parallelAction = new ParallelAction();
                    parallelAction.addAction(Actions.sizeTo(128, 128, 0.1f));
                    parallelAction.addAction(Actions.moveTo(5 * 64, 5 * 64, 0.1f));
                    marketImage.addAction(parallelAction);
                }
            });
        });
    }

}
