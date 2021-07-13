package com.mygdx.mechwargame.screen.starsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.facility.Factory;
import com.mygdx.mechwargame.core.unit.BaseUnit;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.screen.view.star.FactoryView;

import java.util.Collections;
import java.util.Comparator;

public class FactoryViewScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();
    private Star star;
    private Sector sector;
    private Factory factory;

    public FactoryViewScreen(Star star,
                             Sector sector,
                             Factory factory) {
        this.star = star;
        this.sector = sector;
        this.factory = factory;

        Collections.sort(factory.itemsToSell, new Comparator<BaseUnit>() {
            @Override
            public int compare(BaseUnit o1,
                               BaseUnit o2) {
                return String.CASE_INSENSITIVE_ORDER.compare(o1.unitType.name, o2.unitType.name);
            }
        });
    }

    public void show() {
        super.show();
        stage.getViewport().apply(true);

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        screenContentTable.add(new FactoryView(stage, factory.itemsToSell))
        .size(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        stage.addActor(screenContentTable);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
