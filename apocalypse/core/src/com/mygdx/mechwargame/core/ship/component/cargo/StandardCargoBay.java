package com.mygdx.mechwargame.core.ship.component.cargo;

public class StandardCargoBay extends CargoBay {


    public StandardCargoBay(int level) {
        super(level, "std cargo bay");
        baseCapacity = 16;
        adjustValues(level);
    }

}
