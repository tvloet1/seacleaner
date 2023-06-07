package com.github.tvloet1.seacleaner.entities.modifiers;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.tvloet1.seacleaner.entities.SoundManager;
import com.github.tvloet1.seacleaner.entities.Swimmer;

public abstract class Modifier extends DynamicSpriteEntity implements Collided, Collider, SceneBorderCrossingWatcher, ModifySwimmer {

    protected int modifierValue;
    private final String soundClip;
    public Modifier(String resource, Coordinate2D initialLocation, String soundClip, int modifierValue) {
        super(resource, initialLocation, new Size(30, 60));
        this.soundClip = soundClip;
        this.modifierValue = modifierValue;
        setMotion(2,0d);
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Plays sound and removes the modifier
     */
    @Override
    public void onCollision(Collider collidingObject) {
        if(collidingObject instanceof Swimmer) {
            SoundManager.getInstance().playSound(soundClip);
            remove();
        }
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Removes the modifier when it crosses the screen border
     */
    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        remove();
    }

    public abstract void modify(Swimmer swimmer);
}
