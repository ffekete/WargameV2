package com.mygdx.mechwargame.core.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.mechwargame.core.BaseActor;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

import static com.mygdx.mechwargame.Config.SCALE;

public class Star extends BaseActor {

    public Rectangle bounds;
    public String name;

    public Star() {
        super(null);
    }

    public Star(LayeredAnimatedImage layeredAnimatedImage) {
        super(layeredAnimatedImage);
    }

    public void setStarAnimation(String file,
                                 int width,
                                 int length) {
        AnimatedDrawable animatedDrawable = new AnimatedDrawable(file, width, length, true, 0.25f, 30);
        this.layeredAnimatedImage = new LayeredAnimatedImage(animatedDrawable);
        this.layeredAnimatedImage.setPosition(getX(), getY());
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

    @Override
    public void setBounds(float x,
                          float y,
                          float width,
                          float height) {
        super.setBounds(x, y, width, height);
        this.bounds = new Rectangle(x, y, width, height);
        this.setWidth(width);
        this.setHeight(height);
        this.setX(x);
        this.setY(y);
        layeredAnimatedImage.setPosition(x, y);
        layeredAnimatedImage.setWidth(width);
        layeredAnimatedImage.setHeight(height);
    }
}
