package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.tvloet1.seacleaner.entities.Swimmer;

public class ElectricEel extends CollidingEnemy {
    public ElectricEel(Coordinate2D initialLocation, Swimmer swimmer, int damage, int speed) {
        super("sprites/enemies/electricEel.png", initialLocation, new Size(150,20), swimmer, damage, speed, 1000);
    }

    @Override
    public void attackMove() {
        System.out.println("I do "+ damage + " damage!");
        respawn();
    }
}
