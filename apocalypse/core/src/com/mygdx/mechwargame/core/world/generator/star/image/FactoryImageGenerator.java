package com.mygdx.mechwargame.core.world.generator.star.image;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.facility.Factory;
import com.mygdx.mechwargame.ui.AnimatedDrawable;

import static com.mygdx.mechwargame.core.world.generator.star.image.AreaCleaner.clearArea;

public class FactoryImageGenerator {

    public static void generateImage(com.mygdx.mechwargame.core.world.Star star) {
        star.facilities.stream().filter(f -> f instanceof Factory).forEach(f -> {

            Image factoryImage = new Image(new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_FACTORY, 48, 32, 0.2f));

            int px = 10;
            int py = 3;

            // market
            clearArea(star, px, py, 3, 2);

            star.cityView.actors[px][py] = factoryImage;
            factoryImage.setSize(192, 128);
            factoryImage.setPosition(px * 64, py * 64);

            f.actor = factoryImage;

            factoryImage.addListener(new InputListener() {

                @Override
                public void enter(InputEvent event,
                                  float x,
                                  float y,
                                  int pointer,
                                  Actor fromActor) {
                    super.enter(event, x, y, pointer, fromActor);
                    ParallelAction parallelAction = new ParallelAction();
                    parallelAction.addAction(Actions.sizeTo(200, 136, 0.1f));
                    parallelAction.addAction(Actions.moveTo(px * 64 - 4, py * 64 - 4, 0.1f));
                    factoryImage.addAction(parallelAction);
                }

                @Override
                public void exit(InputEvent event,
                                 float x,
                                 float y,
                                 int pointer,
                                 Actor toActor) {
                    super.exit(event, x, y, pointer, toActor);
                    ParallelAction parallelAction = new ParallelAction();
                    parallelAction.addAction(Actions.sizeTo(192, 128, 0.1f));
                    parallelAction.addAction(Actions.moveTo(px * 64, py * 64, 0.1f));
                    factoryImage.addAction(parallelAction);
                }
            });
        });
    }

}
