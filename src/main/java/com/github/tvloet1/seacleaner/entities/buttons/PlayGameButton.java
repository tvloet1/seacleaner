package com.github.tvloet1.seacleaner.entities.buttons;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.tvloet1.seacleaner.SeaCleaner;
import com.github.tvloet1.seacleaner.entities.SoundManager;

public class PlayGameButton extends MenuButton {
    public PlayGameButton(Coordinate2D initialLocation, String text, int fontSize, SeaCleaner seaCleaner) {
        super(initialLocation, text, fontSize, AnchorPoint.CENTER_CENTER, seaCleaner);
    }

    /**
     * @author Tom Vloet
     * @since 30-MAY-2023
     * Determines what happens when the menu button is pressed.
     */
    @Override
    public void run() {
        SoundManager.getInstance().playSound("audio/soundStartGame.wav");
        SoundManager.getInstance().endMusicScene();
        seaCleaner.setActiveScene(1);
    }
}
