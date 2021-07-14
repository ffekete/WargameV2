package com.mygdx.mechwargame.core.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.input.ToolTipManager;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

import static com.mygdx.mechwargame.Config.TOOLTIP_COLOR;
import static com.mygdx.mechwargame.Config.TOOLTIP_MAX_WIDTH;
import static com.mygdx.mechwargame.Config.UNIT_SIZE;

public abstract class Item extends Image {

    public String name;
    public String description;
    protected int price;
    public int order;
    public String shortName;
    public String shortDescription;

    public Item(String image) {
        super(GameState.assetManager.get(image, Texture.class));
        setSize(UNIT_SIZE, UNIT_SIZE);
    }

    public void addToolTip() {
        TooltipManager tooltipManager = ToolTipManager.getTooltipManager();

        NinePatch ninePatch = new NinePatch(GameState.assetManager.get(AssetManagerV2.TOOLTIP_BG, Texture.class), 16, 16, 16, 16);

        Table table = new Table();
        table.pad(30);
        Sprite sprite = new Sprite(GameState.assetManager.get(AssetManagerV2.TOOLTIP_BG, Texture.class));
        sprite.setColor(TOOLTIP_COLOR);

        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(ninePatch);
        table.setBackground(ninePatchDrawable);
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

        table.add(UIFactoryCommon.getTextLabel(getPrice() + "c", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        Label descriptionLabel = UIFactoryCommon.getTextLabel(description, UIFactoryCommon.fontSmall);
        descriptionLabel.setWrap(true);

        table.add(descriptionLabel)
                .colspan(2)
                .width(TOOLTIP_MAX_WIDTH)
                .left()
                .row();

        this.addListener(tooltip);
    }

    public int getPrice() {
        return this.price;
    }

}
