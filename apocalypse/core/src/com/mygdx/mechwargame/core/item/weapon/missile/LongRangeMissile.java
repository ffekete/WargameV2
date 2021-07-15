package com.mygdx.mechwargame.core.item.weapon.missile;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.weapon.Mode;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class LongRangeMissile extends Weapon {

    public LongRangeMissile() {
        super(AssetManagerV2.LR_MISSILE);
        name = "LR missile";
        longName = "long range missile";
        damage = 1;
        range = 8;
        rateOfFire = 3;
        ammo = 30;
        accuracy = 2;
        price = 1200;

        socket = Socket.LargeMissile;

        modes.add(Mode.SingleShot);

        addToolTip();
    }
}
