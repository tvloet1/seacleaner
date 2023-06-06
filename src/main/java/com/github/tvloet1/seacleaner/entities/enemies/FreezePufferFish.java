package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.tvloet1.seacleaner.scenes.GameLevel;

public class FreezePufferFish extends PufferFish {
    public FreezePufferFish(Coordinate2D initialLocation, int damage, int speed, GameLevel gameLevel) {
        super("sprites/enemies/pufferFishGreen.png", initialLocation, damage, speed, gameLevel);
        fire();
    }

    @Override
    public void fire() {
        System.out.println("Pew pew!");
        gameLevel.addEntityToCollection(new FreezeProjectile(getAnchorLocation(), new Size(25,25), randomDegreesDirection(), 1000));
    }
}
