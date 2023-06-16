package com.github.tvloet1.seacleaner.entities.buttons;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.tvloet1.seacleaner.entities.SoundManager;

public class SoundEffectsButton extends MenuButton {

    public SoundEffectsButton(Coordinate2D initialLocation, String text, int fontSize) {
        super(initialLocation, text, fontSize, AnchorPoint.TOP_LEFT);
    }

    @Override
    public void run() {
        SoundManager.getInstance().toggleSoundEffectsOn();
        setSoundEffectsText();
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Change the display text for sound effects on to sound effects off and vice versa.
     */
    private void setSoundEffectsText() {
        String soundEffects;
        if (SoundManager.getInstance().isSoundEffectsOn()) {
            soundEffects = "on";
        } else {
            soundEffects = "off";
        }
        setText("Sound effects: " + soundEffects);
    }
}
