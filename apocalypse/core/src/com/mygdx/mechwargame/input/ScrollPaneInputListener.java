package com.mygdx.mechwargame.input;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;

public class ScrollPaneInputListener {

    public static InputListener newInstance(Stage stage, ScrollPane scrollPane) {

        return new InputListener() {
            @Override
            public void enter(InputEvent event,
                              float x,
                              float y,
                              int pointer,
                              Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                stage.setScrollFocus(scrollPane);
            }

            @Override
            public void exit(InputEvent event,
                             float x,
                             float y,
                             int pointer,
                             Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                stage.setScrollFocus(null);
            }
        };
    }

}
