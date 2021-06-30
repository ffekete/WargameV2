package com.mygdx.mechwargame.core.ship.component.energy;

import com.mygdx.mechwargame.core.ship.component.Component;

public abstract class EnergyGrid extends Component {

    protected int energyBaseCapacity;

    public int energyOutput;
    public int maxEnergyOutput;

    public EnergyGrid(int level, String name) {
        super(level, name);
    }

    @Override
    protected void adjustValues(int level) {
        this.energyOutput = energyBaseCapacity + level;
        this.maxEnergyOutput = energyBaseCapacity + level;
    }
}
