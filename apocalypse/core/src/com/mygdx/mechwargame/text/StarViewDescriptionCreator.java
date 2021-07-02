package com.mygdx.mechwargame.text;

import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;

public class StarViewDescriptionCreator {

    private static final String CAPITOL_DESCR =
            "You stand in the giant halls of the capitol of %s, \n" +
            "which has been the home of %s for long now.\n" +
            "The star system provides various facilities \n" +
            "so now would be the best time to have a look.";

    private static final String STAR_NON_CAPITOL_DESCR =
            "You stand in the halls of one of the biggest\n" +
            "cities of %s, which has been the home of\n" +
            "%s for long.\n" +
            "The star system provides various facilities \n" +
            "so now would be the best time to have a look.";

    private static final String PIRATE_DESCR =
            "You stand in the halls of one of the pirate cove of %s, \n" +
            "which was the hiding place of %s for long now.\n" +
            "The star system provides some facilities \n" +
            "so now would be the best time to have a look.";

    public static String generate(Star star, Sector sector) {
        String text;
        if(sector.sectorOwnerArea.owner.isPirate) {
            text = PIRATE_DESCR;
        } else {
            if(star.capitol) {
                text = CAPITOL_DESCR;
            } else {
                text = STAR_NON_CAPITOL_DESCR;
            }
        }

        return String.format(text, star.name, sector.sectorOwnerArea.owner.name);
    }

}
