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
import com.github.tvloet1.seacleaner.entities.enemies.MovingEnemy;
import com.github.tvloet1.seacleaner.entities.map.SeaUrchin;
import com.github.tvloet1.seacleaner.entities.map.Rock;
import com.github.tvloet1.seacleaner.entities.modifiers.Modify;
import com.github.tvloet1.seacleaner.entities.text.GameText;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;

public class Swimmer extends DynamicSpriteEntity implements KeyListener, SceneBorderTouchingWatcher, Collider, Collided {

	private SeaCleaner seacleaner;
	private GameText scoreText;
	private GameText healthText;
	private int score;
	private int health;
	private int speed;
	public Swimmer(Coordinate2D initialLocation, SeaCleaner seacleaner, GameText scoreText, GameText healthText) {
		super("sprites/swimmingManv5.png", initialLocation, new Size(120, 240), 5, 2);
		this.seacleaner = seacleaner;
		this.scoreText = scoreText;
		this.healthText = healthText;
		this.score = 0;
		this.health = 100;
		scoreText.setTextValue(score);
		healthText.setTextValue(health);
		this.speed = 3;
	}

	/**
	 * @author Tom Vloet
	 * @since 04-APR-2023
	 * Set motion and direction of the swimmer depending on the combination of pressed keys.
	 */
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
	/**
	 * @author Tom Vloet
	 * @since 18-APR-2023
	 * Increases frame index by 2 if it is less than 8. This changes the bag size without changing the direction the swimmer is facing.
	 */
	private void increaseBagSize() {
		var currentFrameIndex = getCurrentFrameIndex();
		if(currentFrameIndex < 8) {
			setCurrentFrameIndex(currentFrameIndex+2);
		}
	}

	/**
	 * @author Tom Vloet
	 * @since 18-APR-2023
	 * Changes the frame index to make the swimmer face right, without changing the bag size.
	 */
	private void faceRight() {
		var currentFrameIndex = getCurrentFrameIndex();
		if(currentFrameIndex % 2 !=0) {
			setCurrentFrameIndex(currentFrameIndex-1);
		}
	}

	/**
	 * @author Tom Vloet
	 * @since 18-APR-2023
	 * Changes the frame index to make the swimmer face left, without changing the bag size.
	 */
	private void faceLeft() {
		var currentFrameIndex = getCurrentFrameIndex();
		if(currentFrameIndex % 2 ==0) {
			setCurrentFrameIndex(currentFrameIndex+1);
		}
	}

	/**
	 * @author Tom Vloet
	 * @since 04-APR-2023
	 * Stop swimmer from leaving the screen.
	 */
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

	/**
	 * @author Tom Vloet
	 * @since 16-APR-2023
	 * Depending on the collidingObject perform different behaviors.
	 * Litter = pick up and score points
	 * SeaUrchin = lose game, move to next scene
	 * Rock = do not pass through, anchor swimmer to previous location
	 * Modify = execute the modifier, apply to the swimmer
	 * Move to next scene if player wins
	 */
	@Override
	public void onCollision(Collider collidingObject) {
		if (collidingObject instanceof Litter){
			increaseScore(((Litter) collidingObject).getValue());
			increaseBagSize();
			scoreText.setTextValue(score);
		} else if (collidingObject instanceof SeaUrchin || collidingObject instanceof Rock) {
			var anchorLocation = determineAnchorLocation(collidingObject.getBoundingBox());
			setSpeed(0);
			setAnchorLocation(anchorLocation);
			if(collidingObject instanceof SeaUrchin) {
				takeDamage(10);
			}
		} else if (collidingObject instanceof MovingEnemy) {
			interactWithEnemy((MovingEnemy) collidingObject);
		} else if (collidingObject instanceof Modify) {
			changeSpeed(((Modify) collidingObject).execute());
		}
		if(score >= 10) {
			seacleaner.endMusicScene();
			seacleaner.setActiveScene(2);
		}
		if(health <= 0) {
			seacleaner.endMusicScene();
			seacleaner.setActiveScene(3);
		}
	}

