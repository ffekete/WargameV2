package com.mygdx.mechwargame.core.item.modification;

import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.core.unit.BaseUnit;
import com.mygdx.mechwargame.core.item.weapon.Weapon;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;

public abstract class Modification extends Item {

    public Modification(String image) {
        super(image);
        order = Config.MODIFICATION_ORDER;
    }

    public abstract boolean canBeAppliedTo(Socket socket);

    void apply(BaseUnit target) {

    }

    void apply(Weapon weapon) {
        weapon.modification = this;
    }

    void remove(Weapon weapon) {
        weapon.modification = null;
    }

}
