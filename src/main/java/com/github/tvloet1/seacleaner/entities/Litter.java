package com.github.tvloet1.seacleaner.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.SceneBorder;

public class Litter extends DynamicSpriteEntity implements Collided, Collider, SceneBorderCrossingWatcher {
    private int value;
    public Litter(String resource, Coordinate2D initialLocation, int speed, int value) {
        super(resource, initialLocation, new Size(30, 60));
        this.value = value;
        setMotion(speed,0d);
    }

    @Override
    public void onCollision(Collider collidingObject) {
        if(collidingObject instanceof Swimmer) {
            var pickUpTrashSound = new SoundClip("audio/soundPickUpTrash.wav");
            pickUpTrashSound.play();
            remove();
        }
    }

    public int getValue() {
        return value;
    }
    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        remove();
    }
}
