package com.github.tvloet1.seacleaner.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.tvloet1.seacleaner.SeaCleaner;

public class SoundEffectsButton extends MenuButton {


    public SoundEffectsButton(Coordinate2D initialLocation, String text, int fontSize, SeaCleaner seaCleaner) {
        super(initialLocation, text, fontSize, seaCleaner);
    }

    /**
     * @author Tom Vloet
     * @since 30-MAY-2023
     * Determines what happens when the menu button is pressed.
     */
    @Override
    public void run() {
        seaCleaner.switchSoundEffectsOn();
        setSoundEffectsText();
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Change the display text for sound effects on to sound effects off and vice versa.
     */
    private void setSoundEffectsText() {
        String soundEffects;
        if(seaCleaner.getSoundEffectsOn()) {
            soundEffects = "on";
        } else {
            soundEffects = "off";
        }
        setText("Sound effects: " + soundEffects);
    }
}
