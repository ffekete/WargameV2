package com.mygdx.mechwargame.core.unit;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.weapon.missile.LongRangeMissile;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class Hellfire extends BaseUnit {

    public Hellfire() {
        idleImagePath = AssetManagerV2.HELLFIRE_IDLE_IMAGE;
        idleDrawable = copyIdleDrawable();
        super.setDrawable(idleDrawable);
        name = "hellfire";
        armor = 2;
        initialArmor = armor;
        maxArmor = 3;
        hp = 2;
        movementPoints = 1;
        initiative = 1;
        primaryWeapon = new LongRangeMissile();
        primaryWeaponSocket = Socket.LargeMissile;
        unitType = UnitType.Vehicle;
        price = 5000;
    }

    @Override
    public String getDescription() {
        return "Slow, long range.\nHas some armour.";
    }
}
