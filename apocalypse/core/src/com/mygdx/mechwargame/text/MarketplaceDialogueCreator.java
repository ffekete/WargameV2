package com.mygdx.mechwargame.text;

import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;

public class MarketplaceDialogueCreator {

    public static final String DIALOGUE =
            "They have a market. Let's refill our stock\n" +
            "and keep an eye out for everything valuable!";

    public static String generate(Star star, Sector sector) {
        return DIALOGUE;
    }

}
