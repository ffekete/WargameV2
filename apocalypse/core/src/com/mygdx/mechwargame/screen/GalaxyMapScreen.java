package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class GalaxyMapScreen extends GenericScreenAdapter {

    @Override
    public void show() {
        super.show();

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event,
                                   int keycode) {
                System.exit(1);
                return super.keyDown(event, keycode);
            }
        });

        Gdx.input.setInputProcessor(stage);
    }
}
