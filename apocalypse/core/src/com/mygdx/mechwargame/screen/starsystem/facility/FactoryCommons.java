package com.mygdx.mechwargame.screen.starsystem.facility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

class FactoryCommons {

    static Label addTextToDialogueBox(Table dialogueTable,
                                              String buttonText,
                                              String text,
                                              ClickListener clickListener) {

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

}
