package com.mygdx.mechwargame.core.item.weapon.socket;

public enum Socket {

    AAMissile("air to air missile"),
    AGMissile("air to ground missile"),
    LargeMissile("large missile"),
    SmallMissile("small missile"),
    Gun("gun"),
    MegaWeapon("mega weapon");

    public String name;

    Socket(String name) {
        this.name = name;
    }
}
