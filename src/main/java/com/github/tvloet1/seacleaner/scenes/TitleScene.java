package com.github.tvloet1.seacleaner.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.tvloet1.seacleaner.SeaCleaner;
import com.github.tvloet1.seacleaner.entities.SoundManager;
import com.github.tvloet1.seacleaner.entities.buttons.MusicButton;
import com.github.tvloet1.seacleaner.entities.buttons.PlayGameButton;
import com.github.tvloet1.seacleaner.entities.buttons.SoundEffectsButton;

import com.github.tvloet1.seacleaner.entities.text.MenuText;

public class TitleScene extends StaticScene {
    private SeaCleaner seacleaner;

    public TitleScene(SeaCleaner seacleaner) {
        this.seacleaner = seacleaner;
    }

    /**
     * @author Tom Vloet
     * @since 04-APR-2023
     * Setup background image.
     */
    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/backgroundStart.jpg");
        SoundManager.getInstance().toggleMusicOn();
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Instantiates classes for the titleScene.
     */
    @Override
    public void setupEntities() {
        var seaCleanerText = new MenuText(new Coordinate2D(getWidth() / 2, getHeight() * 0.4), "Sea Cleaner", 80);
        addEntity(seaCleanerText);
        var playGameButton = new PlayGameButton(new Coordinate2D(getWidth() / 2, getHeight() * 0.5), "Play Game", 50, seacleaner);
        addEntity(playGameButton);
        var musicButton = new MusicButton(new Coordinate2D(getWidth() * 0.70, 5), "Music: On", 30);
        addEntity(musicButton);
        var soundEffectsButton = new SoundEffectsButton(new Coordinate2D(getWidth() * 0.70, 35), "Sound effects: On", 30);
        addEntity(soundEffectsButton);
    }
}
