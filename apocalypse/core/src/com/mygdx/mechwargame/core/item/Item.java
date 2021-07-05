package com.mygdx.mechwargame.core.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

import static com.mygdx.mechwargame.Config.TOOLTIP_COLOR;
import static com.mygdx.mechwargame.Config.UNIT_SIZE;

public abstract class Item extends Image {

    public String name;
    public String description;
    public int price;
    public int order;

    public Item(String image) {
        super(GameState.assetManager.get(image, Texture.class));
        setSize(UNIT_SIZE, UNIT_SIZE);
    }

    public void addToolTip() {
        TooltipManager tooltipManager = new TooltipManager();
        tooltipManager.resetTime = 0.5f;
        tooltipManager.initialTime = 0.5f;
        tooltipManager.subsequentTime = 0.5f;

        Table table = new Table();
        table.pad(30);
        Sprite sprite = new Sprite(GameState.assetManager.get(AssetManagerV2.TOOLTIP_BG, Texture.class));
        sprite.setColor(TOOLTIP_COLOR);
        SpriteDrawable tooltipBackground = new SpriteDrawable(sprite);
        table.background(tooltipBackground);
        Tooltip<Table> tooltip = new Tooltip<>(table, tooltipManager);

        table.add(UIFactoryCommon.getTextLabel("name", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(150)
                .padRight(30);
        table.add(UIFactoryCommon.getTextLabel(name, UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("value", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(150)
                .padRight(30);

        table.add(UIFactoryCommon.getTextLabel(price + "c", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel(description, UIFactoryCommon.fontSmall))
                .colspan(2)
                .row();

        this.addListener(tooltip);
    }
}
