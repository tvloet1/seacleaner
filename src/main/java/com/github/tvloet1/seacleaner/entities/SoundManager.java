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

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void setSeaCleaner(SeaCleaner seaCleaner) {
        if(this.seaCleaner == null) {
            this.seaCleaner = seaCleaner;
        }
    }

    public boolean isSoundEffectsOn() {
        return soundEffectsOn;
    }

    public void setSoundEffectsOn(boolean soundEffectsOn) {
        this.soundEffectsOn = soundEffectsOn;
    }

    public void toggleSoundEffectsOn() {
        soundEffectsOn = !soundEffectsOn;
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

    public void toggleMusicOn() {
        musicOn = !musicOn;
        if(musicOn) {
            seaCleaner.turnOnMusic("audio/behindEnemyLines.mp3");
            seaCleaner.changeVolume(0.25);
        } else {
            seaCleaner.turnOffMusic();
        }
    }

    public void endMusicScene() {
        seaCleaner.endMusicScene();
    }
    public void playSound(String soundClip) {
        if (soundEffectsOn) {
            var sound = new SoundClip(soundClip);
            sound.play();
        }
    }
}