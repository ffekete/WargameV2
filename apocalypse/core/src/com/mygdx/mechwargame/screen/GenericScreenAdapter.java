package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.mechwargame.state.ScreenState;

public class GenericScreenAdapter extends ScreenAdapter {

    public final Stage stage = new Stage();

    @Override
    public void show() {
        stage.setViewport(ScreenState.viewport);
    }

    @Override
    public void resize(int width,
                       int height) {
        super.resize(width, height);

        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        stage.getViewport().apply(true);

        Gdx.graphics.getGL20().glClearColor(0, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act();
        stage.draw();
    }
}
