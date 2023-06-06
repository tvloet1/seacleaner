package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class FireProjectile extends ProjectileEnemy {
    public FireProjectile(Coordinate2D initialLocation, Size size, double direction) {
        super("sprites/enemies/bubbleRed.png", initialLocation, size, 50, 1, 3, direction);
    }

    @Override
    public void attackMove() {
        System.out.println("Do a fireProjectile thing");
        remove();
    }
}
