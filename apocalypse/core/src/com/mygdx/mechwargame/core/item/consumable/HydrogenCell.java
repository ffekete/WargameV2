package com.mygdx.mechwargame.core.item.consumable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.state.GameData;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

import java.text.DecimalFormat;

import static com.mygdx.mechwargame.Config.FUEL_ORDER;
import static com.mygdx.mechwargame.Config.TOOLTIP_COLOR;
import static com.mygdx.mechwargame.Config.TOOLTIP_MAX_WIDTH;
import static com.mygdx.mechwargame.input.ToolTipManager.getTooltipManager;

public class HydrogenCell extends ConsumableItem {

    float fuelCapacity = 200;
    float maxFuelCapacity = 200;
    Image usedImage;
    boolean switchedImage = false;

    public HydrogenCell() {
        super(AssetManagerV2.HYDROGEN_CELL_FULL);
        usedImage = new Image(GameState.assetManager.get(AssetManagerV2.HYDROGEN_CELL_USED, Texture.class));
        order = FUEL_ORDER;
        price = 50;
        name = "hydrogen cell";
        description = String.format("Simple fuel cell used to power ships. Maximum fuel capacity: %s", maxFuelCapacity);

        addToolTip();
    }

    @Override
    public boolean consume() {
        this.fuelCapacity = GameData.starShip.engine.topUp(this.fuelCapacity);
        return this.fuelCapacity == 0;
    }

    public void addToolTip() {
        TooltipManager tooltipManager = getTooltipManager();

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

        table.add(UIFactoryCommon.getDynamicTextLabel(() -> getPrice() + "c", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("consumable", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .colspan(2)
                .width(150)
                .padRight(30)
                .row();

        table.add(UIFactoryCommon.getTextLabel("fuel", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(150)
                .padRight(30);

        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        table.add(UIFactoryCommon.getDynamicTextLabel(() -> decimalFormat.format(fuelCapacity), UIFactoryCommon.fontSmall, Align.left))
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

    @Override
    public int getPrice() {
        return (int) ((float) price * (fuelCapacity / maxFuelCapacity));
    }

    @Override
    public void draw(Batch batch,
                     float parentAlpha) {
        if (fuelCapacity < maxFuelCapacity && !switchedImage) {
            this.setDrawable(usedImage.getDrawable());
            switchedImage = true;
        }

        super.draw(batch, parentAlpha);
    }
}
