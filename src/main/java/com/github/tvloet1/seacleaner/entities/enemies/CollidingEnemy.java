package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.tvloet1.seacleaner.entities.Swimmer;

import java.util.Random;

public abstract class CollidingEnemy extends DynamicSpriteEntity implements Collider, SceneBorderCrossingWatcher {
    protected int damage;
    protected Swimmer swimmer;

    protected CollidingEnemy(String resource, Coordinate2D initialLocation, Size size, Swimmer swimmer, int damage, int speed) {
        super(resource, initialLocation, size, 1, 2);
        this.damage = damage;
        this.swimmer = swimmer;
        setSpeed(speed);
        goLeftOrRight();
    }
    public abstract void attack(Swimmer swimmer);
    public void applyDamage(Swimmer swimmer) {
        swimmer.takeDamage(damage);
    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        respawn();
    }

    protected void respawn() {
        int xVal = new Random().nextInt(2);
        int yPos = new Random().nextInt((int) (getSceneHeight()*0.8));
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
