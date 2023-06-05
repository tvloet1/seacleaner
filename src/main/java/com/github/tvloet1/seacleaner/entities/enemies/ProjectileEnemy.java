package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.core.entities.EntityCollection;

public class ProjectileEnemy extends Enemy implements SceneBorderCrossingWatcher {
    public ProjectileEnemy(String resource, Coordinate2D initialLocation, int damage, int attackMoveCoolDownDuration, int speed, double direction) {
        super(resource, initialLocation, damage, attackMoveCoolDownDuration);
        setMotion(speed,direction);
    }

    @Override
    public void attackMove() {
        remove();
    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        remove();
    }
}
