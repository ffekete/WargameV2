package com.mygdx.mechwargame.core.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public class Star extends Actor {

    public LayeredAnimatedImage layeredAnimatedImage;

    public void setStarAnimation(String file,
                                 int width,
                                 int length) {
        AnimatedDrawable animatedDrawable = new AnimatedDrawable(file, width, length, true, 0.1f);
        this.layeredAnimatedImage = new LayeredAnimatedImage(animatedDrawable);
        this.layeredAnimatedImage.setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        layeredAnimatedImage.draw(batch, parentAlpha);
    }
}
