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
    public Litter(String resource, Coordinate2D initialLocation, int speed) {
        super(resource, initialLocation, new Size(30, 60));
        setMotion(speed,0d);
    }

    @Override
    public void onCollision(Collider collidingObject) {
        if(collidingObject instanceof Swimmer) {
            var popSound = new SoundClip("audio/pop.mp3");
            popSound.play();
            remove();
        }
    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        remove();
    }
}
