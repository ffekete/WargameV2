package com.mygdx.mechwargame.core.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.BaseActor;
import com.mygdx.mechwargame.core.starsystem.Facility;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.LayeredAnimatedImage;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.mechwargame.Config.SECTOR_SIZE;

public class Star extends BaseActor {

    public Rectangle bounds;
    public String name;
    public Texture background;

    public int population;
    public int wealth;
    public int nrOfPlanets;
    public boolean twin;
    public boolean capitol;

    public List<Facility> facilities = new ArrayList<>();

    public LayeredAnimatedImage capitolFrameImage = null;

    public Star() {
        super(null);
    }

    public Star(LayeredAnimatedImage layeredAnimatedImage) {
        super(layeredAnimatedImage);
    }

    public void setStarAnimation(String file,
                                 int width,
                                 int height) {
        if (capitol) {
            AnimatedDrawable capitolFrame = new AnimatedDrawable(AssetManagerV2.CAPITOL_FRAME, width, height, true, 0.25f, 30);
            this.capitolFrameImage = new LayeredAnimatedImage(capitolFrame);
        }
        AnimatedDrawable animatedDrawable = new AnimatedDrawable(file, width, height, true, 0.25f, 30);
        this.layeredAnimatedImage = new LayeredAnimatedImage(animatedDrawable);

        if(this.capitolFrameImage != null) {
            this.capitolFrameImage.setPosition(getX() / SECTOR_SIZE * SECTOR_SIZE, getY() / SECTOR_SIZE * SECTOR_SIZE);
        }
        this.layeredAnimatedImage.setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        if (capitolFrameImage != null) {
            capitolFrameImage.setColor(Color.valueOf("FFFFFF11"));
            capitolFrameImage.draw(batch, parentAlpha);
        }
        layeredAnimatedImage.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x,
                            float y) {
        super.setPosition(x, y);
        layeredAnimatedImage.setPosition(x, y);
        if(this.capitolFrameImage != null) {
            this.capitolFrameImage.setPosition(getX() / SECTOR_SIZE * SECTOR_SIZE, getY() / SECTOR_SIZE * SECTOR_SIZE);
        }
    }

    @Override
    public void setBounds(float x,
                          float y,
                          float width,
                          float height) {
        super.setBounds(x, y, width, height);
        this.bounds = new Rectangle(x, y, width, height);
        this.setWidth(width);
        this.setHeight(height);
        this.setX(x);
        this.setY(y);
        layeredAnimatedImage.setPosition(x, y);
        layeredAnimatedImage.setWidth(width);
        layeredAnimatedImage.setHeight(height);

        if(this.capitolFrameImage != null) {
            this.capitolFrameImage.setWidth(SECTOR_SIZE);
            this.capitolFrameImage.setHeight(SECTOR_SIZE);
        }
    }
}
