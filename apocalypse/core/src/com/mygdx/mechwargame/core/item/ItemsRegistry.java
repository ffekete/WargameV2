package com.mygdx.mechwargame.core.item;

import java.util.Arrays;
import java.util.List;

public class ItemsRegistry {

    // add all items here so demands can be generated properly
    public static List<Class<? extends Item>> items = Arrays.asList(
            HydrogenCell.class
    );

}
