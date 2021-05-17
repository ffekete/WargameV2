package com.blacksoft.hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.state.Config;

import static com.blacksoft.state.Config.TEXTURE_SIZE;

public class Rogue extends Hero {

    public static Texture texture;

    private final Animation<TextureRegion> animation;

    private float duration = 0f;

    static {
        texture = new Texture("hero/Rogue.png");
    }

    public Rogue(Party party) {
        super(party);
        this.hp = getMaxHp();
        this.mp = getMaxMp();
        animation = new Animation<>(0.6f, TextureRegion.split(texture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
    }

    @Override
    public float getSpeed() {
        return 1f;
    }

    @Override
    public int getSalaryRequest() {
        return 0;
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        duration += Gdx.graphics.getDeltaTime();
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.draw(animation.getKeyFrame(duration, true), getX(), getY());
        batch.setColor(color);
    }

    @Override
    public int getMaxHp() {
        return Config.ROGUE_MAX_HP;
    }

    @Override
    public int getMaxMp() {
        return Config.ROGUE_MAX_MP;
    }

    @Override
    public Animation<TextureRegion> getAnimation() {
        return this.animation;
    }
}