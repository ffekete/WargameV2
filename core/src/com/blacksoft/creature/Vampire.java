package com.blacksoft.creature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.blacksoft.state.Config.TEXTURE_SIZE;
import static com.blacksoft.state.Config.VAMPIRE_MAX_HP;
import static com.blacksoft.state.Config.VAMPIRE_SALARY_REQUEST;

public class Vampire extends Creature {

    static {
        texture = new Texture("creature/Vampire.png");
    }

    private float duration = 0f;

    public Vampire() {
        this.animation = new Animation<>(0.4f, TextureRegion.split(texture, TEXTURE_SIZE, TEXTURE_SIZE)[0]);
        this.hp = this.getMaxHp();
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
    public float getSpeed() {
        return 1f;
    }

    @Override
    public int getSalaryRequest() {
        return VAMPIRE_SALARY_REQUEST;
    }

    @Override
    public int getMaxHp() {
        return VAMPIRE_MAX_HP;
    }
}
