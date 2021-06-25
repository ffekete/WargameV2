package com.mygdx.mechwargame.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mechwargame.state.GameState;

import java.util.Random;

public class AnimatedDrawable extends TextureRegionDrawable {

    private final Animation<TextureRegion> animation;
    private float duration = 0f;
    private float delayCounter = 0;
    private float delay = 0;
    private boolean looping = true;

    public AnimatedDrawable(String file, int width, int height, boolean looping, float speed, float delay) {
        this(file, width, height, looping, speed);
        this.delay = delay;
        this.delayCounter = new Random().nextFloat() * delay;
    }

    public AnimatedDrawable(String file, int width, int height, boolean looping, float speed) {
        TextureRegion[][] textureRegion = TextureRegion.split(GameState.assetManager.get(file, Texture.class), width, height);
        this.animation = new Animation<>(speed, textureRegion[0]);
        this.looping = looping;
    }

    public AnimatedDrawable(String file) {
        TextureRegion[][] textureRegion = TextureRegion.split(GameState.assetManager.get(file, Texture.class), 577, 128);
        this.animation = new Animation<>(0.1f, textureRegion[0]);

    }

    @Override
    public void draw(Batch batch,
                     float x,
                     float y,
                     float width,
                     float height) {
        delayCounter += Gdx.graphics.getDeltaTime();

        if(delayCounter > delay) {
            duration += Gdx.graphics.getDeltaTime();
            batch.setColor(Color.WHITE);
            batch.draw(animation.getKeyFrame(duration, looping), x, y, width, height);

            if(duration > animation.getAnimationDuration()) {
                delayCounter = 0;
                duration = 0;
            }
        } else {
            batch.draw(animation.getKeyFrame(0, looping), x, y, width, height);
        }
    }
}
