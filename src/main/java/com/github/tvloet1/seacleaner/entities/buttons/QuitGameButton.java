package com.github.tvloet1.seacleaner.entities.buttons;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.tvloet1.seacleaner.SeaCleaner;

public class QuitGameButton extends MenuButton {


    public QuitGameButton(Coordinate2D initialLocation, String text, int fontSize, SeaCleaner seaCleaner) {
        super(initialLocation, text, fontSize, AnchorPoint.CENTER_CENTER, seaCleaner);
    }

    @Override
    public void run() {
        seaCleaner.quit();
    }
}
