package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.Gdx;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;

public class GalaxyCreatorScreen extends GenericScreenAdapter {

    private GalaxySetupParameters galaxySetupParameters;

    public GalaxyCreatorScreen(GalaxySetupParameters galaxySetupParameters) {
        this.galaxySetupParameters = galaxySetupParameters;
    }

    @Override
    public void show() {
        super.show();

        Gdx.input.setInputProcessor(stage);
    }
}
