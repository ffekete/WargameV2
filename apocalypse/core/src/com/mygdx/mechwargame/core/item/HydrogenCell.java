package com.mygdx.mechwargame.core.item;

import com.mygdx.mechwargame.AssetManagerV2;

public class HydrogenCell extends Item {

    public HydrogenCell() {
        super(AssetManagerV2.HYDROGEN_CELL);
        price = 5;
        name = "hydrogen cell";
    }
}
