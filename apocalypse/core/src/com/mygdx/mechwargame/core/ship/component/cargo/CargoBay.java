package com.mygdx.mechwargame.core.ship.component.cargo;

import com.mygdx.mechwargame.core.ship.component.Component;

public abstract class CargoBay extends Component {

    protected int baseCapacity;
    public int capacity;
    public int maxCapacity;

    public CargoBay(int level,
                    String name) {
        super(level, name);
    }

    @Override
    protected void adjustValues(int level) {
        capacity = level + baseCapacity;
        maxCapacity = level + baseCapacity;
    }
}
