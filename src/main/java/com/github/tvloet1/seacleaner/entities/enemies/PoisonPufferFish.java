package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.tvloet1.seacleaner.scenes.GameLevel;

public class PoisonPufferFish extends PufferFish {
    public PoisonPufferFish(Coordinate2D initialLocation, int damage, int speed, GameLevel gameLevel) {
        super("sprites/enemies/pufferFishGreen.png", initialLocation, damage, speed, gameLevel);
        fire();
    }

    @Override
    public void fire() {
        gameLevel.addEntityToCollection(new PoisonProjectile(getAnchorLocation(), new Size(25, 25), randomDegreesDirection()));
    }
}
