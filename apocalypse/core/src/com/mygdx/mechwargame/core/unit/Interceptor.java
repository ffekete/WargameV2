package com.mygdx.mechwargame.core.unit;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.weapon.aamissile.StandardAirToAirMissile;
import com.mygdx.mechwargame.core.item.weapon.agmissile.StandardAirToGroundMissile;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class Interceptor extends BaseUnit {

    public Interceptor() {
        idleImagePath = AssetManagerV2.INTERCEPTOR_IDLE_IMAGE;
        idleDrawable = copyIdleDrawable();
        super.setDrawable(idleDrawable);
        name = "interceptor";
        armor = 0;
        initialArmor = armor;
        maxArmor = 0;
        hp = 1;
        movementPoints = 5;
        initiative = 5;

        primaryWeapon = new StandardAirToGroundMissile();
        primaryWeaponSocket = Socket.AGMissile;

        secondaryWeapon = new StandardAirToAirMissile();
        secondaryWeaponSocket = Socket.AAMissile;

        unitType = UnitType.Aircraft;

        price = 5000;
    }

    @Override
    public String getDescription() {
        return "Fast, but fragile.\nGood at spotting.";
    }
}
