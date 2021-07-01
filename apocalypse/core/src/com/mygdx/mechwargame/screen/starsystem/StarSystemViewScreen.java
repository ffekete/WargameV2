package com.mygdx.mechwargame.screen.starsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.world.GalaxySetupParameters;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.screen.action.FlashingAction;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.screen.galaxy.GalaxyCreatorScreen;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

public class StarSystemViewScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();

    @Override
    public void show() {
        super.show();

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        screenContentTable.background(new AnimatedDrawable(AssetManagerV2.MAIN_MENU_BACKGROUND, 1920, 1080, true, 0.15f));

        ImageButton backButton = UIFactoryCommon.getBackButton();
        backButton.setSize(64, 64);

        screenContentTable.addActor(backButton);

        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                super.touchDown(event, x, y, pointer, button);
                event.stop();
                return true;
            }

            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                event.stop();
                GameState.game.setScreen(GameState.galaxyViewScreen);
            }
        });


        stage.addActor(screenContentTable);

        Gdx.input.setInputProcessor(stage);
    }
}
