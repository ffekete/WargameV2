package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.world.Galaxy;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.core.world.generator.FactionDistributor;
import com.mygdx.mechwargame.core.world.generator.GalaxyStarDistributor;
import com.mygdx.mechwargame.core.world.generator.StarImageGenerator;
import com.mygdx.mechwargame.core.world.generator.StarSpreadGenerator;
import com.mygdx.mechwargame.state.GalaxyGeneratorState;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

import java.util.Random;

public class GalaxyCreatorScreen extends GenericScreenAdapter {

    private GalaxySetupParameters galaxySetupParameters;

    private Table screenContentTable = new Table();

    public GalaxyCreatorScreen(GalaxySetupParameters galaxySetupParameters) {
        this.galaxySetupParameters = galaxySetupParameters;
    }

    @Override
    public void show() {
        super.show();
        GameData.galaxy = new Galaxy(galaxySetupParameters);

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

        GalaxyStarDistributor.distributeStars(galaxySetupParameters);
        StarImageGenerator.generate(galaxySetupParameters);
        StarSpreadGenerator.spread(galaxySetupParameters);
        FactionDistributor.distribute(galaxySetupParameters);

        GameState.game.setScreen(new GalaxyViewScreen());
    }
}
