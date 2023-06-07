package com.github.tvloet1.seacleaner.entities.spawners;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.github.tvloet1.seacleaner.entities.modifiers.HealthBuff;
import com.github.tvloet1.seacleaner.entities.modifiers.HealthNerf;
import com.github.tvloet1.seacleaner.entities.modifiers.SpeedBuff;
import com.github.tvloet1.seacleaner.entities.modifiers.SpeedNerf;

import java.util.Random;

public class ModifierSpawner extends EntitySpawner {
    private double sceneWidth;
    public ModifierSpawner(double sceneWidth) {
        super(3000);
        this.sceneWidth = sceneWidth;
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Spawns Modifiers at random x position, but always 0 y position.
     */
    @Override
    protected void spawnEntities() {
        int val = new Random().nextInt(4);
        if(val==0){
            spawn(new SpeedBuff(randomLocation(),1));
        } else if (val==1) {
            spawn(new SpeedNerf(randomLocation(),-1));
        } else if (val==2) {
            spawn(new HealthBuff(randomLocation(),60));
        } else if (val==3) {
            spawn(new HealthNerf(randomLocation(),-60));
        }
    }

    /**
     * @author Tom Vloet
     * @return Coordinate2D x and y coordinate
     * @since 23-APR-2023
     * Fixed y position of 0 and a random x position (within the boundaries of the screenwidth)
     */
    private Coordinate2D randomLocation() {
        double x = new Random().nextInt((int) sceneWidth);
        return new Coordinate2D(x, 0);
    }
}
