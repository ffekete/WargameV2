package com.mygdx.mechwargame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

import java.util.function.Supplier;

public class DynamicProgressBar extends ProgressBar {

    private Supplier<Float> valueSupplier;
    private Supplier<Float> maxValueSupplier;

    public DynamicProgressBar(float min,
                              float max,
                              float stepSize,
                              boolean vertical,
                              ProgressBarStyle style,
                              Supplier<Float> valueSupplier,
                              Supplier<Float> maxValueSupplier) {
        super(min, max, stepSize, vertical, style);
        this.valueSupplier = valueSupplier;
        this.maxValueSupplier = maxValueSupplier;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setValue(valueSupplier.get());
        setRange(0, maxValueSupplier.get());
    }
}