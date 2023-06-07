package com.github.tvloet1.seacleaner;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.github.tvloet1.seacleaner.entities.SoundManager;
import com.github.tvloet1.seacleaner.scenes.*;

public class SeaCleaner extends YaegerGame {
    private boolean soundEffectsOn;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void setupGame() {
        setGameTitle("Sea Cleaner");
        setSize(new Size(1200, 800));
        soundEffectsOn = true;
        SoundManager soundManager = SoundManager.getInstance();
        soundManager.setSeaCleaner(this);
        soundManager.setSoundEffectsOn(true);
        soundManager.setMusicOn(false);
    }

    @Override
    public void setupScenes() {
        addScene(0, new TitleScene(this));
        addScene(1, new GameLevel(this));
        addScene(2, new GameOverScene(this,true));
        addScene(3, new GameOverScene(this,false));
    }

    public void endMusicScene() {
        stopBackgroundAudio();
    }

    public void turnOnMusic(String audio) {
        setBackgroundAudio(audio);
    }

    public void changeVolume(double volume)  {
        setBackgroundAudioVolume(volume);
    }

    public void turnOffMusic() {
        stopBackgroundAudio();
    }

    public boolean getSoundEffectsOn() {
        return soundEffectsOn;
    }

    public void switchSoundEffectsOn() {
        soundEffectsOn = !soundEffectsOn;
    }
}
