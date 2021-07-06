package com.mygdx.mechwargame.core.ship.component.mechbay;

public class StandardMechBay extends MechBay {

    public StandardMechBay(int level) {
        super(level, "std mech bay");
        baseMaxCapacity = 3;
        adjustValues(level);
    }
}
