package com.mygdx.mechwargame.screen.starsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.starsystem.Marketplace;
import com.mygdx.mechwargame.core.world.Sector;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.screen.GenericScreenAdapter;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.text.MarketplaceDialogueCreator;
import com.mygdx.mechwargame.text.StarViewDescriptionCreator;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class StarSystemViewScreen extends GenericScreenAdapter {

    private Table screenContentTable = new Table();
    private Star star;
    private Sector sector;

    public StarSystemViewScreen(Star star,
                                Sector sector) {
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
                .center();

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
                .row();

        Table dialogueTable = new Table();
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPaneStyle.background = new TextureRegionDrawable(GameState.assetManager.get(AssetManagerV2.STAR_SYSTEM_SCROLL_PANE_BG, Texture.class));

        ScrollPane scrollPane = new ScrollPane(dialogueTable, scrollPaneStyle);
        scrollPane.setSize(1600, 400);
        scrollPane.setScrollbarsVisible(true);
        scrollPane.setScrollingDisabled(false, false);

        dialogueTable.add(UIFactoryCommon.getTextLabel(StarViewDescriptionCreator.generate(star, sector), UIFactoryCommon.fontSmall))
                .width(1500)
                .colspan(2)
                .padBottom(30)
                .row();

        if (star.facilities.stream().anyMatch(facility -> facility instanceof Marketplace)) {

            ClickListener clickListener = new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event,
                                         float x,
                                         float y,
                                         int pointer,
                                         int button) {
                    // show market screen
                    System.exit(1);
                    return true;
                }
            };

            addTextToDialogueBox(dialogueTable, "1", MarketplaceDialogueCreator.generate(star, sector), clickListener);

        }

        screenContentTable
                .add(scrollPane)
                .colspan(2)
                .size(1600, 400)
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

    private Label addTextToDialogueBox(Table dialogueTable, String buttonText, String text, ClickListener clickListener) {

        ImageTextButton button = UIFactoryCommon.getSmallRoundButton(buttonText);
        button.addListener(clickListener);
        dialogueTable.add(button)
                .size(64, 64)
                .padRight(20);

        Label textLabel = UIFactoryCommon.getTextLabel(text, UIFactoryCommon.fontSmall);

        textLabel.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event,
                              float x,
                              float y,
                              int pointer,
                              Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                textLabel.setColor(Color.GREEN);
            }

            @Override
            public void exit(InputEvent event,
                             float x,
                             float y,
                             int pointer,
                             Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                textLabel.setColor(Color.WHITE);
            }
        });

        textLabel.addListener(clickListener);

        dialogueTable.add(textLabel)
                .width(1500 - 64 - 25)
                .row();

        return textLabel;
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
