package com.mygdx.mechwargame.core.world.generator.star.image;

public class AreaCleaner {

    public static void clearArea(com.mygdx.mechwargame.core.world.Star star,
                                 int x,
                                 int y,
                                 int width,
                                 int height) {

        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                star.cityView.actors[i][j] = null;
            }
        }
    }

}
