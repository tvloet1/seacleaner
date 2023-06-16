package com.github.tvloet1.seacleaner.entities;

import com.github.hanyaeger.api.media.SoundClip;
import com.github.tvloet1.seacleaner.SeaCleaner;

public class SoundManager {
    private static SoundManager instance;
    private boolean soundEffectsOn;
    private boolean musicOn;
    private SeaCleaner seaCleaner;

    private SoundManager() {
        // Private constructor to prevent direct instantiation
    }

    /**
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Returns the Static instance of SoundManager. If it does not yet exist, it is created.
     */
    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * @param seaCleaner
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Sets the value for seaCleaner if it is currently null.
     */
    public void setSeaCleaner(SeaCleaner seaCleaner) {
        if (this.seaCleaner == null) {
            this.seaCleaner = seaCleaner;
        }
    }

    /**
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Returns the Static instance of SoundManager. If it does not yet exist, it is created.
     */
    public boolean isSoundEffectsOn() {
        return soundEffectsOn;
    }

    /**
     * @param soundEffectsOn
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Sets the value of soundEffectsOn to the input value of soundEffectsOn.
     */
    public void setSoundEffectsOn(boolean soundEffectsOn) {
        this.soundEffectsOn = soundEffectsOn;
    }

    /**
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Sets the value of soundEffectsOn to the opposite value.
     */
    public void toggleSoundEffectsOn() {
        soundEffectsOn = !soundEffectsOn;
    }

    /**
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Returns the value for musicOn.
     */
    public boolean isMusicOn() {
        return musicOn;
    }

    /**
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Sets the value of musicOn to the input value of musicOn.
     */
    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

    /**
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Sets the value of musicOn to the opposite value. If the musicOn value is true then call playMusic. If the musicOn
     * value is false then call the seaCleaner's turnOffMusic method.
     */
    public void toggleMusicOn() {
        musicOn = !musicOn;
        if (musicOn) {
            playMusic();
        } else {
            seaCleaner.turnOffMusic();
        }
    }

    /**
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Calls the seaCleaners turnOnMusic and changeVolume methods.
     */
    public void playMusic() {
        seaCleaner.turnOnMusic("audio/behindEnemyLines.mp3");
        seaCleaner.changeVolume(0.25);
    }

    /**
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Calls the seaCleaner's endMusicScene method.
     */
    public void endMusicScene() {
        seaCleaner.turnOffMusic();
    }

    /**
     * @param soundClip
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Takes in a soundClip and plays the sound if the boolean soundEffectsOn is true.
     */
    public void playSound(String soundClip) {
        if (soundEffectsOn) {
            var sound = new SoundClip(soundClip);
            sound.play();
        }
    }
}