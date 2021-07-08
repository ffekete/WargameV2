package com.mygdx.mechwargame.core.facility;

import com.mygdx.mechwargame.core.item.Item;

import java.util.LinkedList;
import java.util.List;

public class Marketplace extends Facility {

    public Marketplace(int level) {
        super(level, "marketplace");
        itemsToSell = new LinkedList<>();
    }

    public List<Item> itemsToSell;
}
