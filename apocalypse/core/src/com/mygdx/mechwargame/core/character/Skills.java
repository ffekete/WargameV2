package com.mygdx.mechwargame.core.character;

public enum Skills {

    Lasers("lasers"),
    Missiles("missiles"),
    Guns("guns"),
    Melee("melee"),
    Piloting("piloting"),
    Evasion("evasion");

    public String displayName;

    Skills(String displayName) {
        this.displayName = displayName;
    }
}
