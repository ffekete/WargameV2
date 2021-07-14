package com.mygdx.mechwargame.core.item.armor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.input.ToolTipManager;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

import static com.mygdx.mechwargame.Config.ARMOR_ORDER;
import static com.mygdx.mechwargame.Config.TOOLTIP_COLOR;
import static com.mygdx.mechwargame.Config.TOOLTIP_MAX_WIDTH;

public abstract class Armor extends Item {

    public int protection;

    public Armor(String image) {
        super(image);
        order = ARMOR_ORDER;
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
                .width(300)
                .padRight(30);
        table.add(UIFactoryCommon.getTextLabel(name, UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("value", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(300)
                .padRight(30);

        table.add(UIFactoryCommon.getTextLabel(getPrice() + "c", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("protection", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(300)
                .padBottom(50)
                .padRight(30);

        table.add(UIFactoryCommon.getTextLabel(Integer.toString(protection), UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .padBottom(50)
                .row();

        Label descriptionLabel = UIFactoryCommon.getTextLabel(description, UIFactoryCommon.fontSmall);
        descriptionLabel.setWrap(true);

        Table descrContainer = new Table();

        descrContainer.add(descriptionLabel)
                .width(TOOLTIP_MAX_WIDTH)
                .left();

        table.add(descrContainer)
                .colspan(2)
                .width(TOOLTIP_MAX_WIDTH)
                .left()
                .row();

        this.addListener(tooltip);
    }
}
