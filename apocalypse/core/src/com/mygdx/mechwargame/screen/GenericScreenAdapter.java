package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GenericScreenAdapter extends ScreenAdapter {

    public final Stage stage = new Stage();

    @Override
    public void resize(int width,
                       int height) {
        super.resize(width, height);

        stage.getViewport().update(width, height, true);
    }
}
