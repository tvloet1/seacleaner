package com.github.tvloet1.seacleaner.entities;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.tvloet1.seacleaner.SeaCleaner;
import com.github.tvloet1.seacleaner.entities.enemies.Enemy;
import com.github.tvloet1.seacleaner.entities.map.SeaUrchin;
import com.github.tvloet1.seacleaner.entities.map.Rock;
import com.github.tvloet1.seacleaner.entities.modifiers.ModifySwimmer;
import com.github.tvloet1.seacleaner.entities.text.GameText;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;

public class Swimmer extends DynamicSpriteEntity implements KeyListener, SceneBorderTouchingWatcher, Collider, Collided {

	private SeaCleaner seacleaner;
	private GameText scoreText;
	private GameText healthText;
	private boolean canMove = true;
	private int score = 0;
	private int health = 100;
	private int speed;
	private Timer freezeMovementTimer;
	public Swimmer(Coordinate2D initialLocation, SeaCleaner seacleaner, GameText scoreText, GameText healthText) {
		super("sprites/swimmingManv5.png", initialLocation, new Size(120, 240), 5, 2);
		this.seacleaner = seacleaner;
		this.scoreText = scoreText;
		this.healthText = healthText;
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
		if(canMove) {
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
			} else {
				setSpeed(0);
			}
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
		if (collidingObject instanceof Enemy) {
			interactWithEnemy((Enemy) collidingObject);
		}
		if (collidingObject instanceof ModifySwimmer) {
			interactWithModifier((ModifySwimmer) collidingObject);
		}
		if(score >= 10||health <= 0) {
			SoundManager.getInstance().endMusicScene();
			if(score >= 10) {
				seacleaner.setActiveScene(2);
			} else {
				seacleaner.setActiveScene(3);
			}
		}
	}

	public void haltMovement(Bounds collidingObjectBounds) {
		var anchorLocation = determineAnchorLocation(collidingObjectBounds);
		setSpeed(0);
		setAnchorLocation(anchorLocation);
	}

	/**
	 * @author Tom Vloet
	 * @since 18-APR-2023
	 * Determine location where the swimmer needs to be anchored to. (after colliding with rock for example)
	 */
	public Coordinate2D determineAnchorLocation(Bounds collidingObjectBounds) {
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
	public void increaseScore(int points) {
		score = score + points;
		scoreText.setTextValue(score);
		increaseBagSize();
	}
	public void takeDamage(int damage) {
		health -= damage;
		healthText.setTextValue(health);
	}
	public void interactWithEnemy(Enemy Enemy) {
		Enemy.attack(this);
	}
	public void interactWithModifier(ModifySwimmer modifySwimmer) {
		modifySwimmer.modify(this);
	}

	/**
	 * @author Tom Vloet
	 * @since 06-JUN-2023
	 */
	public void adjustSpeed(int val){
		speed += val;
	}

	/**
	 * @author Tom Vloet
	 * @since 06-JUN-2023
	 */
	public void adjustHealth(int val){
		health += val;
		healthText.setTextValue(health);
	}

	public void freezeMovement(int ms) {
		canMove = false;
		startFreezeCoolDown(ms);
		setSpeed(0);
	}

	private void startFreezeCoolDown(int ms) {
		// Create and schedule the damage CoolDown timer
		freezeMovementTimer = new Timer();
		freezeMovementTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				// Reset the damage CoolDown flag after the CoolDown duration
				canMove = true;
				freezeMovementTimer.cancel();
			}
		}, ms);
	}
}
