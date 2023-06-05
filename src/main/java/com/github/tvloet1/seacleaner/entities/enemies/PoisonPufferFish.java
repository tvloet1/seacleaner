package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.tvloet1.seacleaner.scenes.GameLevel;

import java.util.Random;

public class PoisonPufferFish extends PufferFish implements CanFire{
    private GameLevel gameLevel;
    public PoisonPufferFish(Coordinate2D initialLocation, int damage, int speed, GameLevel gameLevel) {
        super("sprites/enemies/PufferFish.jpeg", initialLocation, damage, speed);
        this.gameLevel = gameLevel;
        fire();
    }

    @Override
    public void fire() {
        System.out.println("Pew pew!");
        gameLevel.addEntityToCollection(new PoisonProjectile(getAnchorLocation(), new Size(50,25), randomDirection()));
        gameLevel.addEntityToCollection(new FireProjectile(getAnchorLocation(), new Size(30,30), randomDirection()));
    }

    double randomDirection() {
        return new Random().nextInt(181);
    }
}
