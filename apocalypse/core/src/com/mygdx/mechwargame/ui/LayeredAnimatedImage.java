package com.mygdx.mechwargame.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LayeredAnimatedImage extends Image {

    List<AnimatedDrawable> animatedDrawables;

    public LayeredAnimatedImage(AnimatedDrawable... animatedDrawable) {
        animatedDrawables = Arrays.stream(animatedDrawable)
                .collect(Collectors.toList());
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        animatedDrawables.forEach(animatedDrawable -> {
            animatedDrawable.rotation = getRotation();
            animatedDrawable.scale = getScaleX();
            animatedDrawable.draw(batch, getX(), getY(), getWidth(), getHeight());
        });
    }
}
