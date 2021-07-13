package com.mygdx.mechwargame.core.facility;

import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Facility {

    public int level;
    public String name;
    public Actor actor;

    public Facility(int level, String name) {
        this.level = level;
        this.name = name;
    }
}
