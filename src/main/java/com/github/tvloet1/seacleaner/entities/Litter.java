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
    private boolean soundEffects;
    public Litter(String resource, Coordinate2D initialLocation, int speed, int value, boolean soundEffects) {
        super(resource, initialLocation, new Size(30, 60));
        this.value = value;
        this.soundEffects = soundEffects;
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
            playSound();
            remove();
        }
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Returns the value of the piece of litter.
     */
    public int getValue() {
        return value;
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

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Instantiates sound-clip and plays it
     */
    private void playSound() {
        if (soundEffects) {
            var pickUpTrashSound = new SoundClip("audio/soundPickUpTrash.wav");
            pickUpTrashSound.play();
        }
    }
}
