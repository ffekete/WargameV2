package com.mygdx.mechwargame.core.mech;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.weapon.LongRangeMissile;
import com.mygdx.mechwargame.ui.AnimatedDrawable;

public class Hellfire extends Mech {

    public Hellfire() {
        idleImagePath = AssetManagerV2.HELLFIRE_IDLE_IMAGE;
        idleDrawable = copyIdleDrawable();
        super.setDrawable(idleDrawable);
        name = "hellfire";
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
