package com.mygdx.mechwargame.core.weapon.socket;

public enum Socket {

    AAMissile("air to air missile"),
    AGMissile("air to ground missile"),
    LargeMissile("large missile"),
    SmallMissile("small missile"),
    Gun("gun");

    public String name;

    Socket(String name) {
        this.name = name;
    }
}
