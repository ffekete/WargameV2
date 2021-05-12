package com.blacksoft.dungeon.actions.build;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blacksoft.dungeon.actions.AbstractAction;
import com.blacksoft.dungeon.actions.ActionLevel;
import com.blacksoft.dungeon.building.RestingArea;
import com.blacksoft.state.Config;
import com.blacksoft.state.GameState;

import static com.blacksoft.dungeon.actions.ActionLevel.Basic;
import static com.blacksoft.state.Config.RESTING_AREA_PRIORITY;

public class PlaceRestingAreaAction extends AbstractAction {

    public static final PlaceRestingAreaAction I = new PlaceRestingAreaAction();

    private static final Texture texture;
    private static final TextureRegion drawable;

    static {
        texture = new Texture(Gdx.files.internal("tile/RestingArea.png"));
        drawable = new TextureRegion(texture);
        drawable.setRegion(48, 48, 16, 16);
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        batch.draw(drawable, getX(), getY());
    }

    @Override
    public int getPriority() {
        return RESTING_AREA_PRIORITY;
    }

    @Override
    public ActionLevel getActionLevel() {
        return Basic;
    }

    @Override
    public TextureRegion getTexture() {
        return drawable;
    }

    @Override
    public String getTitle() {
        return "Resting area";
    }

    @Override
    public String getDescription() {
        return "Creatures can rest here.";
    }

    @Override
    public void execute() {
        GameState.currentBuilding = new RestingArea();
    }

    @Override
    public int getProgressAmount() {
        return Config.RESTING_AREA_PROGRESS_VALUE;
    }

    @Override
    public Class<?> getActionResultClass() {
        return RestingArea.class;
    }
}