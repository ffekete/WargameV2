package com.blacksoft.dungeon.building;

import box2dLight.Light;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.build.PlaceGraveyardAction;
import com.blacksoft.dungeon.lighting.LightSourceFactory;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

public class DungeonEntrance implements Building {

    public int level = 1;
    private Light lightSource;

    @Override
    public boolean canUpgradeBy(AbstractAction action) {
        return false;
    }

    @Override
    public void place(int x,
                      int y) {
        this.lightSource = LightSourceFactory.getDungeonEntranceLightSource(x / 16 * 16 + 8,y / 16 * 16 + 8);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Tile getTile() {
        return Tile.GraveYard;
    }

    @Override
    public int getUpgradeLevel() {
        return level;
    }
}
