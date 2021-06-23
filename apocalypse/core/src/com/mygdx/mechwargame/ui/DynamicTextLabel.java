package com.mygdx.mechwargame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.function.Supplier;

public class DynamicTextLabel extends Label {

    private final Supplier<String> textSource;

    public DynamicTextLabel(CharSequence text,
                            LabelStyle style,
                            Supplier<String> textSource) {
        super(text, style);
        this.textSource = textSource;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.setText(textSource.get());
    }
}
