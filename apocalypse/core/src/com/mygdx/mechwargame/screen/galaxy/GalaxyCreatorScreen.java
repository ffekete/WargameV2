package com.mygdx.mechwargame.screen.galaxy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.world.Galaxy;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.generator.*;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

import java.util.Random;

public class GalaxyCreatorScreen extends GenericScreenAdapter {

    private GalaxySetupParameters galaxySetupParameters;

    private Table screenContentTable = new Table();

    private boolean finishedGenerating = false;

    public GalaxyCreatorScreen(GalaxySetupParameters galaxySetupParameters) {
        this.galaxySetupParameters = galaxySetupParameters;
    }

    @Override
    public void show() {
        super.show();
        GameData.galaxy = new Galaxy(galaxySetupParameters);
        stage.getViewport().apply(true);
        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        screenContentTable.add(UIFactoryCommon.getDynamicTextLabel(() -> GalaxyGeneratorState.state))
                .center()
                .bottom();

        stage.addActor(screenContentTable);
        Gdx.input.setInputProcessor(stage);

        Random random = new Random(galaxySetupParameters.seed);
        StarImageGenerator.random = random;
        StarSpreadGenerator.random = random;
        GalaxyStarDistributor.random = random;
        FactionDistributor.random = random;
        PiratesDistributor.random = random;

        new Thread() {
            @Override
            public void run() {
                GalaxyStarDistributor.distributeStars(galaxySetupParameters);
                StarImageGenerator.generate(galaxySetupParameters);
                StarSpreadGenerator.spread(galaxySetupParameters);
                FactionDistributor.distribute(galaxySetupParameters);
                PiratesDistributor.distribute(galaxySetupParameters);
                finishedGenerating = true;
            }
        }.start();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        refreshScreen();
        if(finishedGenerating) {
            GameState.galaxyViewScreen = new GalaxyViewScreen();
            GameState.game.setScreen(GameState.galaxyViewScreen);
        }
    }

    private void refreshScreen() {
        stage.act();
        stage.draw();
    }
}
