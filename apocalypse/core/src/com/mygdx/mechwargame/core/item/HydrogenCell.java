package com.mygdx.mechwargame.core.item;

import com.mygdx.mechwargame.AssetManagerV2;

import static com.mygdx.mechwargame.Config.FUEL_ORDER;

public class HydrogenCell extends Item {

    int fuelCapacity = 200;

    public HydrogenCell() {
        super(AssetManagerV2.HYDROGEN_CELL);
        order = FUEL_ORDER;
        price = 50;
        name = "hydrogen cell";
        description = String.format("Simple fuel cell used to power ships.\n" +
                "Fuel capacity: %s", fuelCapacity);

        addToolTip();
    }
}
