package com.mygdx.mechwargame.core.mech;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.weapon.LaserCannon;
import com.mygdx.mechwargame.ui.AnimatedDrawable;

public class BlackBear extends Mech {

    public BlackBear() {
        idleImagePath = AssetManagerV2.BLACK_BEAR_IDLE_IMAGE;
        idleDrawable = copyIdleDrawable();
        super.setDrawable(idleDrawable);
        name = "black bear";

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
