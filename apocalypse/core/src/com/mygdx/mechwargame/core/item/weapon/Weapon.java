package com.mygdx.mechwargame.core.item.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.mechwargame.AssetManagerV2;
import com.mygdx.mechwargame.core.item.Item;
import com.mygdx.mechwargame.core.item.modification.Modification;
import com.mygdx.mechwargame.core.item.weapon.socket.Socket;
import com.mygdx.mechwargame.state.GameState;
import com.mygdx.mechwargame.ui.factory.UIFactoryCommon;

import static com.mygdx.mechwargame.Config.TOOLTIP_COLOR;
import static com.mygdx.mechwargame.Config.WEAPON_ORDER;

public abstract class Weapon extends Item {

    private static final int WIDTH = 250;

    public Modification modification;

    public int damage;
    public int range;
    public int rateOfFire;
    public int ammo;
    public int accuracy; // roll accuracy against armor, if this roll is bigger, hit

    public String name;
    public String longName;

    public Socket socket;

    public Weapon(String image) {
        super(image);
        order = WEAPON_ORDER;
    }

    public void addToolTip() {
        TooltipManager tooltipManager = new TooltipManager();
        tooltipManager.resetTime = 0.5f;
        tooltipManager.initialTime = 0.5f;
        tooltipManager.subsequentTime = 0.5f;

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
                .width(WIDTH)
                .padRight(30);
        table.add(UIFactoryCommon.getTextLabel(longName, UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("value", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(WIDTH)
                .padRight(30);

        table.add(UIFactoryCommon.getDynamicTextLabel(() -> getPrice() + "c", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("damage", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(WIDTH)
                .padRight(30);

        table.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(damage) + "x" + rateOfFire, UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("accuracy", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(WIDTH)
                .padRight(30);

        table.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(accuracy), UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("range", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(WIDTH)
                .padRight(30);

        table.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(range), UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("ammo", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(WIDTH)
                .padRight(30);

        table.add(UIFactoryCommon.getDynamicTextLabel(() -> Integer.toString(ammo), UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel("socket", UIFactoryCommon.fontSmall, Align.left))
                .left()
                .width(WIDTH)
                .padRight(30);

        table.add(UIFactoryCommon.getDynamicTextLabel(() -> socket.name, UIFactoryCommon.fontSmall, Align.left))
                .left()
                .expandX()
                .row();

        table.add(UIFactoryCommon.getTextLabel(description, UIFactoryCommon.fontSmall))
                .colspan(2)
                .row();

        this.addListener(tooltip);
    }

    @Override
    public String toString() {
        return name + " dmg: " + damage + "(x" + rateOfFire + ") rng: " + range + " acc: " + accuracy;
    }
}
