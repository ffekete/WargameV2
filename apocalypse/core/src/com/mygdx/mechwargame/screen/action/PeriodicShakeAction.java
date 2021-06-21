package com.mygdx.mechwargame.screen.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

public class PeriodicShakeAction extends Action {

    private float ox, oy;
    private Actor actor;
    private float durationCounter = 0;
    private float delayCounter = 0;

    private float delay;
    private float duration;
    private float intensity;

    public PeriodicShakeAction(Actor actor, float delay, float duration, float intensity) {
        this.actor = actor;
        this.ox = actor.getX();
        this.oy = actor.getY();
        this.delay = delay;
        this.duration = duration;
        this.intensity = intensity;
    }


    @Override
    public boolean act(float delta) {

        if(delayCounter < delay) {
            delayCounter += delta;
        }

        if(delayCounter >= delay && durationCounter <= duration) {
            durationCounter += delta;
        }

        if(durationCounter > 0f) {

            float offSetX = intensity / 2f - new Random().nextFloat() * intensity;
            float offSetY = intensity / 2f - new Random().nextFloat() * intensity;

            actor.setPosition(ox + offSetX, oy + offSetY);

            if(durationCounter >= duration) {
                durationCounter = 0f;
                delayCounter = 0f;
                actor.setPosition(ox, oy);
            }
        }

        return false;
    }
}
