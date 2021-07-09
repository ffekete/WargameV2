package com.mygdx.mechwargame.core.item.weapon.socket;

public enum Socket {

    AAMissile("m-aa"),
    AGMissile("m-ag"),
    LargeMissile("m-l"),
    SmallMissile("m-s"),
    Gun("gun"),
    MegaWeapon("cannon");

    public String name;

    Socket(String name) {
        this.name = name;
    }
}
