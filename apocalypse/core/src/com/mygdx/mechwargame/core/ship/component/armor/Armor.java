package com.mygdx.mechwargame.core.ship.component.armor;

import com.mygdx.mechwargame.core.ship.component.Component;

public abstract class Armor extends Component {

    protected int baseArmorValue;

    public int armor;
    public int maxArmor;

    public Armor(int level, String name) {
        super(level, name);
    }

    @Override
    protected void adjustValues(int level) {
        armor = baseArmorValue + level;
        maxArmor = baseArmorValue + level;
    }

}
