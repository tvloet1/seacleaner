package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.tvloet1.seacleaner.scenes.GameLevel;


public class FirePufferFish extends PufferFish {
    public FirePufferFish(Coordinate2D initialLocation, int damage, int speed, GameLevel gameLevel) {
        super("sprites/enemies/pufferFishOrange.png", initialLocation, damage, speed, gameLevel);
        fire();
    }

    @Override
    public void fire() {
        gameLevel.addEntityToCollection(new FireProjectile(getAnchorLocation(), new Size(50, 50), randomDegreesDirection()));
    }
}
