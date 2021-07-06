package com.mygdx.mechwargame.core.weapon;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.weapon.socket.Socket;

public class LongRangeMissile extends Weapon {

    public LongRangeMissile() {
        super(AssetManagerV2.MISSILE);
        name = "LR missile";
        damage = 1;
        range = 8;
        rateOfFire = 3;
        ammo = 30;
        accuracy = 2;

        socket = Socket.Missile;
    }
}
