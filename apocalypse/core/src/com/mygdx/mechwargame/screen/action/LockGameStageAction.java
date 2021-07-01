package com.mygdx.mechwargame.screen.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygdx.mechwargame.state.GameData;

public class LockGameStageAction extends Action {

    private boolean lock;

    public LockGameStageAction(boolean lock) {
        this.lock = lock;
    }

    @Override
    public boolean act(float delta) {
        GameData.lockGameStage = this.lock;
        return true;
    }
}
