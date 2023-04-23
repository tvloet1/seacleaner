package com.github.tvloet1.seacleaner.scenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.tvloet1.seacleaner.SeaCleaner;
import com.github.tvloet1.seacleaner.entities.buttons.MusicButton;
import com.github.tvloet1.seacleaner.entities.buttons.SoundEffectsButton;
import com.github.tvloet1.seacleaner.entities.buttons.StartButton;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TitleScene extends StaticScene {
	private SeaCleaner seacleaner;

	public TitleScene(SeaCleaner seacleaner) {
		this.seacleaner = seacleaner;
	}

	@Override
	public void setupScene() {
		setBackgroundImage("backgrounds/backgroundStart.jpg");
	}

	@Override
	public void setupEntities() {
		var seaCleanerText = new TextEntity(new Coordinate2D(getWidth() / 2, getHeight() *0.4), "Sea Cleaner");
		seaCleanerText.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		seaCleanerText.setFill(Color.ORANGE);
		seaCleanerText.setFont(Font.font("Courier New", FontWeight.BOLD, 80));
		addEntity(seaCleanerText);
		var startButton = new StartButton(new Coordinate2D(getWidth() / 2, getHeight() * 0.5), seacleaner);
		startButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
		addEntity(startButton);
		var musicButton = new MusicButton(new Coordinate2D(getWidth() * 0.70, 5), seacleaner);
		addEntity(musicButton);
		var soundEffectsButton = new SoundEffectsButton(new Coordinate2D(getWidth() * 0.70, 35), seacleaner);
		addEntity(soundEffectsButton);
	}

}
