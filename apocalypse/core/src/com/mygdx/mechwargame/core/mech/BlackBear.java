package com.mygdx.mechwargame.core.mech;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.weapon.LaserCannon;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public class BlackBear extends Mech {

    public BlackBear() {
        AnimatedDrawable baseLayer = new AnimatedDrawable(AssetManagerV2.BLACK_BEAR_IMAGE, 32, 32, true, 0.5f);
        mechImage = new LayeredAnimatedImage(baseLayer);
        armor = 3;
        movementPoints = 1;
        initiative = 1;
        primaryWeapon = new LaserCannon();
        hp = 3;
    }

    @Override
    public String getDescription() {
        return "Slow but armoured.\nGood in melee.";
    }
}
