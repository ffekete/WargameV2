package com.mygdx.mechwargame.screen.galaxy.inputevent.cargo;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.mechwargame.core.item.Item;

import java.util.List;

import static com.mygdx.mechwargame.screen.galaxy.inputevent.cargo.CargoRightClickEventDefinition.rightClickDown;
import static com.mygdx.mechwargame.screen.galaxy.inputevent.cargo.CargoRightClickEventDefinition.rightClickUp;

public class CargoLeftClickListener {

    public static ClickListener newInstance() {

        return new ClickListener(Input.Buttons.LEFT) {

            @Override
            public boolean touchDown(InputEvent event,
                                     float x,
                                     float y,
                                     int pointer,
                                     int button) {
                boolean result = super.touchDown(event, x, y, pointer, button);

                if (button == Input.Buttons.LEFT) {

                }

                return result;
            }

            @Override
            public void touchUp(InputEvent event,
                                float x,
                                float y,
                                int pointer,
                                int button) {
                super.touchUp(event, x, y, pointer, button);
                event.stop();

                if (button == Input.Buttons.LEFT) {

                }
            }
        };
    }

}
