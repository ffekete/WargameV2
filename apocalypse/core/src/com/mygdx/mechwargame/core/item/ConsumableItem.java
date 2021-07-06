package com.mygdx.mechwargame.core.item;

public abstract class ConsumableItem extends Item {

    public ConsumableItem(String image) {
        super(image);
    }

    public abstract boolean consume();

}
