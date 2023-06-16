package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.scenes.SceneBorder;

import java.util.Random;

public abstract class MovingEnemy extends Enemy implements SceneBorderCrossingWatcher {

    protected MovingEnemy(String resource, Coordinate2D initialLocation, Size size, int damage, int attackMoveCoolDownDuration, int speed) {
        super(resource, initialLocation, size, 1, 2, damage, attackMoveCoolDownDuration);
        setSpeed(speed);
        goLeftOrRight();
    }

    protected MovingEnemy(String resource, Coordinate2D initialLocation, Size size, int damage, int attackMoveCoolDownDuration, int speed, String soundClip) {
        super(resource, initialLocation, size, 1, 2, damage, attackMoveCoolDownDuration, soundClip);
        setSpeed(speed);
        goLeftOrRight();
    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        respawn();
    }

    /**
     * @author Tom Vloet
     * @since 05-JUN-2023
     * Spawns the MovingEnemy either on the left of the screen going right or on the right of the screen going left.
     * The height is anywhere within the boundaries of the screen.
     */
    protected void respawn() {
        int xVal = new Random().nextInt(2); // 50 50 change to be 0 or 1
        int yPos = new Random().nextInt((int) (getSceneHeight()));
        double xPos;
        if (xVal == 0) {
            xPos = getSceneWidth();
            goRight();
        } else {
            xPos = 0 - getWidth() + 1;
            goLeft();
        }
        setAnchorLocation(new Coordinate2D(xPos, yPos));
    }

    /**
     * @author Tom Vloet
     * @since 05-JUN-2023
     * Changes the direction the MovingEnemy is heading from left to right or vice versa.
     */
    protected void turnAround() {
        if (goingLeft()) {
            goRight();
        } else {
            goLeft();
        }
    }

    /**
     * @author Tom Vloet
     * @since 05-JUN-2023
     * Returns true if the MovingEnemy is going left.
     */
    protected boolean goingLeft() {
        return getDirection() == 90d;
    }

    /**
     * @author Tom Vloet
     * @since 05-JUN-2023
     * Changes the MovingEnemy direction to left. Also changes frameIndex accordingly.
     */
    protected void goLeft() {
        setDirection(90d);
        setCurrentFrameIndex(1);
    }

    /**
     * @author Tom Vloet
     * @since 05-JUN-2023
     * Changes the MovingEnemy direction to right. Also changes frameIndex accordingly.
     */
    protected void goRight() {
        setDirection(270d);
        setCurrentFrameIndex(0);
    }

    /**
     * @author Tom Vloet
     * @since 05-JUN-2023
     * Changes the MovingEnemy's direction to either right or left at random.
     */
    protected void goLeftOrRight() {
        int val = new Random().nextInt(2);
        if (val == 0) {
            goLeft();
        } else {
            goRight();
        }
    }

}
