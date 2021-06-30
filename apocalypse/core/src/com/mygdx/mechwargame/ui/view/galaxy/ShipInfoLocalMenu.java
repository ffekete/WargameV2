package com.mygdx.mechwargame.ui.view.galaxy;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.steer.behaviors.Alignment;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.ship.BaseShip;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

public class ShipInfoLocalMenu extends Table {

    public ImageTextButton engineInfoButton;

    public ShipInfoLocalMenu(Stage stage) {

        BaseShip ship = GameData.starShip;
        Camera camera = stage.getCamera();

        this.background(new AnimatedDrawable(AssetManagerV2.CHARACTER_ATTRIBUTES_FRAME, 1500, 600, true, 0.1f));
        this.setSize(1500, 600);
        this.setPosition(camera.position.x - 750f, camera.position.y - 300);

        this.setVisible(false);

        stage.addActor(this);

        Table shipNameTable = new Table();
        ImageButton backButton = UIFactoryCommon.getBackButton();

        shipNameTable.add(backButton)
                .size(80);

        Label nameLabel = UIFactoryCommon.getTextLabel(ship.modelName, UIFactoryCommon.fontSmall);
        nameLabel.setAlignment(Align.center);
        shipNameTable.add(nameLabel)
                .size(1420, 80)
                .center();

        shipNameTable.setSize(1500, 80);

        Table mainTable = new Table();
        mainTable.setSize(1500, 500);

        this.add(mainTable);

        mainTable.add(shipNameTable)
                .width(1500)
                .center()
                .colspan(2)
                .row();

        Table buttonsTable = new Table();
        Table descriptionTable = new Table();

        buttonsTable.setSize(450, 500);
        descriptionTable.setSize(1000, 500);
        descriptionTable.background(new AnimatedDrawable(AssetManagerV2.SHIP_INFO_DESCRIPTION_PANEL, 1000, 500, true, 0.1f));

        mainTable.add(buttonsTable)
                .padRight(50)
                .center()
                .size(450, 500);
        mainTable.add(descriptionTable)
                .center()
                .size(1000, 500);

        engineInfoButton = UIFactoryCommon.getMenuButton("status");
        buttonsTable.add(engineInfoButton)
                .size(400, 80)
                .center()
                .padBottom(10)
                .padLeft(20)
                .row();

        engineInfoButton = UIFactoryCommon.getMenuButton("components");
        buttonsTable.add(engineInfoButton)
                .size(400, 80)
                .center()
                .padBottom(10)
                .padLeft(20)
                .row();

        engineInfoButton = UIFactoryCommon.getMenuButton("cargo");
        buttonsTable.add(engineInfoButton)
                .size(400, 80)
                .center()
                .padBottom(10)
                .padLeft(20)
                .row();

        backButton.addListener(new ClickListener(Input.Buttons.LEFT) {

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
                setVisible(false);
                stage.getActors().removeValue(mainTable, true);
            }
        });

    }
}
