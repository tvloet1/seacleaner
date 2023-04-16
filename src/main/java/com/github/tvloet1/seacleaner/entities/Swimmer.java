package com.github.tvloet1.seacleaner.entities;

import java.util.Set;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.tvloet1.seacleaner.SeaCleaner;

import com.github.tvloet1.seacleaner.entities.text.ScoreText;
import javafx.scene.input.KeyCode;

public class Swimmer extends DynamicSpriteEntity implements KeyListener, SceneBorderTouchingWatcher, Collider, Collided {

	private SeaCleaner seacleaner;
	private ScoreText scoreText;

	private int score;

	public Swimmer(Coordinate2D initialLocation, SeaCleaner seacleaner, ScoreText scoreText) {
		super("sprites/swimmingManv4.png", initialLocation, new Size(120, 240), 2, 2);
		this.seacleaner = seacleaner;
		this.scoreText = scoreText;
		this.score = 0;
		scoreText.setScoreText(score);
	}

	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		if (pressedKeys.contains(KeyCode.RIGHT) & pressedKeys.contains(KeyCode.DOWN)) {
			setMotion(3, 45d);
			setCurrentFrameIndex(goRight());
		} else if (pressedKeys.contains(KeyCode.RIGHT) & pressedKeys.contains(KeyCode.UP)) {
			setMotion(3, 135d);
			setCurrentFrameIndex(goRight());
		} else if (pressedKeys.contains(KeyCode.LEFT) & pressedKeys.contains(KeyCode.DOWN)) {
			setMotion(3, 315d);
			setCurrentFrameIndex(goLeft());
		} else if (pressedKeys.contains(KeyCode.LEFT) & pressedKeys.contains(KeyCode.UP)) {
			setMotion(3, 225d);
			setCurrentFrameIndex(goLeft());
		} else if (pressedKeys.contains(KeyCode.LEFT)) {
			setMotion(3, 270d);
			setCurrentFrameIndex(goLeft());
		} else if (pressedKeys.contains(KeyCode.RIGHT)) {
			setMotion(3, 90d);
			setCurrentFrameIndex(goRight());
		} else if (pressedKeys.contains(KeyCode.UP)) {
			setMotion(3, 180d);
		} else if (pressedKeys.contains(KeyCode.DOWN)) {
			setMotion(3, 0d);
		} else if (pressedKeys.contains(KeyCode.Q)) {
			setCurrentFrameIndex(0);
		} else if (pressedKeys.contains(KeyCode.W)) {
			setCurrentFrameIndex(1);
		} else if (pressedKeys.contains(KeyCode.E)) {
			setCurrentFrameIndex(2);
		} else if (pressedKeys.contains(KeyCode.R)) {
			setCurrentFrameIndex(3);
		} else {
			setSpeed(0);
		}
	}

	private int goRight() {
		if(getCurrentFrameIndex()<=1) {
			return 0;
		}else {
			return 2;
		}
	}

	private int goLeft() {
		if(getCurrentFrameIndex()<=1) {
			return 1;
		}else {
			return 3;
		}
	}

	@Override
	public void notifyBoundaryTouching(SceneBorder border) {
		setSpeed(0);
		switch (border) {
		case TOP:
			setAnchorLocationY(1);
			break;
		case BOTTOM:
			setAnchorLocationY(getSceneHeight() - getHeight() - 1);
			break;
		case LEFT:
			setAnchorLocationX(1);
			break;
		case RIGHT:
			setAnchorLocationX(getSceneWidth() - getWidth() - 1);
		default:
			break;
		}
	}

	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Litter){
			score++;
			scoreText.setScoreText(score);
		}
		if(score >= 10) {
			var finishGameSound = new SoundClip("audio/soundFinishGame.wav");
			finishGameSound.play();
			seacleaner.setActiveScene(2);
		}
	}
}
