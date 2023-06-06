package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.tvloet1.seacleaner.scenes.GameLevel;

import java.util.Random;

public abstract class PufferFish extends MovingEnemy implements CanFire {
    protected GameLevel gameLevel;

    public PufferFish(String resource, Coordinate2D initialLocation, int damage, int speed, GameLevel gameLevel) {
        super(resource, initialLocation, new Size(120,120), damage, 2000, speed);
        this.gameLevel = gameLevel;
    }

    @Override
    public void attackMove() {
        turnAround();
    }

    @Override
    public abstract void fire();

    double randomDegreesDirection() {
        return new Random().nextInt(361);
    }
}
