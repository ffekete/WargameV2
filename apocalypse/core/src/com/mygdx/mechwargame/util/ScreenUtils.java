package com.mygdx.mechwargame.util;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.mechwargame.Config;

public class ScreenUtils {

    public static void repositionToScreenIfNotInFrustum(Camera camera, Actor actor) {
        if (!camera.frustum.pointInFrustum(actor.getX(), camera.position.y, 0)) {
            actor.setPosition(camera.position.x - Config.SCREEN_WIDTH / 2f, actor.getY());
        }

        if (!camera.frustum.pointInFrustum(camera.position.x, actor.getY(), 0)) {
            actor.setPosition(actor.getX(), camera.position.y - Config.SCREEN_HEIGHT / 2f + 20);
        }

        if (!camera.frustum.pointInFrustum(actor.getX() + actor.getWidth(), camera.position.y, 0)) {
            actor.setPosition(camera.position.x + Config.SCREEN_WIDTH / 2f - actor.getWidth() - 10, actor.getY());
        }

        if (!camera.frustum.pointInFrustum(camera.position.x, actor.getY() + actor.getHeight(), 0)) {
            actor.setPosition(actor.getX(), camera.position.y + Config.SCREEN_HEIGHT / 2f - actor.getHeight() - 10);
        }
    }
}
