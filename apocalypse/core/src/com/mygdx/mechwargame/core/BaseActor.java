package com.mygdx.mechwargame.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

import static com.mygdx.mechwargame.Config.SECTOR_SIZE;

public class BaseActor extends Actor {

    public LayeredAnimatedImage layeredAnimatedImage;

    public BaseActor(LayeredAnimatedImage layeredAnimatedImage) {
        this.layeredAnimatedImage = layeredAnimatedImage;
        super.setOrigin(SECTOR_SIZE / 2f, SECTOR_SIZE / 2f);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        layeredAnimatedImage.setPosition(getX(), getY());
    }

    @Override
    public void setPosition(float x, float y, int alignment) {
        super.setPosition(x, y, alignment);
        layeredAnimatedImage.setPosition(getX(), getY(), alignment);
    }

    @Override
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        layeredAnimatedImage.setRotation(degrees);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        layeredAnimatedImage.draw(batch, parentAlpha);
    }

    @Override
    public void setScale(float scaleXY) {
        super.setScale(scaleXY);
        layeredAnimatedImage.setScale(scaleXY);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        layeredAnimatedImage.setSize(width, height);
    }

}
