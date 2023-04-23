package com.github.tvloet1.seacleaner;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.github.tvloet1.seacleaner.scenes.GameLevel;
import com.github.tvloet1.seacleaner.scenes.GameOverSceneLose;
import com.github.tvloet1.seacleaner.scenes.GameOverSceneWin;
import com.github.tvloet1.seacleaner.scenes.TitleScene;

public class SeaCleaner extends YaegerGame {
    private boolean musicOn;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void setupGame() {
        setGameTitle("Sea Cleaner");
        setSize(new Size(1200, 800));
        musicOn = false;
        switchMusic();
    }

    @Override
    public void setupScenes() {
        addScene(0, new TitleScene(this));
        addScene(1, new GameLevel(this));
        addScene(2, new GameOverSceneWin(this));
        addScene(3, new GameOverSceneLose(this));
    }

    public boolean getMusicOn() {
        return musicOn;
    }

    public void switchMusic() {
        musicOn = !musicOn;
        if(musicOn) {
            setBackgroundAudio("audio/behindEnemyLines.mp3");
            setBackgroundAudioVolume(0.25);
        }else {
            stopBackgroundAudio();
        }
    }
    public void endMusicScene() {
        stopBackgroundAudio();
    }
}
