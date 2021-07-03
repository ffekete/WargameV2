package com.mygdx.mechwargame.core.starsystem;

import com.mygdx.mechwargame.core.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Marketplace extends Facility {

    public Marketplace(int level) {
        super(level, "marketplace");
        itemsToSell = new ArrayList<>();
    }

    public List<Item> itemsToSell;
}
