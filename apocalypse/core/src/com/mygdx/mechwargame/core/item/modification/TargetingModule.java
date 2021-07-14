package com.mygdx.mechwargame.core.item.modification;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public class TargetingModule extends Modification {

    public TargetingModule() {
        super(AssetManagerV2.TARGETING_MODULE);
        name = "targeting module";
        shortName = "tgt";
        price = 800;
        shortDescription = "+1 weapon accuracy\n" +
                "slots: all missiles";

        description =
                "+1 weapon accuracy.\n" +
                        "slots: all missiles\n\n" +
                        "Simple chip that enhances the targeting capabilities of the missile launcher it is applied to.";
        addToolTip();
    }

    @Override
    public boolean canBeAppliedTo(Socket socket) {
        return socket == Socket.AAMissile
                || socket == Socket.AGMissile
                || socket == Socket.LargeMissile
                || socket == Socket.SmallMissile;
    }

    @Override
    public void apply(Weapon weapon) {
        super.apply(weapon);
        weapon.accuracy++;
    }

    @Override
    public void remove(Weapon weapon) {
        super.remove(weapon);
        weapon.accuracy--;
    }
}
