package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class PufferFish extends MovingEnemy {

    public PufferFish(Coordinate2D initialLocation, int damage, int speed) {
        super("sprites/enemies/PufferFish.png", initialLocation, new Size(120,120), damage, 2000, speed);
    }

    @Override
    public void attackMove() {
        turnAround();
    }

}
