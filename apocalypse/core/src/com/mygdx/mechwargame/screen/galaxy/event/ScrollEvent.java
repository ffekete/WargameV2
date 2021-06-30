package com.mygdx.mechwargame.screen.galaxy.event;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.mechwargame.screen.ScrollController;

public class ScrollEvent {

    public static boolean check(float x, float y, Stage stage, ScrollController scrollController) {
        Vector2 coord = stage.stageToScreenCoordinates(new Vector2(x, y));

        if (coord.x < 15) {
            scrollController.xOffset = -10;
        } else if (coord.x > stage.getViewport().getScreenWidth() - 10) {
            scrollController.xOffset = 10;
        } else {
            scrollController.xOffset = 0;
        }

        if (coord.y < 10) {
            scrollController.yOffset = 10;
        } else if (coord.y > stage.getViewport().getScreenHeight() - 10) {
            scrollController.yOffset = -10;
        } else {
            scrollController.yOffset = 0;
        }
        return true;
    }

}
