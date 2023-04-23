package com.github.tvloet1.seacleaner.entities;

import java.util.Set;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.tvloet1.seacleaner.SeaCleaner;

import com.github.tvloet1.seacleaner.entities.map.SeaUrchin;
import com.github.tvloet1.seacleaner.entities.map.Rock;
import com.github.tvloet1.seacleaner.entities.modifiers.Modify;
import com.github.tvloet1.seacleaner.entities.text.ScoreText;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;

public class Swimmer extends DynamicSpriteEntity implements KeyListener, SceneBorderTouchingWatcher, Collider, Collided {

	private SeaCleaner seacleaner;
	private ScoreText scoreText;

	private int score;

	private int speed;

	public Swimmer(Coordinate2D initialLocation, SeaCleaner seacleaner, ScoreText scoreText) {
		super("sprites/swimmingManv5.png", initialLocation, new Size(120, 240), 5, 2);
		this.seacleaner = seacleaner;
		this.scoreText = scoreText;
		this.score = 0;
		scoreText.setScoreText(score);
		this.speed = 3;
	}

	@Override
	public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
		if (pressedKeys.contains(KeyCode.RIGHT) & pressedKeys.contains(KeyCode.DOWN)) {
			setMotion(speed, 45d);
			faceRight();
		} else if (pressedKeys.contains(KeyCode.RIGHT) & pressedKeys.contains(KeyCode.UP)) {
			setMotion(speed, 135d);
			faceRight();
		} else if (pressedKeys.contains(KeyCode.LEFT) & pressedKeys.contains(KeyCode.DOWN)) {
			setMotion(speed, 315d);
			faceLeft();
		} else if (pressedKeys.contains(KeyCode.LEFT) & pressedKeys.contains(KeyCode.UP)) {
			setMotion(speed, 225d);
			faceLeft();
		} else if (pressedKeys.contains(KeyCode.LEFT)) {
			setMotion(speed, 270d);
			faceLeft();
		} else if (pressedKeys.contains(KeyCode.RIGHT)) {
			setMotion(speed, 90d);
			faceRight();
		} else if (pressedKeys.contains(KeyCode.UP)) {
			setMotion(speed, 180d);
		} else if (pressedKeys.contains(KeyCode.DOWN)) {
			setMotion(speed, 0d);
		} else if (pressedKeys.contains(KeyCode.I)) {
			increaseSpeed();
		} else if (pressedKeys.contains(KeyCode.D)) {
			decreaseSpeed();
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
			seacleaner.endMusicScene();
			seacleaner.setActiveScene(3);
		} else if (collidingObject instanceof Rock) {
			var anchorLocation = determineAnchorDirection(getBoundingBox(), collidingObject.getBoundingBox(), getDirection(), getAnchorLocation());
			setSpeed(0);
			setAnchorLocation(anchorLocation);
		} else if (collidingObject instanceof Modify) {
			changeSpeed(((Modify) collidingObject).execute());
		}
		if(score >= 10) {
			seacleaner.endMusicScene();
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

	private void increaseScore(int value) {
		score = score + value;
	}

	private void increaseSpeed(){
		if(speed <= 5) {
			speed++;
		}
	}

	private void decreaseSpeed(){
		if(speed > 1) {
			speed--;
		}
	}

	private void changeSpeed(int value) {
		if (value > 0) {
			increaseSpeed();
		} else {
			decreaseSpeed();
		}
	}
}
