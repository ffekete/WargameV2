package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.screen.action.FlashingAction;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

public class GalaxySetupScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();

    @Override
    public void show() {
        super.show();

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        screenContentTable.background(new AnimatedDrawable(AssetManagerV2.MAIN_MENU_BACKGROUND, 1920, 1080, true, 0.15f));
        screenContentTable.add().width(1500).row();
        screenContentTable.add(UIFactoryCommon.getTextLabel("create the galaxy", UIFactoryCommon.fontMedium))
                .padBottom(50)
                .center()
                .row();

        Table galaxySetupParametersTable = new Table();
        galaxySetupParametersTable.background(new AnimatedDrawable(AssetManagerV2.CHARACTER_ATTRIBUTES_FRAME, 1500, 600, true, 0.1f));
        galaxySetupParametersTable.setSize(1500, 600);

        GalaxySetupParameters galaxySetupParameters = new GalaxySetupParameters();

        // galaxy size
        Table galaxySizeTable = new Table();
        galaxySizeTable.add(UIFactoryCommon.getTextLabel("galaxy size")).width(600);

        ImageTextButton galaxySizeDecreaseButton = UIFactoryCommon.getSmallRoundButton("-");
        galaxySizeTable.add(galaxySizeDecreaseButton)
                .padRight(10)
                .padBottom(10);

        ImageTextButton galaxySizeIncreaseButton = UIFactoryCommon.getSmallRoundButton("+");
        galaxySizeTable.add(galaxySizeIncreaseButton)
                .padRight(30);

        galaxySizeTable.add(UIFactoryCommon.getDynamicTextLabel(() -> galaxySetupParameters.sizes.get(galaxySetupParameters.defaultSize)))
                .width(300);
        galaxySetupParametersTable.add(galaxySizeTable)
                .row();

        // nr of stars
        Table starsSizeTable = new Table();
        starsSizeTable.add(UIFactoryCommon.getTextLabel("nr of stars")).width(600);

        ImageTextButton nrOfStarsDecreaseButton = UIFactoryCommon.getSmallRoundButton("-");
        starsSizeTable.add(nrOfStarsDecreaseButton)
                .padRight(10)
                .padBottom(10);

        ImageTextButton nrOfStarsIncreaseButton = UIFactoryCommon.getSmallRoundButton("+");
        starsSizeTable.add(nrOfStarsIncreaseButton)
                .padRight(30);

        starsSizeTable.add(UIFactoryCommon.getDynamicTextLabel(() -> String.valueOf(galaxySetupParameters.numberOfStars * galaxySetupParameters.numberOfStarsMultiplier)))
                .width(300);
        galaxySetupParametersTable.add(starsSizeTable)
                .row();

        // nr of factions
        Table factionsSizeTable = new Table();
        factionsSizeTable.add(UIFactoryCommon.getTextLabel("nr of factions")).width(600);

        ImageTextButton nrOfFactionsDecreaseButton = UIFactoryCommon.getSmallRoundButton("-");
        factionsSizeTable.add(nrOfFactionsDecreaseButton)
                .padRight(10)
                .padBottom(10);

        ImageTextButton nrOfFactionsIncreaseButton = UIFactoryCommon.getSmallRoundButton("+");
        factionsSizeTable.add(nrOfFactionsIncreaseButton)
                .padRight(30);

        factionsSizeTable.add(UIFactoryCommon.getDynamicTextLabel(() -> String.valueOf(galaxySetupParameters.defaultNumberOfFactions)))
                .width(300);
        galaxySetupParametersTable.add(factionsSizeTable)
                .row();

        // nr of pirates
        Table piratesSizeTable = new Table();
        piratesSizeTable.add(UIFactoryCommon.getTextLabel("nr of pirates")).width(600);

        ImageTextButton nrOfPiratesDecreaseButton = UIFactoryCommon.getSmallRoundButton("-");
        piratesSizeTable.add(nrOfPiratesDecreaseButton)
                .padRight(10)
                .padBottom(10);

        ImageTextButton nrOfPiratesIncreaseButton = UIFactoryCommon.getSmallRoundButton("+");
        piratesSizeTable.add(nrOfPiratesIncreaseButton)
                .padRight(30);

        piratesSizeTable.add(UIFactoryCommon.getDynamicTextLabel(() -> String.valueOf(galaxySetupParameters.numberOfPirates)))
                .width(300);
        galaxySetupParametersTable.add(piratesSizeTable)
                .row();

        nrOfStarsDecreaseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                galaxySetupParameters.numberOfStarsMultiplier = galaxySetupParameters.previousStarsMultiplier();
                return true;
            }
        });

        nrOfStarsIncreaseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                galaxySetupParameters.numberOfStarsMultiplier = galaxySetupParameters.nextStarsMultiplier();
                return true;
            }
        });

        nrOfFactionsIncreaseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                galaxySetupParameters.defaultNumberOfFactions = galaxySetupParameters.nextFactionsCount();
                return true;
            }
        });

        nrOfFactionsDecreaseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                galaxySetupParameters.defaultNumberOfFactions = galaxySetupParameters.previousFactionsCount();
                return true;
            }
        });

        nrOfPiratesIncreaseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                galaxySetupParameters.numberOfPirates = galaxySetupParameters.nextPiratesCount();
                return true;
            }
        });

        nrOfPiratesDecreaseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                galaxySetupParameters.numberOfPirates = galaxySetupParameters.previousPiratesCount();
                return true;
            }
        });

        galaxySizeIncreaseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                galaxySetupParameters.defaultSize = galaxySetupParameters.nextSize();
                return true;
            }
        });

        galaxySizeDecreaseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                galaxySetupParameters.defaultSize = galaxySetupParameters.previousSize();
                return true;
            }
        });


        screenContentTable.add(galaxySetupParametersTable)
                .center()
                .size(1500, 600)
                .padBottom(20)
                .row();

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event,
                                   int keycode) {
                System.exit(1);
                return super.keyDown(event, keycode);
            }
        });

        ImageTextButton finishButton = UIFactoryCommon.getMenuButton("finish");

        screenContentTable.add(finishButton)
                .center()
                .size(450, 80);

        finishButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                SequenceAction sequenceAction = new SequenceAction();
                sequenceAction.addAction(new FlashingAction(0.1f, 8, finishButton.getLabel()));
                sequenceAction.addAction(new SetScreenAction(new GalaxyCreatorScreen(galaxySetupParameters)));
                stage.addAction(sequenceAction);

                return true;
            }
        });

        stage.addActor(screenContentTable);

        Gdx.input.setInputProcessor(stage);
    }
}
