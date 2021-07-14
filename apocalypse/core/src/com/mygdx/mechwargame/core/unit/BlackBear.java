package com.mygdx.mechwargame.core.unit;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.weapon.laser.LaserCannon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class BlackBear extends BaseUnit {

    public BlackBear() {
        idleImagePath = AssetManagerV2.BLACK_BEAR_IDLE_IMAGE;
        idleDrawable = copyIdleDrawable();
        super.setDrawable(idleDrawable);
        name = "black bear";

        armor = 3;
        initialArmor = armor;
        maxArmor = 4;
        movementPoints = 1;
        initiative = 1;
        primaryWeapon = new LaserCannon();
        primaryWeaponSocket = Socket.Laser;

        hp = 2;

        unitType = UnitType.PowerArmor;

        price = 5000;
    }

    @Override
    public String getDescription() {
        return "Slow but armoured.\nGood in melee.";
    }
}
