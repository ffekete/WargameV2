package com.mygdx.mechwargame.core.ship.component.cargo;

public class StandardCargoBay extends CargoBay {


    public StandardCargoBay(int level) {
        super(level, "standard c bay");

        adjustValues(level);
    }

}
