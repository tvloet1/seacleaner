package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;

public class PoisonPufferFish extends PufferFish implements CanFire{
    public PoisonPufferFish(Coordinate2D initialLocation, int damage, int speed) {
        super(initialLocation, damage, speed);
        fire();
    }

    @Override
    public void fire() {
        System.out.println("Pew pew!");
//        new ProjectileEnemy("sprites/enemies/fishSkeleton.png", getAnchorLocation(), 40, 1500, 5, 90d);
    }
}
