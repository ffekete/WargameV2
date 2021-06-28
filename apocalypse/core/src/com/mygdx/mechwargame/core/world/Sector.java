package com.mygdx.mechwargame.core.world;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.state.GameState;

import java.util.ArrayList;
import java.util.List;

public class Sector {

    public Texture background;

    public Sector() {
        this.background = GameState.assetManager.get(AssetManagerV2.SECTOR_BOUNDARY);
    }

    public List<Star> stars = new ArrayList<>();
    public SectorOwnerArea sectorOwnerArea;

}
