package com.mygdx.mechwargame.core.item.modification;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class SmallPowerBank extends Modification {

    public SmallPowerBank() {
        super(AssetManagerV2.SMALL_POWERBANK);
        name = "small powerbank";
        shortName = "spb";
        price = 800;
        shortDescription = "+2 ammo\n" +
                "slot: laser";
        description =
                "+2 ammo.\n" +
                        "slot: laser\n\n" +
                        "An extension to the battery of a\n" +
                        "laser weapon to enhance ammo capacity.";
        addToolTip();
    }

    @Override
    public boolean canBeAppliedTo(Socket socket) {
        return socket == Socket.Laser;
    }

    @Override
    public void apply(Weapon weapon) {
        super.apply(weapon);
        weapon.ammo += 2;
    }

    @Override
    public void remove(Weapon weapon) {
        super.remove(weapon);
        weapon.ammo -= 2;
    }
}
