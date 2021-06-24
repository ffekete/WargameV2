package com.mygdx.mechwargame.core.mech;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.weapon.LongRangeMissile;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public class Hellfire extends Mech {

    public Hellfire() {
        AnimatedDrawable baseLayer = new AnimatedDrawable(AssetManagerV2.HELLFIRE_IMAGE, 32, 32, true, 0.25f);
        mechImage = new LayeredAnimatedImage(baseLayer);
        armor = 2;
        hp = 2;
        movementPoints = 1;
        initiative = 1;
        primaryWeapon = new LongRangeMissile();
    }

    @Override
    public String getDescription() {
        return "Slow, long range.\nHas some armour.";
    }
}
