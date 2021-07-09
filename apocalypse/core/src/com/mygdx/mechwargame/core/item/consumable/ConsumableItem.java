package com.mygdx.mechwargame.core.item.consumable;

import com.mygdx.mechwargame.core.item.Item;

public abstract class ConsumableItem extends Item {

    public ConsumableItem(String image) {
        super(image);
    }

    public abstract boolean consume();

}
