package com.mygdx.mechwargame.screen.action;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FlashingAction extends Action {

    private float duration;
    private int times;
    private float counter;
    private Actor actor;
    private boolean visible = true;

    public FlashingAction(float duration, int times, Actor actor) {
        this.duration = duration;
        this.actor = actor;
        this.times = times;
    }

    @Override
    public boolean act(float delta) {
        counter += delta;
        if (counter >= duration){
            visible = !visible;
            counter = 0;

            if (visible) {
                actor.setColor(Color.WHITE);
            } else {
                actor.setColor(Color.BLACK);
            }

            times--;
        }
        return times <= 0;
    }
}
