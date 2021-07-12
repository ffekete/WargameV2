package com.mygdx.mechwargame.screen.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CityView extends Table {

    public Actor[][] actors = new Actor[25][8];
    public boolean[][] occupied = new boolean[25][8];


    public void layout() {

        setSize(1600, 512);

        for (int i = 0; i < 25; i++) {
            for (int j = 6; j >= 0; j--) {

                Actor actor = actors[i][j];

                if(actor != null) {
                    add(actor)
                            .size(64, 128);
                }
            }

            row();
        }

    }
}
