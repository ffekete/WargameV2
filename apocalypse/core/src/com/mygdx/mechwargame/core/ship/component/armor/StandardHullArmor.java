package com.mygdx.mechwargame.core.ship.component.armor;

public class StandardHullArmor extends Armor {

    public StandardHullArmor(int level) {
        super(level, "standard armor");
        baseArmorValue = 1;
        adjustValues(level);
    }

}
