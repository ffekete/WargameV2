package com.mygdx.mechwargame.core.world;

public class Galaxy {

    public Sector[][] sectors;

    public int width;
    public int height;

    public Galaxy(GalaxySetupParameters galaxySetupParameters) {
        this.sectors = new Sector[galaxySetupParameters.width * galaxySetupParameters.defaultSize][galaxySetupParameters.height * galaxySetupParameters.defaultSize];

        this.width = galaxySetupParameters.width * galaxySetupParameters.defaultSize;
        this.height = galaxySetupParameters.height * galaxySetupParameters.defaultSize;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.sectors[i][j] = new Sector();
            }
        }
    }
}
