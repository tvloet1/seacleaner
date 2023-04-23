package com.github.tvloet1.seacleaner.entities.modifiers;

import com.github.hanyaeger.api.Coordinate2D;

public class SpeedNerf extends Modifier {
    public SpeedNerf(Coordinate2D initialLocation) {
        super("sprites/modifiers/speedNerf.png", initialLocation, "audio/speedNerf.mp3");
    }

    @Override
    public int execute() {
        return -1;
    }
}
