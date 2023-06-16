package com.github.tvloet1.seacleaner.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.tvloet1.seacleaner.entities.modifiers.ModifySwimmer;

public class Litter extends DynamicSpriteEntity implements Collided, Collider, SceneBorderCrossingWatcher, ModifySwimmer {
    private int points;
    public Litter(String resource, Coordinate2D initialLocation, int speed, int points) {
        super(resource, initialLocation, new Size(30, 60));
        this.points = points;
        setMotion(speed,0d);
    }

    /**
     * @author Tom Vloet
     * @since 16-APR-2023
     * Plays sound and removes the litter
     */
    @Override
    public void onCollision(Collider collidingObject) {
        if(collidingObject instanceof Swimmer) {
            SoundManager.getInstance().playSound("audio/soundPickUpTrash.wav");
            remove();
        }
    }

    /**
     * @author Tom Vloet
     * @since 16-APR-2023
     * Removes the modifier when it crosses the screen border
     */
    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        remove();
    }

    @Override
    public void modify(Swimmer swimmer) {
        swimmer.increaseScore(points);
    }
}
