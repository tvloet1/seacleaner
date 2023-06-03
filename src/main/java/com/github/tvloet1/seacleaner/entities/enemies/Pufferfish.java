package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.tvloet1.seacleaner.entities.Swimmer;

public class Pufferfish extends CollidingEnemy  {
    public Pufferfish(Coordinate2D initialLocation, Swimmer swimmer, int damage, int speed) {
        super("sprites/enemies/Pufferfish.png", initialLocation, new Size(120,120), swimmer, damage, speed, 2000);
        this.damage = damage;
    }

    @Override
    public void attackMove() {
        System.out.println("I do "+ damage + " damage!");
        turnAround();
    }

}
