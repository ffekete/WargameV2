package com.mygdx.mechwargame.core.mech;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.weapon.AirToAirMissile;
import com.mygdx.mechwargame.core.weapon.AirToSurfaceMissile;

public class Interceptor extends Mech {

    public Interceptor() {
        idleImagePath = AssetManagerV2.INTERCEPTOR_IDLE_IMAGE;
        idleDrawable = copyIdleDrawable();
        super.setDrawable(idleDrawable);
        name = "interceptor";
        armor = 0;
        hp = 1;
        movementPoints = 5;
        initiative = 5;
        primaryWeapon = new AirToSurfaceMissile();
        secondaryWeapon = new AirToAirMissile();
    }

    @Override
    public String getDescription() {
        return "Fast, but fragile.\nGood at spotting.";
    }
}
