package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.scenes.SceneBorder;

public abstract class ProjectileEnemy extends Enemy implements SceneBorderCrossingWatcher {
    public ProjectileEnemy(String resource, Coordinate2D initialLocation, Size size, int damage, int attackMoveCoolDownDuration, int speed, double direction) {
        super(resource, initialLocation, size, damage, attackMoveCoolDownDuration);
        setMotion(speed,direction);
    }
    public ProjectileEnemy(String resource, Coordinate2D initialLocation, Size size, int damage, int attackMoveCoolDownDuration, int speed, double direction, String soundClip) {
        super(resource, initialLocation, size, damage, attackMoveCoolDownDuration, soundClip);
        setMotion(speed,direction);
    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        remove();
    }
}
