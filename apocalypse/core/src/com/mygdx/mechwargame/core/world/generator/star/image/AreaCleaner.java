package com.mygdx.mechwargame.core.world.generator.star.image;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.ui.AnimatedDrawable;

public class AreaCleaner {

    public static void addBuilding(com.mygdx.mechwargame.core.world.Star star,
                                    int x,
                                    int y) {
        star.cityView.actors[x][y + 1] = null;
        star.cityView.actors[x + 1][y + 1] = null;
        star.cityView.actors[x + 1][y] = null;

        star.cityView.actors[x][y - 1] = new Image(new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_DECORATION_02, 16, 32, 0.25f));

        star.cityView.actors[x][y - 1].setSize(64, 128);
        star.cityView.actors[x][y - 1].setPosition((x) * 64, (y - 1) * 64);

        star.cityView.actors[x + 1][y - 1] = new Image(new AnimatedDrawable(AssetManagerV2.STAR_SYSTEM_DECORATION_02, 16, 32, 0.25f));
        star.cityView.actors[x + 1][y - 1].setSize(64, 128);
        star.cityView.actors[x + 1][y - 1].setPosition((x + 1) * 64, (y - 1) * 64);

        star.cityView.actors[x + 1][y - 1].setTouchable(Touchable.disabled);
        star.cityView.actors[x][y - 1].setTouchable(Touchable.disabled);

        star.cityView.actors[x + 1][y - 1].setColor(0.8f / (y / 2f), 0.8f / (y / 2f), 0.8f / (y / 2f), 1f);
        star.cityView.actors[x][y - 1].setColor(0.8f / (y / 2f), 0.8f / (y / 2f), 0.8f / (y / 2f), 1f);
    }

}
