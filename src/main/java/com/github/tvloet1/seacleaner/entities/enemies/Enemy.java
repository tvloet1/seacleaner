package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.tvloet1.seacleaner.entities.SoundManager;
import com.github.tvloet1.seacleaner.entities.Swimmer;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Enemy extends DynamicSpriteEntity implements Collider {
    private final int damage;
    private boolean isAttackMoveCoolDown = false;
    private Timer attackMoveCoolDownTimer;
    private final int attackMoveCoolDownDuration;
    private String soundClip = "audio/soundOuch.mp3";
    private final boolean soundEffectsOn = true;
    protected Enemy(String resource, Coordinate2D initialLocation, Size size, int damage, int attackMoveCoolDownDuration) {
        super(resource, initialLocation, size);
        this.damage = damage;
        this.attackMoveCoolDownDuration = attackMoveCoolDownDuration;
    }
    protected Enemy(String resource, Coordinate2D initialLocation, Size size, int rows, int columns, int damage, int attackMoveCoolDownDuration) {
        super(resource, initialLocation, size, rows, columns);
        this.damage = damage;
        this.attackMoveCoolDownDuration = attackMoveCoolDownDuration;
    }

    public void attack(Swimmer swimmer) {
        if(!isAttackMoveCoolDown){
            attackMove();
            playSound();
            applyDamage(swimmer);
            startAttackMoveCoolDown();
        }
    };
    public abstract void attackMove();
    public void applyDamage(Swimmer swimmer) {
        swimmer.takeDamage(damage);
    }
    private void startAttackMoveCoolDown() {
        isAttackMoveCoolDown = true;
        // Create and schedule the damage CoolDown timer
        attackMoveCoolDownTimer = new Timer();
        attackMoveCoolDownTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Reset the damage CoolDown flag after the CoolDown duration
                isAttackMoveCoolDown = false;
                attackMoveCoolDownTimer.cancel();
            }
        }, attackMoveCoolDownDuration);
    }
    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Instantiates sound-clip and plays it
     */
    private void playSound() {
        System.out.println("enemy " + SoundManager.getInstance().isSoundEffectsOn());
        if (SoundManager.getInstance().isSoundEffectsOn()) {
            var sound = new SoundClip(soundClip);
            sound.play();
        }
    }
}
