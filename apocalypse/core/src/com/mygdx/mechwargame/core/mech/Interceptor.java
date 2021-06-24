package com.mygdx.mechwargame.core.mech;

import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public class Interceptor extends Mech {

    public Interceptor() {
        AnimatedDrawable baseLayer = new AnimatedDrawable(AssetManagerV2.INTERCEPTOR_IMAGE, 32, 32, true, 0.25f);
        mechImage = new LayeredAnimatedImage(baseLayer);
        armor = 0;
        movementPoints = 5;
        initiative = 5;
    }

    @Override
    public String getDescription() {
        return "Fast, but fragile.\nGood at spotting.";
    }
}
