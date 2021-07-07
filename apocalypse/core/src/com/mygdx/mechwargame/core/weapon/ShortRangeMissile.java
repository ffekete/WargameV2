package com.mygdx.mechwargame.core.weapon;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.weapon.socket.Socket;

public class ShortRangeMissile extends Weapon {

    public ShortRangeMissile() {
        super(AssetManagerV2.MISSILE);
        name = "SR missile";
        longName = "short range missile";
        damage = 1;
        range = 4;
        rateOfFire = 3;
        ammo = 30;
        accuracy = 3;
        price = 900;

        socket = Socket.SmallMissile;

        addToolTip();
    }
}
