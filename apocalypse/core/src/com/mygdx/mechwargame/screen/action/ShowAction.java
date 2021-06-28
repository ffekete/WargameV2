package com.mygdx.mechwargame.screen.action;

import com.badlogic.gdx.scenes.scene2d.actions.VisibleAction;

public class ShowAction extends VisibleAction {

    @Override
    public boolean act(float delta) {
        System.out.println("Triggered");
        return super.act(delta);
    }
}
