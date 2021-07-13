package com.mygdx.mechwargame.core.facility;

import com.mygdx.mechwargame.core.unit.BaseUnit;

import java.util.LinkedList;
import java.util.List;

public class Factory extends Facility {

    public List<BaseUnit> itemsToSell;

    public Factory(int level) {
        super(level, "factory");
        itemsToSell = new LinkedList<>();
    }
}
