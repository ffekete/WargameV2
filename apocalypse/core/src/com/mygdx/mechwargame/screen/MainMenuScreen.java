package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.state.ScreenState;
import com.mygdx.mechwargame.ui.MainMenuUIFactory;

public class MainMenuScreen extends ScreenAdapter {

    public static final MainMenuScreen SCREEN = new MainMenuScreen();
    public static final Stage mainMenuStage = new Stage();

    private boolean loading = false;

    @Override
    public void show() {
        // loading assets
        loading = true;
        GameState.assetManager.load(AssetManagerV2.MAIN_MENU_BUTTON_BG_FRAME, Texture.class);
        mainMenuStage.setViewport(ScreenState.viewport);
    }

    @Override
    public void render(float delta) {
        mainMenuStage.getViewport().apply(true);

        Gdx.graphics.getGL20().glClearColor(0, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if (loading && GameState.assetManager.update()) {
                loading = false;
                finishedLoading();
        } else if(loading){
            return;
        }

        // draw
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

    private void finishedLoading() {
        Table table = new Table();

        ImageTextButton newGameButton = MainMenuUIFactory.getMenuButton("Start");
        ImageTextButton loadGameButton = MainMenuUIFactory.getMenuButton("Load");
        ImageTextButton optionsButton = MainMenuUIFactory.getMenuButton("Options");
        ImageTextButton exitButton = MainMenuUIFactory.getMenuButton("Exit");

        table.add(newGameButton).size(400, 80).pad(30).row();
        table.add(loadGameButton).size(400, 80).pad(30).row();
        table.add(optionsButton).size(400, 80).pad(30).row();
        table.add(exitButton).size(400, 80).pad(30).row();
        table.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        mainMenuStage.addActor(table);

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
}
