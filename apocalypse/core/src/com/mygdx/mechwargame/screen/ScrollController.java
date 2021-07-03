package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.state.GameData;

import static com.mygdx.mechwargame.Config.UNIT_SIZE;

public class ScrollController extends Actor {

    public Stage stage;
    public Camera camera;
    public int xOffset = 0;
    public int yOffset = 0;

    public ScrollController(Stage stage) {
        this.stage = stage;
        this.camera = stage.getViewport().getCamera();

    }

    @Override
    public void act(float delta) {
        camera.position.x += xOffset;

        if (camera.position.x < Config.SCREEN_WIDTH / 2f) {
            camera.position.x = Config.SCREEN_WIDTH / 2f;
        }

        if (camera.position.x > GameData.galaxy.width * UNIT_SIZE - Config.SCREEN_WIDTH / 2f) {
            camera.position.x = GameData.galaxy.width * UNIT_SIZE - Config.SCREEN_WIDTH / 2f;
        }

        camera.position.y += yOffset;

        if (camera.position.y < Config.SCREEN_HEIGHT / 2f - UNIT_SIZE) {
            camera.position.y = Config.SCREEN_HEIGHT / 2f - UNIT_SIZE;
        }

        if (camera.position.y > GameData.galaxy.height * UNIT_SIZE - Config.SCREEN_HEIGHT / 2f + UNIT_SIZE) {
            camera.position.y = GameData.galaxy.height * UNIT_SIZE - Config.SCREEN_HEIGHT / 2f + UNIT_SIZE;
        }
    }
}