	/**
	 * @author Tom Vloet
	 * @since 18-APR-2023
	 * Determine location where the swimmer needs to be anchored to. (after colliding with rock for example)
	 */
	private Coordinate2D determineAnchorLocation(Bounds collidingObjectBounds) {
		var swimmerBounds = getBoundingBox();
		var direction = getDirection();
		var anchorLocation = getAnchorLocation();
		if (direction == 0d) {
			return new Coordinate2D(anchorLocation.getX(),collidingObjectBounds.getMinY()-getHeight()-1);
		} else if (direction == 180d) {
			return new Coordinate2D(anchorLocation.getX(),collidingObjectBounds.getMaxY()+1);
		} else if (direction == 270d) {
			return new Coordinate2D(collidingObjectBounds.getMaxX()+1,anchorLocation.getY());
		} else if (direction == 90d) {
			return new Coordinate2D(collidingObjectBounds.getMinX()-getWidth()-1,anchorLocation.getY());
		} else if(direction == 45d) {
			if(swimmerBounds.getMaxX() - collidingObjectBounds.getMinX() < swimmerBounds.getMaxY() - collidingObjectBounds.getMinY()) {
				return new Coordinate2D(collidingObjectBounds.getMinX() - getWidth() - 1, anchorLocation.getY());
			} else {
				return new Coordinate2D(anchorLocation.getX(),collidingObjectBounds.getMinY()-getHeight()-1);
			}
		} else if(direction == 135d) {
			if(swimmerBounds.getMaxX() - collidingObjectBounds.getMinX() < collidingObjectBounds.getMaxY() - swimmerBounds.getMinY()) {
				return new Coordinate2D(collidingObjectBounds.getMinX()-getWidth()-1,anchorLocation.getY());
			} else {
				return new Coordinate2D(anchorLocation.getX(),collidingObjectBounds.getMaxY()+1);
			}
		} else if (direction == 225d) {
			if(collidingObjectBounds.getMaxX() - swimmerBounds.getMinX() < collidingObjectBounds.getMaxY() - swimmerBounds.getMinY()) {
				return new Coordinate2D(collidingObjectBounds.getMaxX()+1,anchorLocation.getY());
			} else {
				return new Coordinate2D(anchorLocation.getX(),collidingObjectBounds.getMaxY()+1);
			}
		} else if (direction == 315d) {
			if(swimmerBounds.getMinX() - collidingObjectBounds.getMaxX() < collidingObjectBounds.getMinY() - swimmerBounds.getMaxY()) {
				return new Coordinate2D(anchorLocation.getX(),collidingObjectBounds.getMinY()-getHeight()-1);
			} else {
				return new Coordinate2D(collidingObjectBounds.getMaxX()+1,anchorLocation.getY());
			}
		}
		return anchorLocation;
	}


	/**
	 * @author Tom Vloet
	 * @since 22-APR-2023
	 */
	private void increaseScore(int value) {
		score = score + value;
	}
	public void takeDamage(int damage) {
		health -= damage;
		healthText.setTextValue(health);
	}
	public void interactWithEnemy(MovingEnemy movingEnemy) {
		movingEnemy.attack(this);
	}

	/**
	 * @author Tom Vloet
	 * @since 23-APR-2023
	 */
	private void increaseSpeed(){
		if(speed <= 5) {
			speed++;
		}
	}

	/**
	 * @author Tom Vloet
	 * @since 23-APR-2023
	 */
	private void decreaseSpeed(){
		if(speed > 1) {
			speed--;
		}
	}

	/**
	 * @author Tom Vloet
	 * @since 23-APR-2023
	 */
	private void changeSpeed(int value) {
		if (value > 0) {
			increaseSpeed();
		} else {
			decreaseSpeed();
		}
	}
}
