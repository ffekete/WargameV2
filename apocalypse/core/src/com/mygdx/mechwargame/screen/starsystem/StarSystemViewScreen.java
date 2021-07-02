package com.mygdx.mechwargame.screen.starsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class StarSystemViewScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();
    private Star star;
    private Sector sector;

    public StarSystemViewScreen(Star star, Sector sector) {
        this.star = star;
        this.sector = sector;
    }

    @Override
    public void show() {
        super.show();

        screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        screenContentTable.background(new AnimatedDrawable(AssetManagerV2.MAIN_MENU_BACKGROUND, 1920, 1080, true, 0.15f));

        Map<Integer, String> wealthMapper = new HashMap<>();
        wealthMapper.put(1, "poor");
        wealthMapper.put(2, "moderate");
        wealthMapper.put(3, "rich");
        wealthMapper.put(4, "wealthy");

        int rowsToAdd = 3;

        DecimalFormat formatter = new DecimalFormat("###,###,###,###");

        Table detailTable = new Table();
        detailTable.setSize(800, 500);

        detailTable.add(UIFactoryCommon.getTextLabel("star system"))
                .size(400, 80)
                .padBottom(10)
                .left();

        detailTable.add(UIFactoryCommon.getTextLabel(star.name))
                .size(400, 80)
                .padBottom(10)
                .left()
                .row();

        if (star.capitol) {

            detailTable.add(UIFactoryCommon.getTextLabel("capitol of " + sector.sectorOwnerArea.owner.name))
                    .size(800, 80)
                    .colspan(2)
                    .padBottom(10)
                    .center()
                    .row();
        } else {
            rowsToAdd--;
        }


        detailTable.add(UIFactoryCommon.getTextLabel("planets"))
                .size(400, 80)
                .padBottom(10)
                .center();

        detailTable.add(UIFactoryCommon.getTextLabel(Integer.toString(star.nrOfPlanets)))
                .size(400, 80)
                .padBottom(10)
                .center()
                .row();

        if (star.population > 0) {

            detailTable.add(UIFactoryCommon.getTextLabel("population"))
                    .size(400, 80)
                    .padBottom(10)
                    .center();

            detailTable.add(UIFactoryCommon.getTextLabel(formatter.format(star.population)))
                    .size(400, 80)
                    .center()
                    .row();
        } else {
            rowsToAdd--;
        }

        if (star.population > 0) {
            detailTable.add(UIFactoryCommon.getTextLabel("wealth"))
                    .size(400, 80)
                    .padBottom(10)
                    .center();

            detailTable.add(UIFactoryCommon.getTextLabel(wealthMapper.get(star.wealth)))
                    .size(400, 80)
                    .padBottom(10)
                    .center()
                    .row();
        } else {
            rowsToAdd--;
        }

        detailTable.add()
                .size(800, 500 - 90 - 90 - rowsToAdd * 90)
                .colspan(2);

        screenContentTable.add(detailTable)
                .size(800, 500)
                .center();

        Table imageTable = new Table();
        imageTable.setSize(800, 500);

        Image image = new Image(star.background);
        imageTable.add(image)
                .size(800, 400)
                .top()
                .row();

        imageTable.add()
                .size(800, 100)
                .top();

        screenContentTable
                .add(imageTable)
                .size(800, 500)
                .row();


        ImageButton backButton = UIFactoryCommon.getBackButton();
        backButton.setSize(64, 64);

        screenContentTable.addActor(backButton);

        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                super.touchDown(event, x, y, pointer, button);
                event.stop();
                return true;
            }

            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                event.stop();
                GameState.game.setScreen(GameState.galaxyViewScreen);
            }
        });


        stage.addActor(screenContentTable);

        Gdx.input.setInputProcessor(stage);
    }
}
