package com.mygdx.mechwargame.screen.view.star;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.facility.Facility;

import java.util.ArrayList;
import java.util.List;

public class CityView extends Table {

    public Actor[][] actors = new Actor[Config.CITY_WIDTH][8];

    List<Image> facilities = new ArrayList<>();

    public void layout() {

        setSize(1600, 512);

        for (int i = 0; i < Config.CITY_WIDTH; i++) {
            for (int j = Config.CITY_HEIGHT - 1; j >= 0; j--) {

                Actor actor = actors[i][j];

                if (actor != null) {
                    add(actor)
                            .size(64, 128);
                    actor.toFront();
                }
            }

            row();
        }

    }
}
