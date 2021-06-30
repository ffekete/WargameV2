package com.mygdx.mechwargame.core.ship.component.engine;

public class StandardEngine extends Engine {

    public StandardEngine(int level) {
        super(level);
        fuelBaseCapacity = 200;
        fuelUpgradePerLevel = 50;
        baseFuelConsumption = 5f;
        baseEnergyConsumption = 3;

        adjustValues(level);
    }
}
