package com.mygdx.mechwargame.core.ship.component.mechbay;

import com.mygdx.mechwargame.core.ship.component.Component;
import com.mygdx.mechwargame.core.unit.BaseUnit;

import java.util.LinkedList;
import java.util.List;

public class Hangar extends Component {

    public int capacity;
    public int maxCapacity;

    protected int baseMaxCapacity;

    protected List<BaseUnit> baseUnits = new LinkedList<>();

    public Hangar(int level,
                  String name) {
        super(level, name);
    }

    public List<BaseUnit> getMechs() {
        return baseUnits;
    }

    @Override
    protected void adjustValues(int level) {
        maxCapacity = baseMaxCapacity + level;
        capacity = 0;
    }

    public boolean addUnit(BaseUnit baseUnit) {
        if (capacity + baseUnit.unitType.size >= maxCapacity) {
            return false;
        }

        baseUnits.add(baseUnit);
        capacity += baseUnit.unitType.size;
        return true;
    }

    public boolean removeUnit(BaseUnit baseUnit) {
        if (baseUnits.remove(baseUnit)) {
            capacity -= baseUnit.unitType.size;
            return true;
        }
        return false;
    }
}
