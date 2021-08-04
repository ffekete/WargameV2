package com.mygdx.mechwargame.screen.starsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.screen.starsystem.facility.BlackMarketClickHandler;
import com.mygdx.mechwargame.screen.starsystem.facility.FactoryClickHandler;
import com.mygdx.mechwargame.screen.starsystem.facility.MarketPlaceClickHandler;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static com.mygdx.mechwargame.Config.SCREEN_TRANSITION_DELAY;

public class StarSystemViewScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();
    private Star star;
    private Sector sector;

    private boolean reloadNeeded = true;

    public StarSystemViewScreen(Star star,
                                Sector sector) {
        this.star = star;
        this.sector = sector;
    }

    @Override
    public void show() {
        super.show();

        GameState.previousScreen = this;

        screenContentTable.setColor(1, 1, 1, 1);

        if (reloadNeeded) {

            screenContentTable.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

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
                    .left();

            detailTable.add(UIFactoryCommon.getTextLabel(star.name))
                    .size(400, 80)
                    .left()
                    .row();

            if (star.capitol) {

                detailTable.add(UIFactoryCommon.getTextLabel("capitol of " + sector.sectorOwnerArea.owner.name))
                        .size(800, 80)
                        .colspan(2)
                        .center()
                        .row();
            } else {
                rowsToAdd--;
            }


            detailTable.add(UIFactoryCommon.getTextLabel("planets"))
                    .size(400, 80)

                    .center();

            detailTable.add(UIFactoryCommon.getTextLabel(Integer.toString(star.nrOfPlanets)))
                    .size(400, 80)

                    .center()
                    .row();

            if (star.population > 0) {

                detailTable.add(UIFactoryCommon.getTextLabel("population"))
                        .size(400, 80)

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

                        .center();

                detailTable.add(UIFactoryCommon.getTextLabel(wealthMapper.get(star.wealth)))
                        .size(400, 80)

                        .center()
                        .row();
            } else {
                rowsToAdd--;
            }

            detailTable.add()
                    .size(800, 400 - 90 - 90 - rowsToAdd * 90)
                    .colspan(2);

            screenContentTable.add(detailTable)
                    .size(800, 500)
                    .right();

            Table imageTable = new Table();
            imageTable.setSize(800, 500);

            Image image = new Image(star.background);
            imageTable.add(image)
                    .size(800, 400)
                    .top()
                    .row();

            screenContentTable
                    .add(imageTable)
                    .size(800, 500)
                    .left()
                    .row();

            screenContentTable.add(star.cityView)
                    .size(1600, 448)
                    .expand()
                    .colspan(2)
                    .center()
                    .row();


            BlackMarketClickHandler.addClickListener(screenContentTable, sector, star, stage);
            MarketPlaceClickHandler.addClickListener(screenContentTable, sector, star, stage);
            FactoryClickHandler.addClickListener(screenContentTable, sector, star, stage);

            Table buttonRow = new Table();
            ImageTextButton backButton = UIFactoryCommon.getSmallRoundButton("back", UIFactoryCommon.fontSmall);
            buttonRow.add().expand();
            buttonRow.add(backButton)
                    .size(350, 70);

            screenContentTable.add(buttonRow)
                    .colspan(2)
                    .size(1600, 70);


            backButton.addListener(new ClickListener() {

                @Override
                public void touchUp(InputEvent event,
                                    float x,
                                    float y,
                                    int pointer,
                                    int button) {
                    super.touchUp(event, x, y, pointer, button);
                    event.stop();
                    SequenceAction sequenceAction = new SequenceAction();
                    AlphaAction alphaAction = new AlphaAction();
                    sequenceAction.addAction(alphaAction);
                    alphaAction.setAlpha(0);
                    alphaAction.setDuration(SCREEN_TRANSITION_DELAY);
                    alphaAction.setActor(screenContentTable);

                    sequenceAction.addAction(new SetScreenAction(GameState.galaxyViewScreen));

                    stage.addAction(sequenceAction);
                }
            });

            stage.addActor(screenContentTable);

            reloadNeeded = false;
        }

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.getViewport().apply(true);

        Gdx.graphics.getGL20().glClearColor(0, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

}
