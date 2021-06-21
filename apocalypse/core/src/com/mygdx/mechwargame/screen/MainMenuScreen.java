package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.screen.action.FlashingAction;
import com.mygdx.mechwargame.screen.action.PeriodicShakeAction;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.state.ScreenState;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.MainMenuUIFactory;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

public class MainMenuScreen extends GenericScreenAdapter {

    public static final MainMenuScreen SCREEN = new MainMenuScreen();

    private boolean loading = false;

    private Table loadingTextTable;
    private Label loadingText;

    @Override
    public void show() {
        // loading assets
        loading = true;
        GameState.assetManager.load(AssetManagerV2.MAIN_MENU_BUTTON_BG_FRAME, Texture.class);
        GameState.assetManager.load(AssetManagerV2.MAIN_MENU_PARALLAX_ANIM, Texture.class);
        GameState.assetManager.load(AssetManagerV2.TEXT_CURSOR, Texture.class);
        stage.setViewport(ScreenState.viewport);

        loadingTextTable = new Table();
        loadingText = UIFactoryCommon.getTextLabel("Loading...", UIFactoryCommon.fontLarge);
        loadingTextTable.add(loadingText);
        loadingTextTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        stage.addActor(loadingTextTable);
    }

    @Override
    public void render(float delta) {
        stage.getViewport().apply(true);

        Gdx.graphics.getGL20().glClearColor(0, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if (loading && GameState.assetManager.update()) {
            loading = false;
            finishedLoading();
        } else if (loading) {
            stage.getActors().removeValue(loadingTextTable, true);
            return;
        }

        // draw
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        super.hide();
    }



    private void finishedLoading() {
        Table table = new Table();

        final ImageTextButton newGameButton = MainMenuUIFactory.getMenuButton("Start");
        ImageTextButton loadGameButton = MainMenuUIFactory.getMenuButton("Load");
        ImageTextButton optionsButton = MainMenuUIFactory.getMenuButton("Options");
        ImageTextButton exitButton = MainMenuUIFactory.getMenuButton("Exit");

        Label titleLabel = UIFactoryCommon.getTextLabel("Monster Apocalypse", UIFactoryCommon.fontLarge, Color.GREEN);

        table.add(titleLabel).row();
        table.add().size(400, 80).row();
        table.add(newGameButton).size(400, 80).pad(20).row();
        table.add(loadGameButton).size(400, 80).pad(20).row();
        table.add(optionsButton).size(400, 80).pad(20).row();
        table.add(exitButton).size(400, 80).pad(20).row();
        table.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        table.add(new Image(new AnimatedDrawable(AssetManagerV2.MAIN_MENU_PARALLAX_ANIM))).size(577*2, 128*2);

        stage.addActor(table);

        Label versionLabel = UIFactoryCommon.getTextLabel(String.format("version %s", GameState.VERSION), UIFactoryCommon.fontSmall);
        versionLabel.setPosition(1500, 20);
        stage.addActor(versionLabel);

        newGameButton.addListener(new ClickListener(Input.Buttons.LEFT) {
            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(new FlashingAction(0.1f, 8, newGameButton.getLabel()));
                sequenceAction.addAction(new SetScreenAction(new CharacterCreationScreen()));
                stage.addAction(sequenceAction);

            }
        });

        Gdx.input.setInputProcessor(stage);
    }
}
