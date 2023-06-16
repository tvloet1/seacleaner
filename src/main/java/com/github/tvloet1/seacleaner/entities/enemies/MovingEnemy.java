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

    protected void respawn() {
        int xVal = new Random().nextInt(2); // 50 50 change to be 0 or 1
        int yPos = new Random().nextInt((int) ((getSceneHeight()*0.8) + getSceneHeight()*0.2));
        double xPos;
        if(xVal == 0) {
            xPos = getSceneWidth();
            goRight();
        } else {
            xPos = 0 - getWidth() +1;
            goLeft();
        }
        setAnchorLocation(new Coordinate2D(xPos, yPos));
    }

    protected void turnAround() {
        if(goingLeft()) {
            goRight();
        } else {
            goLeft();
        }
    }

    protected boolean goingLeft() {
        return getDirection() == 90d;
    }

    protected void goLeft() {
        setDirection(90d);
        setCurrentFrameIndex(1);
    }

    protected void goRight() {
        setDirection(270d);
        setCurrentFrameIndex(0);
    }

    protected void goLeftOrRight() {
        int val = new Random().nextInt(2);
        if(val == 0) {
            goLeft();
        } else {
            goRight();
        }
    }

}
