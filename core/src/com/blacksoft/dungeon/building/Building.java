package com.blacksoft.dungeon.building;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;

public interface Building {

    boolean canUpgradeBy(AbstractAction action);

    void place(int x,
               int y);

    void upgrade();

    void destroy();

    Tile getTile();

    int getUpgradeLevel();

    boolean getState();

    void toggleState();

    TextureRegion getTextureRegion();
}
