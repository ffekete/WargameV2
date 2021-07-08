package com.mygdx.mechwargame.core.facility;

import com.mygdx.mechwargame.core.item.Item;

import java.util.ArrayList;
import java.util.List;

public class BlackMarket extends Facility {

    public BlackMarket(int level) {
        super(level, "black market");
    }

    public List<Item> itemsToSell = new ArrayList<>();
}
