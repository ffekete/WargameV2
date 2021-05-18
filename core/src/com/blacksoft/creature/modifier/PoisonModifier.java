package com.blacksoft.creature.modifier;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.blacksoft.creature.Creature;
import com.blacksoft.creature.action.ReduceHpAction;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.skill.Poison;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;
import com.blacksoft.ui.AnimatedImage;

public class PoisonModifier implements Modifier {

    private int duration;
    private Creature target;
    private int amount;

    public PoisonModifier(int duration,
                          Creature target,
                          int amount) {
        this.duration = duration;
        this.target = target;
        this.amount = amount;
    }

    @Override
    public void apply(SequenceAction sequenceAction) {
        if (duration > 0) {
            duration--;

            AnimatedImage image = GameState.battleImages.get(target);
            Label label = UIFactory.I.createFloatingLabelWithIcon(amount, new TextureRegion(Poison.icon), (int) image.getX() + 90 + 24, (int) image.getY() + 60 + 48, sequenceAction);
            sequenceAction.addAction(Actions.delay(0.85f));
            sequenceAction.addAction(new ReduceHpAction(target, amount));
            label.setColor(Color.GREEN);
        }
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void finish(SequenceAction sequenceAction) {

    }

    @Override
    public void start(SequenceAction sequenceAction) {
        AnimatedImage image = GameState.battleImages.get(target);
        UIFactory.I.createFloatingIcon(UIState.poisonEffectTexture, (int) image.getX() + 90 + 24, (int) image.getY() + 60 + 32, sequenceAction);
        sequenceAction.addAction(Actions.delay(0.5f));
    }
}
