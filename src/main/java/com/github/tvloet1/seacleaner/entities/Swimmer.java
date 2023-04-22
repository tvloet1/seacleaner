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

import com.github.tvloet1.seacleaner.entities.map.SeaUrchin;
import com.github.tvloet1.seacleaner.entities.map.Rock;
import com.github.tvloet1.seacleaner.entities.text.ScoreText;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;

public class Swimmer extends DynamicSpriteEntity implements KeyListener, SceneBorderTouchingWatcher, Collider, Collided {

	private SeaCleaner seacleaner;
	private ScoreText scoreText;

	private int score;

	public Swimmer(Coordinate2D initialLocation, SeaCleaner seacleaner, ScoreText scoreText) {
		super("sprites/swimmingManv5.png", initialLocation, new Size(120, 240), 5, 2);
		this.seacleaner = seacleaner;
		this.scoreText = scoreText;
		this.score = 0;
		scoreText.setScoreText(score);
	}

	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		if (pressedKeys.contains(KeyCode.RIGHT) & pressedKeys.contains(KeyCode.DOWN)) {
			setMotion(3, 45d);
			faceRight();
		} else if (pressedKeys.contains(KeyCode.RIGHT) & pressedKeys.contains(KeyCode.UP)) {
			setMotion(3, 135d);
			faceRight();
		} else if (pressedKeys.contains(KeyCode.LEFT) & pressedKeys.contains(KeyCode.DOWN)) {
			setMotion(3, 315d);
			faceLeft();
		} else if (pressedKeys.contains(KeyCode.LEFT) & pressedKeys.contains(KeyCode.UP)) {
			setMotion(3, 225d);
			faceLeft();
		} else if (pressedKeys.contains(KeyCode.LEFT)) {
			setMotion(3, 270d);
			faceLeft();
		} else if (pressedKeys.contains(KeyCode.RIGHT)) {
			setMotion(3, 90d);
			faceRight();
		} else if (pressedKeys.contains(KeyCode.UP)) {
			setMotion(3, 180d);
		} else if (pressedKeys.contains(KeyCode.DOWN)) {
			setMotion(3, 0d);
		} else if (pressedKeys.contains(KeyCode.I)) {
			increaseBagSize();
		} else if (pressedKeys.contains(KeyCode.D)) {
			decreaseBagSize();
		} else {
			setSpeed(0);
		}
	}

	private void increaseBagSize() {
		var currentFrameIndex = getCurrentFrameIndex();
		if(currentFrameIndex < 8) {
			setCurrentFrameIndex(currentFrameIndex+2);
		}
	}

	private void decreaseBagSize() {
		var currentFrameIndex = getCurrentFrameIndex();
		if(currentFrameIndex > 1) {
			setCurrentFrameIndex(currentFrameIndex-2);
		}
	}

	private void faceRight() {
		var currentFrameIndex = getCurrentFrameIndex();
		if(currentFrameIndex % 2 !=0) {
			setCurrentFrameIndex(currentFrameIndex-1);
		}
	}

	private void faceLeft() {
		var currentFrameIndex = getCurrentFrameIndex();
		if(currentFrameIndex % 2 ==0) {
			setCurrentFrameIndex(currentFrameIndex+1);
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
			increaseScore(((Litter) collidingObject).getValue());
			scoreText.setScoreText(score);
			increaseBagSize();
		} else if (collidingObject instanceof SeaUrchin) {
			seacleaner.setActiveScene(2);
		} else if (collidingObject instanceof Rock) {
			var anchorLocation = determineAnchorDirection(getBoundingBox(), collidingObject.getBoundingBox(), getDirection(), getAnchorLocation());
			setSpeed(0);
			setAnchorLocation(anchorLocation);
		}
		if(score >= 10) {
			var finishGameSound = new SoundClip("audio/soundFinishGame.wav");
			finishGameSound.play();
			seacleaner.setActiveScene(2);
		}
	}

	private Coordinate2D determineAnchorDirection(Bounds swimmer, Bounds collidingObject, double direction, Coordinate2D anchorLocation) {
		if (direction == 0d) {
			return new Coordinate2D(anchorLocation.getX(),collidingObject.getMinY()-getHeight()-1);
		} else if (direction == 180d) {
			return new Coordinate2D(anchorLocation.getX(),collidingObject.getMaxY()+1);
		} else if (direction == 270d) {
			return new Coordinate2D(collidingObject.getMaxX()+1,anchorLocation.getY());
		} else if (direction == 90d) {
			return new Coordinate2D(collidingObject.getMinX()-getWidth()-1,anchorLocation.getY());
		} else if(direction == 45d) {
			if(swimmer.getMaxX() - collidingObject.getMinX() < swimmer.getMaxY() - collidingObject.getMinY()) {
				return new Coordinate2D(collidingObject.getMinX() - getWidth() - 1, anchorLocation.getY());
			} else {
				return new Coordinate2D(anchorLocation.getX(),collidingObject.getMinY()-getHeight()-1);
			}
		} else if(direction == 135d) {
			if(swimmer.getMaxX() - collidingObject.getMinX() < collidingObject.getMaxY() - swimmer.getMinY()) {
				return new Coordinate2D(collidingObject.getMinX()-getWidth()-1,anchorLocation.getY());
			} else {
				return new Coordinate2D(anchorLocation.getX(),collidingObject.getMaxY()+1);
			}
		} else if (direction == 225d) {
			if(collidingObject.getMaxX() - swimmer.getMinX() < collidingObject.getMaxY() - swimmer.getMinY()) {
				return new Coordinate2D(collidingObject.getMaxX()+1,anchorLocation.getY());
			} else {
				return new Coordinate2D(anchorLocation.getX(),collidingObject.getMaxY()+1);
			}
		} else if (direction == 315d) {
			if(swimmer.getMinX() - collidingObject.getMaxX() < collidingObject.getMinY() - swimmer.getMaxY()) {
				return new Coordinate2D(anchorLocation.getX(),collidingObject.getMinY()-getHeight()-1);
			} else {
				return new Coordinate2D(collidingObject.getMaxX()+1,anchorLocation.getY());
			}
		}
		return anchorLocation;
	}

	private void increaseScore (int value) {
		score = score + value;
	}
}
