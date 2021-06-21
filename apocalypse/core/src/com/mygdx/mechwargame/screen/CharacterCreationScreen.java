package com.mygdx.mechwargame.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.state.ScreenState;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

public class CharacterCreationScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();

    @Override
    public void show() {
        stage.setViewport(ScreenState.viewport);
        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        screenContentTable.add(UIFactoryCommon.getTextLabel("name", UIFactoryCommon.fontSmall)).left().width(250);
        screenContentTable.add(UIFactoryCommon.getTextField("enter name", "", UIFactoryCommon.fontSmall)).width(600).pad(10).row();
        screenContentTable.add(UIFactoryCommon.getTextLabel("nickname", UIFactoryCommon.fontSmall)).left().width(250);
        screenContentTable.add(UIFactoryCommon.getTextField("enter nickname", "", UIFactoryCommon.fontSmall)).width(600).pad(10).row();

        stage.addActor(screenContentTable);

        Gdx.input.setInputProcessor(stage);
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
