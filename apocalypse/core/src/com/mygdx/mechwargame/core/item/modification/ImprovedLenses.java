package com.mygdx.mechwargame.core.item.modification;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.item.weapon.Mode;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class ImprovedLenses extends Modification {

    public ImprovedLenses() {
        super(AssetManagerV2.IMPROVED_LENSES);
        name = "improved lenses";
        shortName = "iln";
        price = 1200;
        shortDescription = "+2 damage\n" +
                "slot: laser";
        description =
                "+2 damage.\n" +
                        "slot: laser\n\n" +
                        "A lens that improves focusing of laser beams to deal more damage.";
        addToolTip();
    }

    @Override
    public boolean canBeAppliedTo(Socket socket) {
        return socket == Socket.Laser;
    }

    @Override
    public void apply(Weapon weapon) {
        super.apply(weapon);

        weapon.damage += 2;
    }

    @Override
    public void remove(Weapon weapon) {
        super.remove(weapon);
        weapon.damage -= 2;
    }
}
