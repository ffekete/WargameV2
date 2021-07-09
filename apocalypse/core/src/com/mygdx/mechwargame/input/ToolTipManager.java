package com.mygdx.mechwargame.input;

import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;

public class ToolTipManager {

    public static TooltipManager getTooltipManager() {
        TooltipManager tooltipManager = new TooltipManager();
        tooltipManager.resetTime = 0.25f;
        tooltipManager.initialTime = 0.25f;
        tooltipManager.subsequentTime = 0.25f;
        return tooltipManager;
    }

}
