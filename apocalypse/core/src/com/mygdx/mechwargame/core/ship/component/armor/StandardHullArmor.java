package com.mygdx.mechwargame.core.ship.component.armor;

public class StandardHullArmor extends Armor {

    public StandardHullArmor(int level) {
        super(level, "std hull armor");
        baseArmorValue = 1;
        adjustValues(level);
    }

}
