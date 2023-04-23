package com.github.tvloet1.seacleaner.entities.modifiers;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.tvloet1.seacleaner.entities.Swimmer;

public abstract class Modifier extends DynamicSpriteEntity implements Modify, Collided, Collider, SceneBorderCrossingWatcher {
    private final String soundClip;
    private final boolean soundEffectsOn;
    public Modifier(String resource, Coordinate2D initialLocation, String soundClip, boolean soundEffectsOn) {
        super(resource, initialLocation, new Size(30, 60));
        this.soundClip = soundClip;
        this.soundEffectsOn = soundEffectsOn;
        setMotion(2,0d);
    }

    @Override
    public void onCollision(Collider collidingObject) {
        if(collidingObject instanceof Swimmer) {
            playSound();
            remove();
        }
    }
    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        remove();
    }

    private void playSound() {
        if (soundEffectsOn) {
            var pickUpTrashSound = new SoundClip(soundClip);
            pickUpTrashSound.play();
        }
    }
}
