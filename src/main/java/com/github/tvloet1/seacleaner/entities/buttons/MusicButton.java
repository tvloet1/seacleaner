package com.github.tvloet1.seacleaner.entities.buttons;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.tvloet1.seacleaner.entities.SoundManager;

public class MusicButton extends MenuButton {

    public MusicButton(Coordinate2D initialLocation, String text, int fontSize) {
        super(initialLocation, text, fontSize, AnchorPoint.TOP_LEFT);
    }

    @Override
    public void run() {
        SoundManager.getInstance().toggleMusicOn();
        setMusicText();
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Change the display text for music on to music of and vice versa.
     */
    private void setMusicText() {
        String music;
        if (SoundManager.getInstance().isMusicOn()) {
            music = "on";
        } else {
            music = "off";
        }
        setText("Music: " + music);
    }
}
