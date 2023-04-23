package com.github.tvloet1.seacleaner.entities.spawners;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.github.tvloet1.seacleaner.entities.modifiers.SpeedBuff;
import com.github.tvloet1.seacleaner.entities.modifiers.SpeedNerf;

import java.util.Random;

public class ModifierSpawner extends EntitySpawner {
    private double sceneWidth;
    private final boolean SoundEffectsOn;
    public ModifierSpawner(double sceneWidth, boolean SoundEffectsOn) {
        super(3000);
        this.sceneWidth = sceneWidth;
        this.SoundEffectsOn = SoundEffectsOn;
    }

    @Override
    protected void spawnEntities() {
        int val = new Random().nextInt(2);
        if(val==0){
            spawn(new SpeedBuff(randomLocation(), SoundEffectsOn));
        } else if (val==1) {
            spawn(new SpeedNerf(randomLocation(), SoundEffectsOn));
        }
    }

    private Coordinate2D randomLocation() {
        double x = new Random().nextInt((int) sceneWidth);
        return new Coordinate2D(x, 0);
    }
}
