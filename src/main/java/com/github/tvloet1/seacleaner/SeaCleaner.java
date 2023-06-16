package com.github.tvloet1.seacleaner;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.github.tvloet1.seacleaner.entities.SoundManager;
import com.github.tvloet1.seacleaner.enums.SceneEnum;
import com.github.tvloet1.seacleaner.scenes.*;

public class SeaCleaner extends YaegerGame {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setupGame() {
        setGameTitle("Sea Cleaner");
        setSize(new Size(1200, 800));
        SoundManager soundManager = SoundManager.getInstance();
        soundManager.setSeaCleaner(this);
        soundManager.setSoundEffectsOn(true);
        soundManager.setMusicOn(false);
    }

    @Override
    public void setupScenes() {
        addScene(SceneEnum.TITLE_SCENE.getValue(), new TitleScene(this));
        addScene(SceneEnum.LEVEL1.getValue(), new GameLevel(this));
        addScene(SceneEnum.END_SCENE_WIN.getValue(), new GameOverScene(this, true));
        addScene(SceneEnum.END_SCENE_LOSE.getValue(), new GameOverScene(this, false));
    }

    /**
     * @param audio
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Calls YaegerGame's setBackgroundAudio.
     */
    public void turnOnMusic(String audio) {
        setBackgroundAudio(audio);
    }

    /**
     * @param volume
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Calls YaegerGame's setBackgroundAudioVolume. Pass the value for volume to the method.
     */
    public void changeVolume(double volume) {
        setBackgroundAudioVolume(volume);
    }

    /**
     * @author Tom Vloet
     * @since 07-JUN-2023
     * Calls YaegerGame's stopBackgroundAudio.
     */
    public void turnOffMusic() {
        stopBackgroundAudio();
    }
}
