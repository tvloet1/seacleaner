package com.github.tvloet1.seacleaner.scenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.tvloet1.seacleaner.SeaCleaner;
import com.github.tvloet1.seacleaner.entities.buttons.PlayAgainButton;
import com.github.tvloet1.seacleaner.entities.buttons.QuitGameButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOverSceneWin extends StaticScene {
	private SeaCleaner seacleaner;

	public GameOverSceneWin(SeaCleaner seacleaner) {
		this.seacleaner = seacleaner;
	}

	@Override
	public void setupScene() {
		setBackgroundAudio("audio/musicEndScene.wav");
		setBackgroundImage("backgrounds/backgroundEndWin.jpg");
	}

	@Override
	public void setupEntities() {
		var gameOverSceneWinText = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() *0.2), "You Win!");
		gameOverSceneWinText.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		gameOverSceneWinText.setFill(Color.ORANGE);
		gameOverSceneWinText.setFont(Font.font("Courier New", FontWeight.BOLD, 80));
		addEntity(gameOverSceneWinText);
		var playAgainButton = new PlayAgainButton(
				new Coordinate2D(getWidth() / 2,getHeight() *0.4),seacleaner
		);
		playAgainButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		addEntity(playAgainButton);
		var quitGameButton = new QuitGameButton(
				new Coordinate2D(getWidth() / 2,getHeight() *0.5),seacleaner
		);
		quitGameButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		addEntity(quitGameButton);
	}

}
