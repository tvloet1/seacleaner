package com.github.tvloet1.seacleaner.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.tvloet1.seacleaner.SeaCleaner;

public class PlayGameButton extends MenuButton {
    public PlayGameButton(Coordinate2D initialLocation, String text, int fontSize, SeaCleaner seaCleaner) {
        super(initialLocation, text, fontSize, seaCleaner);
    }

    /**
     * @author Tom Vloet
     * @since 30-MAY-2023
     * Determines what happens when the menu button is pressed.
     */
    @Override
    public void run() {
        playSound();
        seaCleaner.endMusicScene();
        seaCleaner.setActiveScene(1);
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Instantiates sound-clip and plays it
     */
    private void playSound() {
        if (seaCleaner.getSoundEffectsOn()) {
            var startGameSound = new SoundClip("audio/soundStartGame.wav");
            startGameSound.play();
        }
    }
}
