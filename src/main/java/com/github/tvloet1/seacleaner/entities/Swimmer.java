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
import com.github.tvloet1.seacleaner.entities.modifiers.ModifySwimmer;
import com.github.tvloet1.seacleaner.entities.text.GameText;
import com.github.tvloet1.seacleaner.enums.HealthEnum;
import com.github.tvloet1.seacleaner.enums.SceneEnum;
import com.github.tvloet1.seacleaner.enums.ScoreEnum;
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
        super("sprites/swimmingMan.png", initialLocation, new Size(120, 240), 5, 2);
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
        if (canMove) {
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
        if (currentFrameIndex < 8) {
            setCurrentFrameIndex(currentFrameIndex + 2);
        }
    }

    /**
     * @author Tom Vloet
     * @since 18-APR-2023
     * Changes the frame index to make the swimmer face right, without changing the bag size.
     */
    private void faceRight() {
        var currentFrameIndex = getCurrentFrameIndex();
        if (currentFrameIndex % 2 != 0) {
            setCurrentFrameIndex(currentFrameIndex - 1);
        }
    }

    /**
     * @author Tom Vloet
     * @since 18-APR-2023
     * Changes the frame index to make the swimmer face left, without changing the bag size.
     */
    private void faceLeft() {
        var currentFrameIndex = getCurrentFrameIndex();
        if (currentFrameIndex % 2 == 0) {
            setCurrentFrameIndex(currentFrameIndex + 1);
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
     * @param collidingObject
     * @author Tom Vloet
     * @since 16-APR-2023
     * Depending on the collidingObject perform different behaviors. Runs interactWithEnemy for all Enemy child classes.
     * Runs interactWithModifier for all classes that implement ModifySwimmer.
     * Also ends the game when the Swimmer's health reaches minimum threshold.
     * Also ends the game when the Swimmer's score reaches maximum threshold.
     */
    @Override
    public void onCollision(Collider collidingObject) {
        if (collidingObject instanceof Enemy) {
            interactWithEnemy((Enemy) collidingObject);
        }
        if (collidingObject instanceof ModifySwimmer) {
            interactWithModifier((ModifySwimmer) collidingObject);
        }
        if (score >= ScoreEnum.MAXIMUM.getValue() || health <= HealthEnum.MINIMUM.getValue()) {
            SoundManager.getInstance().endMusicScene();
            if (score >= ScoreEnum.MAXIMUM.getValue()) {
                seacleaner.setActiveScene(SceneEnum.END_SCENE_WIN.getValue());
            } else {
                seacleaner.setActiveScene(SceneEnum.END_SCENE_LOSE.getValue());
            }
        }
    }

    /**
     * @param collidingObjectBounds
     * @author Tom Vloet
     * @since 18-APR-2023
     * Determine location where the swimmer needs to be anchored to. (after colliding with rock for example)
     */
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
            return new Coordinate2D(anchorLocation.getX(), collidingObjectBounds.getMinY() - getHeight() - 1);
        } else if (direction == 180d) {
            return new Coordinate2D(anchorLocation.getX(), collidingObjectBounds.getMaxY() + 1);
        } else if (direction == 270d) {
            return new Coordinate2D(collidingObjectBounds.getMaxX() + 1, anchorLocation.getY());
        } else if (direction == 90d) {
            return new Coordinate2D(collidingObjectBounds.getMinX() - getWidth() - 1, anchorLocation.getY());
        } else if (direction == 45d) {
            if (swimmerBounds.getMaxX() - collidingObjectBounds.getMinX() < swimmerBounds.getMaxY() - collidingObjectBounds.getMinY()) {
                return new Coordinate2D(collidingObjectBounds.getMinX() - getWidth() - 1, anchorLocation.getY());
            } else {
                return new Coordinate2D(anchorLocation.getX(), collidingObjectBounds.getMinY() - getHeight() - 1);
            }
        } else if (direction == 135d) {
            if (swimmerBounds.getMaxX() - collidingObjectBounds.getMinX() < collidingObjectBounds.getMaxY() - swimmerBounds.getMinY()) {
                return new Coordinate2D(collidingObjectBounds.getMinX() - getWidth() - 1, anchorLocation.getY());
            } else {
                return new Coordinate2D(anchorLocation.getX(), collidingObjectBounds.getMaxY() + 1);
            }
        } else if (direction == 225d) {
            if (collidingObjectBounds.getMaxX() - swimmerBounds.getMinX() < collidingObjectBounds.getMaxY() - swimmerBounds.getMinY()) {
                return new Coordinate2D(collidingObjectBounds.getMaxX() + 1, anchorLocation.getY());
            } else {
                return new Coordinate2D(anchorLocation.getX(), collidingObjectBounds.getMaxY() + 1);
            }
        } else if (direction == 315d) {
            if (swimmerBounds.getMinX() - collidingObjectBounds.getMaxX() < collidingObjectBounds.getMinY() - swimmerBounds.getMaxY()) {
                return new Coordinate2D(anchorLocation.getX(), collidingObjectBounds.getMinY() - getHeight() - 1);
            } else {
                return new Coordinate2D(collidingObjectBounds.getMaxX() + 1, anchorLocation.getY());
            }
        }
        return anchorLocation;
    }


    /**
     * @param points
     * @author Tom Vloet
     * @since 22-APR-2023
     * Adds the value of points to the score, then sets the scoreText and increases the size of the garbage bag.
     */
    public void increaseScore(int points) {
        score = score + points;
        scoreText.setTextValue(score);
        increaseBagSize();
    }

    /**
     * @param damage
     * @author Tom Vloet
     * @since 05-JUN-2023
     * Adds (or subtracts) the input damage to/from the health. Also updates the healthText.
     */
    public void takeDamage(int damage) {
        health -= damage;
        healthText.setTextValue(health);
    }

    /**
     * @param enemy
     * @author Tom Vloet
     * @since 05-JUN-2023
     * Performs the enemy's attack method, which also needs the Swimmer to be passed along in their method.
     */
    public void interactWithEnemy(Enemy enemy) {
        enemy.attack(this);
    }


    /**
     * @param modifySwimmer
     * @author Tom Vloet
     * @since 06-JUN-2023
     * Performs the modifySwimmer's modify method, which also needs the Swimmer to be passed along in their method.
     */
    public void interactWithModifier(ModifySwimmer modifySwimmer) {
        modifySwimmer.modify(this);
    }

    /**
     * @param value
     * @author Tom Vloet
     * @since 06-JUN-2023
     * Adds (or subtracts) the input value to/from the Swimmer's speed.
     */
    public void adjustSpeed(int value) {
        speed += value;
    }

    /**
     * @author Tom Vloet
     * @since 06-JUN-2023
     * Adds (or subtracts) the input value to/from the Swimmer's health. Also sets the healthText.
     */
    public void adjustHealth(int value) {
        health += value;
        healthText.setTextValue(health);
    }

    /**
     * @param milliSeconds
     * @author Tom Vloet
     * @since 06-JUN-2023
     * Sets canMove boolean to false so the player cannot move the simmer. Then starts the coolDown of the startFreezeCoolDown.
     * Also sets the speed to 0.
     */
    public void freezeMovement(int milliSeconds) {
        canMove = false;
        startFreezeCoolDown(milliSeconds);
        setSpeed(0);
    }

    /**
     * @param milliSeconds
     * @author Tom Vloet
     * @since 06-JUN-2023
     * Creates a timer which after milliSeconds, sets the canMove boolean to true and throws away the timer.
     */
    private void startFreezeCoolDown(int milliSeconds) {
        // Create and schedule the damage CoolDown timer
        freezeMovementTimer = new Timer();
        freezeMovementTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Reset the damage CoolDown flag after the CoolDown duration
                canMove = true;
                freezeMovementTimer.cancel();
            }
        }, milliSeconds);
    }
}
