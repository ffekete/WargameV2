package com.mygdx.mechwargame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.Config;
import com.mygdx.mechwargame.screen.action.FlashingAction;
import com.mygdx.mechwargame.screen.action.SetScreenAction;
import com.mygdx.mechwargame.ui.AnimatedDrawable;
import com.mygdx.mechwargame.ui.UIFactoryCommon;

public class GameMainMenuScreen extends GenericScreenAdapter {

    @Override
    public void show() {
        super.show();

        Gdx.input.setInputProcessor(stage);
    }
}
