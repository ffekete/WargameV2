package com.mygdx.mechwargame.screen.action;

import com.badlogic.gdx.scenes.scene2d.Action;

import java.util.function.Supplier;

public class ConditionalAction extends Action {

    private Action actionToComplete;
    private Supplier<Boolean> predicate;

    public ConditionalAction(Action actionToComplete,
                             Supplier<Boolean> predicate) {
        this.actionToComplete = actionToComplete;
        this.predicate = predicate;
    }

    @Override
    public boolean act(float delta) {
        if (this.predicate.get()) {
            return actionToComplete.act(delta);
        } else {
            System.out.println("condition failed");
            return true;
        }
    }
}
