package com.mygdx.mechwargame.core.ship.component.engine;

import com.mygdx.mechwargame.core.ship.component.Component;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;

public abstract class Engine extends Component {

    protected float fuelBaseCapacity = 0;
    protected float fuelUpgradePerLevel = 0;
    protected float baseFuelConsumption = 0f;
    protected float baseEnergyConsumption = 0f;
    protected float baseSpeed;
    protected float speedUpgradePerLevel;
    protected float fuelConsumptionPerLevelDivider;

    public float fuel;
    public float maxFuel;
    public float fuelConsumption;

    public Engine(int level,
                  String name) {
        super(level, name);
        adjustValues(level);
    }

    public float getEnergyConsumption() {
        return this.baseEnergyConsumption - level * 0.1f;
    }

    public float getSpeed() {
        return baseSpeed - ((float) level * speedUpgradePerLevel);
    }

    @Override
    public void adjustValues(int level) {
        this.fuel = fuelBaseCapacity + level * fuelUpgradePerLevel;
        this.maxFuel = fuelBaseCapacity + level * fuelUpgradePerLevel;
        this.fuelConsumption = baseFuelConsumption - level / fuelConsumptionPerLevelDivider;
    }

    public void consumeFuel() {
        fuel -= fuelConsumption;
        if (fuel < 0) {
            fuel = 0;



        }
    }

}
