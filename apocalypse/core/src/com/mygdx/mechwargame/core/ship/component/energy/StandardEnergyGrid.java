package com.mygdx.mechwargame.core.ship.component.energy;

public class StandardEnergyGrid extends EnergyGrid {

    public StandardEnergyGrid(int level) {
        super(level);
        energyBaseCapacity = 5;
        adjustValues(level);
    }
}
