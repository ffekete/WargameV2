package com.blacksoft.dungeon.building;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.Tile;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.screen.UIFactory;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;
import com.blacksoft.state.UIState;

public class RestingArea implements Building {


    private static TextureRegion textureRegion;

    static {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("tile/RestingArea.png")));
    }

    public int level = 1;

    @Override
    public boolean canUpgradeBy(AbstractAction action) {
        return false;
    }

    @Override
    public void place(int x,
                      int y) {
        int oldProgress = GameState.loopProgress;
        GameState.loopProgress += Config.RESTING_AREA_PROGRESS_VALUE;
        UIFactory.I.updateLabelAmount(oldProgress, GameState.loopProgress, UIState.progressLabel, "%s", null);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Tile getTile() {
        return Tile.RestingArea;
    }

    @Override
    public int getUpgradeLevel() {
        return level;
    }

    @Override
    public boolean getState() {
        return false;
    }

    @Override
    public void toggleState() {

    }

    @Override
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}