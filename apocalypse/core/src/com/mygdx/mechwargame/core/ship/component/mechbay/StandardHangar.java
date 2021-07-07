package com.mygdx.mechwargame.core.ship.component.mechbay;

public class StandardHangar extends Hangar {

    public StandardHangar(int level) {
        super(level, "std mech bay");
        baseMaxCapacity = 3;
        adjustValues(level);
    }
}
