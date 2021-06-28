package com.mygdx.mechwargame.core.ship;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.BaseActor;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

public class StarShip extends BaseActor {

    public StarShip() {
        super(new LayeredAnimatedImage(new AnimatedDrawable(AssetManagerV2.SHIP, 32, 32, 0.15f)));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
