package com.mygdx.mechwargame.core.character;

public enum Attributes {

    Endurance("endurance"),
    Perception("perception"),
    HandEyeCoordination("Hand-eye coordination"),
    Reflexes("reflexes");

    public String displayName;

    Attributes(String displayName) {
        this.displayName = displayName;
    }
}
