package com.mygdx.mechwargame.core.world;

import com.badlogic.gdx.utils.Array;

public class Galaxy {

    public Sector[][] sectors;

    public Galaxy(GalaxySetupParameters galaxySetupParameters) {
        this.sectors = new Sector[galaxySetupParameters.height * galaxySetupParameters.defaultSize][galaxySetupParameters.width * galaxySetupParameters.defaultSize];

        for (int i = 0; i < galaxySetupParameters.height; i++) {
            for (int j = 0; j < galaxySetupParameters.width; j++) {
                this.sectors[i][j] = new Sector();
            }
        }
    }
}
