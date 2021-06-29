package com.mygdx.mechwargame.core.faction;

import com.badlogic.gdx.graphics.Color;

public class Faction {

    public String name;
    public Color color;
    public boolean isPirate = false;

    public Faction(String name, Color color) {
        this.name = name;
        this.color = color;
    }
}
