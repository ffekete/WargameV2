package com.mygdx.mechwargame.core.unit;

public enum UnitType {

    PowerArmor(1, "power armor"),
    Vehicle(1, "vehicle"),
    Mech(2, "mech"),
    Aircraft(1, "aircraft");

    public int size;
    public String name;

    UnitType(int size, String name) {
        this.size = size;
        this.name = name;
    }
}
