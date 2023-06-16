package com.github.tvloet1.seacleaner.entities.buttons;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.tvloet1.seacleaner.SeaCleaner;
import com.github.tvloet1.seacleaner.enums.SceneEnum;

public class PlayAgainButton extends MenuButton {

    public PlayAgainButton(Coordinate2D initialLocation, String text, int fontSize, SeaCleaner seaCleaner) {
        super(initialLocation, text, fontSize, AnchorPoint.CENTER_CENTER, seaCleaner);
    }

    @Override
    public void run() {
        seaCleaner.setActiveScene(SceneEnum.LEVEL1.getValue());
    }
}
