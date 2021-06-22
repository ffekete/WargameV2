package com.mygdx.mechwargame.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.state.ScreenState;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

public class CharacterCreationScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();
    private int logoIndex = 0;

    @Override
    public void show() {
        stage.setViewport(ScreenState.viewport);
        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        screenContentTable.add(UIFactoryCommon.getTextLabel("name", UIFactoryCommon.fontSmall))
                .left()
                .width(250);

        screenContentTable.add(UIFactoryCommon.getTextField("enter name", "", UIFactoryCommon.fontSmall))
                .width(600)
                .pad(10)
                .colspan(5)
                .row();

        screenContentTable.add(UIFactoryCommon.getTextLabel("nickname", UIFactoryCommon.fontSmall)).left()
                .width(250);
        screenContentTable.add(UIFactoryCommon.getTextField("enter nickname", "", UIFactoryCommon.fontSmall))
                .width(600)
                .pad(10, 10, 50, 10)
                .colspan(5)
                .row();


        screenContentTable.add(UIFactoryCommon.getTextLabel("Choose logo", UIFactoryCommon.fontSmall)).size(128);
        screenContentTable.add().size(128, 128);
        TextButton scrollLogoLeftButton = UIFactoryCommon.getTextButton("<", UIFactoryCommon.fontLarge);
        screenContentTable.add(scrollLogoLeftButton).size(128).right();

        final Image logoImage = new Image(GameState.assetManager.get(GameState.assetManager.logos.get(logoIndex), Texture.class));
        final Cell<Image> logoImgeCell = screenContentTable.add(logoImage).size(128, 128).center();
        TextButton scrollLogoRightButton = UIFactoryCommon.getTextButton(">", UIFactoryCommon.fontLarge);
        screenContentTable.add(scrollLogoRightButton).size(128).left();

        scrollLogoRightButton.addListener(new ClickListener(Input.Buttons.LEFT) {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                logoIndex++;
                if (logoIndex >= GameState.assetManager.logos.size()) {
                    logoIndex = 0;
                }

                logoImgeCell.setActor(new Image(GameState.assetManager.get(GameState.assetManager.logos.get(logoIndex), Texture.class)));

                return true;
            }
        });
        screenContentTable.add().size(128, 128).row();

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
