package com.mygdx.mechwargame.screen.action;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygdx.mechwargame.state.GameState;

public class SetScreenAction extends Action {

    private ScreenAdapter screenAdapter;

    public SetScreenAction(ScreenAdapter screenAdapter) {
        this.screenAdapter = screenAdapter;
    }

    @Override
    public boolean act(float delta) {
        GameState.game.setScreen(this.screenAdapter);
        return true;
    }
}
