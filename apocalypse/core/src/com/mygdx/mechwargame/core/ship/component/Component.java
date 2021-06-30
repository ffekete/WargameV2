package com.mygdx.mechwargame.core.ship.component;

public abstract class Component {

    protected int level;

    public String name;

    public Component(int level, String name) {
        this.level = level;
        this.name = name;
    }

    int getLevel() {
        return this.level;
    }
    void setLevel(int value) {
        this.level = value;
    }

    public boolean canLevelUp() {
        return getLevel() < 10;
    }

    protected abstract void adjustValues(int level);

    public void levelUpValues() {
        if (getLevel() < 10) {
            setLevel(getLevel() + 1);
            adjustValues(getLevel());
        }
    }
}
