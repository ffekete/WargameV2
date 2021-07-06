package com.mygdx.mechwargame.core.ship.component.mechbay;

import com.mygdx.mechwargame.core.mech.Mech;
import com.mygdx.mechwargame.core.ship.component.Component;

import java.util.ArrayList;
import java.util.List;

public class MechBay extends Component {

    public int capacity;
    public int maxCapacity;

    protected int baseMaxCapacity;

    protected List<Mech> mechs = new ArrayList<>();

    public MechBay(int level,
                   String name) {
        super(level, name);
        adjustValues(level);
    }

    @Override
    protected void adjustValues(int level) {
        maxCapacity = baseMaxCapacity + level;
        capacity = 0;
    }

    public boolean addMech(Mech mech) {
        if (capacity >= maxCapacity) {
            return false;
        }

        mechs.add(mech);
        capacity++;
        return true;
    }

    public boolean removeMech(Mech mech) {
        if (mechs.remove(mech)) {
            capacity--;
            return true;
        }
        return false;
    }
}
