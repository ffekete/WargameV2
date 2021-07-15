package com.mygdx.mechwargame.core.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.mechwargame.core.BaseActor;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public class Nebulae extends BaseActor {


    public Nebulae(String path) {
        super(new LayeredAnimatedImage(new AnimatedDrawable(path, 128, 128, 1f)));
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        layeredAnimatedImage.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x,
                            float y) {
        super.setPosition(x, y);
        layeredAnimatedImage.setPosition(x, y);
    }
}
