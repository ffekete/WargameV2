package com.mygdx.mechwargame.core.item.weapon.laser;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.weapon.Mode;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class LargeLaserCannon extends Weapon {

    public LargeLaserCannon() {
        super(AssetManagerV2.LARGE_LASER_CANNON);
        name = "l laser cannon";
        longName = "large laser cannon";
        damage = 2;
        range = 6;
        rateOfFire = 1;
        ammo = 8;
        accuracy = 2;
        price = 1750;

        socket = Socket.Laser;

        modes.add(Mode.SingleShot);

        addToolTip();
    }

}
