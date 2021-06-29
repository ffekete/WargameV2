package com.mygdx.mechwargame.ui.view.galaxy;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.core.world.Star;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.UIFactoryCommon;
import com.mygdx.mechwargame.util.ScreenUtils;

import static com.mygdx.mechwargame.util.ScreenUtils.repositionToScreenIfNotInFrustum;

public class StarLocalMenu extends Table {

    public ImageTextButton viewInfoButton;
    public ImageTextButton visitButton;
    public ImageTextButton leaveButton;

    public Star star;

    public StarLocalMenu(Star star,
                         Stage stage) {
        this.star = star;

        this.background(new AnimatedDrawable(AssetManagerV2.STAR_LOCAL_MENU, 450, 350, true, 0.1f));
        this.setSize(450, 350);
        this.setPosition(star.getX(), star.getY());

        Camera camera = stage.getCamera();

        repositionToScreenIfNotInFrustum(camera, this);

        this.setVisible(false);

        stage.addActor(this);

        Table starNameTable = new Table();
        starNameTable.add(UIFactoryCommon.getTextLabel(star.name));

        Table mainTable = new Table();

        this.add(mainTable);

        mainTable.add(starNameTable)
                .row();

        viewInfoButton = UIFactoryCommon.getMenuButton("view");
        mainTable.add(viewInfoButton)
                .size(400, 80)
                .padBottom(10)
                .row();

        visitButton = UIFactoryCommon.getMenuButton("visit");
        mainTable.add(visitButton)
                .size(400, 80)
                .padBottom(10)
                .row();

        leaveButton = UIFactoryCommon.getMenuButton("leave");
        mainTable.add(leaveButton)
                .size(400, 80)
                .padBottom(10)
                .row();

        viewInfoButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                setVisible(false);
                stage.getActors().removeValue(mainTable, true);
                event.stop();
                return true;
            }
        });

        visitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                setVisible(false);
                stage.getActors().removeValue(mainTable, true);
                event.stop();
                return true;
            }
        });

        leaveButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                setVisible(false);
                stage.getActors().removeValue(mainTable, true);
                event.stop();
                return true;
            }
        });
    }
}
