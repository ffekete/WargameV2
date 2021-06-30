package com.mygdx.mechwargame.core.ship.component.engine;

import com.mygdx.mechwargame.core.ship.component.Component;

public abstract class Engine extends Component {

    protected int fuelBaseCapacity = 0;
    protected int fuelUpgradePerLevel = 0;
    protected float baseFuelConsumption = 0f;
    protected float baseEnergyConsumption = 0f;

    public float fuel;
    public float maxFuel;
    public float fuelConsumption;

    public Engine(int level) {
        super(level);
        adjustValues(level);
    }

    public float getEnergyConsumption() {
        return this.baseEnergyConsumption - level * 0.1f;
    }

    public float getSpeed() {
        return 1f - ((float) level * 0.05f);
    }

    @Override
    public void adjustValues(int level) {
        this.fuel = fuelBaseCapacity + level * fuelUpgradePerLevel;
        this.maxFuel = fuelBaseCapacity + level * fuelUpgradePerLevel;
        this.fuelConsumption = baseFuelConsumption - level / 2f;
    }

    public void consumeFuel() {
        fuel -= fuelConsumption;
        if (fuel < 0) {
            fuel = 0;
        }
    }

}
