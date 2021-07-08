package com.mygdx.mechwargame.core.facility;

public abstract class Facility {

    public int level;
    public String name;

    public Facility(int level, String name) {
        this.level = level;
        this.name = name;
    }
}
