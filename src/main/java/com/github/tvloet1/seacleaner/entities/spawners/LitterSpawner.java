package com.github.tvloet1.seacleaner.entities.spawners;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.github.tvloet1.seacleaner.entities.Litter;

import java.util.Random;

public class LitterSpawner extends EntitySpawner {

    private final double sceneWidth;
    private final double sceneHeight;

    public LitterSpawner(double sceneWidth, double sceneHeight) {
        super(1000);
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    @Override
    protected void spawnEntities(){
        int val = new Random().nextInt(10);
        if(val==0){
            spawn(new Litter("sprites/litter/litterCan1.png",randomLocation(), 1));
        } else if (val==1) {
            spawn(new Litter("sprites/litter/litterCan2.png",randomLocation(), 1));
        }else if (val==2) {
            spawn(new Litter("sprites/litter/litterCan3.png",randomLocation(), 1));
        }else if (val==3) {
            spawn(new Litter("sprites/litter/litterCan4.png",randomLocation(), 1));
        }else if (val==4) {
            spawn(new Litter("sprites/litter/litterCan5.png",randomLocation(), 1));
        }else if (val==5) {
            spawn(new Litter("sprites/litter/litterCan6.png",randomLocation(), 1));
        }else if (val==6) {
            spawn(new Litter("sprites/litter/litterBottle1.png",randomLocation(), 1));
        }else if (val==7) {
            spawn(new Litter("sprites/litter/litterBottle2.png",randomLocation(), 1));
        }else if (val==8) {
            spawn(new Litter("sprites/litter/litterBottle3.png",randomLocation(), 1));
        }
    }

    private Coordinate2D randomLocation() {
        double x = new Random().nextInt((int) sceneWidth);
        return new Coordinate2D(x, 0);
    }
}
