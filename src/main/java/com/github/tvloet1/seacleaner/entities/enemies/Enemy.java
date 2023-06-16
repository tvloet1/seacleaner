package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.tvloet1.seacleaner.entities.SoundManager;
import com.github.tvloet1.seacleaner.entities.Swimmer;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Enemy extends DynamicSpriteEntity implements Collider {
    private final int damage;
    private final String soundClip;
    private boolean isAttackMoveCoolDown = false;
    private Timer attackMoveCoolDownTimer;
    private final int attackMoveCoolDownDuration;

    protected Enemy(String resource, Coordinate2D initialLocation, Size size, int damage, int attackMoveCoolDownDuration) {
        this(resource, initialLocation, size, 1, 1, damage, attackMoveCoolDownDuration, "audio/soundOuch.mp3");
    }

    protected Enemy(String resource, Coordinate2D initialLocation, Size size, int rows, int columns, int damage, int attackMoveCoolDownDuration) {
        this(resource, initialLocation, size, rows, columns, damage, attackMoveCoolDownDuration, "audio/soundOuch.mp3");
    }

    protected Enemy(String resource, Coordinate2D initialLocation, Size size, int damage, int attackMoveCoolDownDuration, String soundClip) {
        this(resource, initialLocation, size, 1, 1, damage, attackMoveCoolDownDuration, soundClip);
    }

    protected Enemy(String resource, Coordinate2D initialLocation, Size size, int rows, int columns, int damage, int attackMoveCoolDownDuration, String soundClip) {
        super(resource, initialLocation, size, rows, columns);
        this.damage = damage;
        this.attackMoveCoolDownDuration = attackMoveCoolDownDuration;
        this.soundClip = soundClip;
    }

    /**
     * @param swimmer This method is used to interact with the Swimmer. If the isAttackMoveCoolDown is false then the enemies attack move
     *                is carried out, followed by a soundClip, then damage is applied to the swimmer. Finally, the startAttackMoveCoolDown
     *                is started.
     * @author Tom Vloet
     * @since 05-JUN-2023
     */
    public void attack(Swimmer swimmer) {
        if (!isAttackMoveCoolDown) {
            attackMove();
            SoundManager.getInstance().playSound(soundClip);
            applyDamage(swimmer);
            startAttackMoveCoolDown();
        }
    }

    /**
     * @author Tom Vloet
     * @since 05-JUN-2023
     * This method is used in the attack method. It is abstract so that all classes extending from Enemy, must implement
     * their own version of attackMove.There is no default implementation of attackMove.
     */
    public abstract void attackMove();

    /**
     * @param swimmer This method calls the Swimmer's takeDamage method, and uses the Enemy's value for damage to subtract from the Swimmer's health.
     * @author Tom Vloet
     * @since 05-JUN-2023
     */
    public void applyDamage(Swimmer swimmer) {
        swimmer.takeDamage(damage);
    }

    /**
     * @author Tom Vloet
     * @since 05-JUN-2023
     * This method prevents damage from being done on every game world update. The isAttackMoveCoolDown is set to true, then
     * a timer is instantiated and scheduled to set isAttackMoveCoolDown back to false and cancel the timer after a specific
     * amount of milliseconds, stored in attackMoveCoolDownDuration.
     */
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
}
