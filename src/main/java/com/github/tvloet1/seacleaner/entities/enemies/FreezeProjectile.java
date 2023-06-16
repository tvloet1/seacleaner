package com.github.tvloet1.seacleaner.entities.enemies;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.tvloet1.seacleaner.entities.Swimmer;
import com.github.tvloet1.seacleaner.entities.modifiers.ModifySwimmer;

public class FreezeProjectile extends ProjectileEnemy implements ModifySwimmer {
    private int freezeDuration;

    public FreezeProjectile(Coordinate2D initialLocation, Size size, double direction, int freezeDuration) {
        super("sprites/enemies/bubbleBlue.png", initialLocation, size, 5, 1, 2, direction, "audio/soundFreeze.mp3");
        this.freezeDuration = freezeDuration;
    }

    @Override
    public void attackMove() {
        System.out.println("Do a FreezeProjectile thing");
        remove();
    }

    @Override
    public void modify(Swimmer swimmer) {
        swimmer.freezeMovement(freezeDuration);
    }
}
