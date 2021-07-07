package com.mygdx.mechwargame.core.weapon;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.weapon.socket.Socket;

public class LargeLaserCannon extends Weapon {

    public LargeLaserCannon() {
        super(AssetManagerV2.LASER_CANNON);
        name = "l laser cannon";
        longName = "large laser cannon";
        damage = 2;
        range = 6;
        rateOfFire = 1;
        ammo = 8;
        accuracy = 2;
        price = 1750;

        socket = Socket.Gun;

        addToolTip();
    }

}
