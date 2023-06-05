package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class PoisonProjectile extends ProjectileEnemy {
    public PoisonProjectile(Coordinate2D initialLocation, Size size, double direction) {
        super("sprites/enemies/fishSkeleton.jpeg", initialLocation, size, 20, 1, 5, direction);
    }

    @Override
    public void attackMove() {
        System.out.println("Do a posionprojectile thing");
        remove();
    }
}
