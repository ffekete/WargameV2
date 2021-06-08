package com.mygdx.apocalypsegame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.apocalypsegame.Config;
import com.mygdx.apocalypsegame.ui.MainMenuUIFactory;

public class MainMenuScreen extends ScreenAdapter {

    public static final MainMenuScreen SCREEN = new MainMenuScreen();
    public static final Stage mainMenuStage = new Stage();

    @Override
    public void show() {
        super.show();

        Table table = new Table();

        ImageTextButton newGameButton = MainMenuUIFactory.getMenuButton("Start");
        ImageTextButton loadGameButton = MainMenuUIFactory.getMenuButton("Load");
        ImageTextButton optionsButton = MainMenuUIFactory.getMenuButton("Options");
        ImageTextButton exitButton = MainMenuUIFactory.getMenuButton("Exit");

        table.add(newGameButton).row();
        table.add(loadGameButton).row();
        table.add(optionsButton).row();
        table.add(exitButton).row();
        table.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        mainMenuStage.addActor(table);
        mainMenuStage.setViewport(ScreenState.viewport);

        mainMenuStage.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event,
                                 int keycode) {
                System.exit(0);
                return true;
            }
        });

        Gdx.input.setInputProcessor(mainMenuStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        mainMenuStage.getViewport().apply(true);

        Gdx.graphics.getGL20().glClearColor(0, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        mainMenuStage.act();
        mainMenuStage.draw();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void resize(int width,
                       int height) {
        super.resize(width, height);

        mainMenuStage.getViewport().update(width, height, true);
    }
}
