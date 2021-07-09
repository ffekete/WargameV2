package com.mygdx.mechwargame.core.item.weapon.missile;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.weapon.Mode;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

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

        modes.add(Mode.SingleShot);

        addToolTip();
    }
}
