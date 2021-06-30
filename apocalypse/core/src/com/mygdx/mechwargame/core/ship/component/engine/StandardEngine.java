package com.mygdx.mechwargame.core.ship.component.engine;

public class StandardEngine extends Engine {

    public StandardEngine(int level) {
        super(level, "standard engine");
        fuelBaseCapacity = 200;
        fuelUpgradePerLevel = 5;
        fuelConsumptionPerLevelDivider = 4f;
        baseFuelConsumption = 5f;

        baseEnergyConsumption = 3;
        baseSpeed = 2f;
        speedUpgradePerLevel = 0.1f;

        adjustValues(level);
    }
}
