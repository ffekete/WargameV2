package com.mygdx.mechwargame.core.weapon;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.weapon.socket.Socket;

public class AirToSurfaceMissile extends Weapon {

    public AirToSurfaceMissile() {
        super(AssetManagerV2.AG_MISSILE);
        name = "AS missile";
        damage = 1;
        range = 4;
        rateOfFire = 4;
        ammo = 40;
        accuracy = 2;

        socket = Socket.ASMissile;
    }

}
