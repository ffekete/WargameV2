package com.mygdx.mechwargame.core.faction;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.mechwargame.core.item.Item;

import java.util.*;

public class Faction {

    public String name;
    public Color color;
    public boolean isPirate = false;

    public Map<Class<? extends Item>, Float> itemsDemand = new HashMap<>();

    public List<Vector2> areas = new ArrayList<>();

    public Faction(String name,
                   Color color) {
        this.name = name;
        this.color = color;
    }
}
