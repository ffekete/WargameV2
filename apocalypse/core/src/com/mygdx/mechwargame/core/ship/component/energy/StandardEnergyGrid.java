package com.mygdx.mechwargame.core.ship.component.energy;

public class StandardEnergyGrid extends EnergyGrid {

    public StandardEnergyGrid(int level) {
        super(level, "std energy grid");
        energyBaseCapacity = 5;
        adjustValues(level);
    }
}
