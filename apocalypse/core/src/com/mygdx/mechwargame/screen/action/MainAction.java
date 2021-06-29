package com.mygdx.mechwargame.screen.action;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.mechwargame.state.GameData;

public class MainAction extends SequenceAction {

    @Override
    public boolean act(float delta) {
        if(GameData.isPaused) {
            return false;
        }
        boolean result = super.act(delta);
        return result;
    }
}
