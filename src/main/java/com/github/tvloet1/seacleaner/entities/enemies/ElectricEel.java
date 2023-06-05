package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class ElectricEel extends MovingEnemy {
    public ElectricEel(Coordinate2D initialLocation, int damage, int speed) {
        super("sprites/enemies/electricEel.png", initialLocation, new Size(150,20), damage, 1000, speed);
    }

    @Override
    public void attackMove() {
        respawn();
    }
}
