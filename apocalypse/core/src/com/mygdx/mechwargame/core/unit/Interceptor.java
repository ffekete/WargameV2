package com.mygdx.mechwargame.core.unit;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.weapon.AirToAirMissile;
import com.mygdx.mechwargame.core.weapon.AirToGroundMissile;
import com.mygdx.mechwargame.core.weapon.socket.Socket;

public class Interceptor extends BaseUnit {

    public Interceptor() {
        idleImagePath = AssetManagerV2.INTERCEPTOR_IDLE_IMAGE;
        idleDrawable = copyIdleDrawable();
        super.setDrawable(idleDrawable);
        name = "interceptor";
        armor = 0;
        maxArmor = 0;
        hp = 1;
        movementPoints = 5;
        initiative = 5;

        primaryWeapon = new AirToGroundMissile();
        primaryWeaponSocket = Socket.AGMissile;

        secondaryWeapon = new AirToAirMissile();
        secondaryWeaponSocket = Socket.AAMissile;

        unitType = UnitType.Aircraft;
    }

    @Override
    public String getDescription() {
        return "Fast, but fragile.\nGood at spotting.";
    }
}
