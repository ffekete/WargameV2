package com.mygdx.mechwargame.core.item.armor;

import com.mygdx.mechwargame.AssetManagerV2;

public class ArmorPlating extends Armor {

    public ArmorPlating() {
        super(AssetManagerV2.ARMOR_PLATE);
        protection = 1;
        price = 2000;
        name = "armor plate";
        shortDescription = "+1 armor";
        description = "Grants +1 armor value to the unit equipping this.";
        addToolTip();
    }
}
