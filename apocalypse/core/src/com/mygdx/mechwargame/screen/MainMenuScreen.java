package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.character.Attributes;
import com.mygdx.mechwargame.core.character.Character;
import com.mygdx.mechwargame.core.character.Skills;
import com.mygdx.mechwargame.core.unit.BlackBear;
import com.mygdx.mechwargame.core.unit.Hellfire;
import com.mygdx.mechwargame.core.unit.Interceptor;
import com.mygdx.mechwargame.core.ship.BaseShip;
import com.mygdx.mechwargame.core.ship.SmallStarShip;
import com.mygdx.mechwargame.core.weapon.*;
import com.mygdx.mechwargame.screen.action.FlashingAction;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.screen.chargen.CharacterCreationScreen;
import com.mygdx.mechwargame.screen.galaxy.GalaxySetupScreen;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

public class MainMenuScreen extends GenericScreenAdapter {

    public static final MainMenuScreen SCREEN = new MainMenuScreen();

    private boolean loading = false;

    private Table loadingTextTable;
    private Label loadingText;

    @Override
    public void show() {

        Pixmap pm = new Pixmap(Gdx.files.internal(AssetManagerV2.MOUSE_POINTER));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
        pm.dispose();

        super.show();

        // loading assets
        loading = true;
        GameState.assetManager.loadAll();

        loadingTextTable = new Table();
        loadingText = UIFactoryCommon.getTextLabel("Loading...", UIFactoryCommon.fontLarge, Color.WHITE);
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
            stage.getActors().removeValue(loadingTextTable, true);
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

        final ImageTextButton quickStartButton = UIFactoryCommon.getMenuButton("Quick Start");
        final ImageTextButton newGameButton = UIFactoryCommon.getMenuButton("Start");
        ImageTextButton loadGameButton = UIFactoryCommon.getMenuButton("Load");
        ImageTextButton optionsButton = UIFactoryCommon.getMenuButton("Options");
        ImageTextButton exitButton = UIFactoryCommon.getMenuButton("Exit");

        Label titleLabel = UIFactoryCommon.getTextLabel("Monster Apocalypse", UIFactoryCommon.fontLarge, Color.GREEN);

        table.add(titleLabel).row();
        table.add().size(400, 80).row();
        table.add(quickStartButton).size(400, 80).pad(20).row();
        table.add(newGameButton).size(400, 80).pad(20).row();
        table.add(loadGameButton).size(400, 80).pad(20).row();
        table.add(optionsButton).size(400, 80).pad(20).row();
        table.add(exitButton).size(400, 80).pad(20).row();
        table.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

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
                sequenceAction.addAction(new FlashingAction(0.1f, 8, newGameButton));
                sequenceAction.addAction(new SetScreenAction(new CharacterCreationScreen()));
                stage.addAction(sequenceAction);
            }
        });

        quickStartButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {

                Character character = new Character();
                character.skillValues.put(Skills.Lasers, 3);
                character.attributeValues.put(Attributes.Endurance, 2);
                character.firstName = "Lukas";
                character.lastName = "Kovalenko";
                character.nickName = "Mole";

                GameData.mainCharacter = character;

                BaseShip starShip = new SmallStarShip();
                starShip.hangar.addUnit(new BlackBear());
                starShip.hangar.addUnit(new Interceptor());
                starShip.hangar.addUnit(new Hellfire());

                starShip.cargoBay.addItem(new LargeLaserCannon());
                starShip.cargoBay.addItem(new ShortRangeMissile());
                starShip.cargoBay.addItem(new LongRangeMissile());
                starShip.cargoBay.addItem(new AirToAirMissile());
                starShip.cargoBay.addItem(new AirToGroundMissile());

                GameData.starShip = starShip;

                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(new FlashingAction(0.1f, 8, quickStartButton));
                sequenceAction.addAction(new SetScreenAction(new GalaxySetupScreen()));
                stage.addAction(sequenceAction);
                return true;
            }
        });

        Gdx.input.setInputProcessor(stage);
    }
}
