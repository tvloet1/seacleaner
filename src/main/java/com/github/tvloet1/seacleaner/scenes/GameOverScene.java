package com.github.tvloet1.seacleaner.scenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.tvloet1.seacleaner.SeaCleaner;
import com.github.tvloet1.seacleaner.entities.buttons.PlayAgainButton;
import com.github.tvloet1.seacleaner.entities.buttons.QuitGameButton;
import com.github.tvloet1.seacleaner.entities.text.MenuText;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOverScene extends StaticScene {
    private SeaCleaner seacleaner;
    private boolean win;

    public GameOverScene(SeaCleaner seacleaner, boolean win) {
        this.seacleaner = seacleaner;
        this.win = win;
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Setup music and set background image.
     */
    @Override
    public void setupScene() {
        setupMusic();
        setBackgroundImage(determineBackground());
    }

    /**
     * @author Tom Vloet
     * @since 22-APR-2023
     */
    @Override
    public void setupEntities() {
        var gameOverSceneText = new MenuText(new Coordinate2D(getWidth() / 2, getHeight() *0.2), determineText(), 80);
        addEntity(gameOverSceneText);
        var playAgainButton = new PlayAgainButton(new Coordinate2D(getWidth() / 2,getHeight() *0.4),"Play again", 30 ,seacleaner);
        addEntity(playAgainButton);
        var quitGameButton = new QuitGameButton(new Coordinate2D(getWidth() / 2,getHeight() *0.5),"Quit game", 30, seacleaner);
        addEntity(quitGameButton);
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Depending on the seaCleaner value for musicOn turn the music on.
     */
    private void setupMusic() {
        if(seacleaner.getMusicOn()) {
            setBackgroundAudio(determineMusic());
        }
    }

    /**
     * @author Tom Vloet
     * @since 30-MAY-2023
     * Depending on value of win, return text accordingly.
     */
    private String determineText() {
        if(win) {
            return "You win!";
        } else {
            return "You lose!";
        }
    }

    /**
     * @author Tom Vloet
     * @since 30-MAY-2023
     * Depending on value of win, return path of music file accordingly.
     */
    private String determineMusic() {
        if(win) {
            return "audio/musicEndScene.wav";
        } else {
            return "audio/fogHorn.mp3";
        }
    }

    /**
     * @author Tom Vloet
     * @since 30-MAY-2023
     * Depending on value of win, return path of background image file accordingly.
     */
    private String determineBackground() {
        if(win) {
            return "backgrounds/backgroundEndWin.jpg";
        } else {
            return "backgrounds/backgroundEndLose.jpg";
        }
    }
}
