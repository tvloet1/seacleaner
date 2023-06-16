package com.github.tvloet1.seacleaner.entities.modifiers;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.tvloet1.seacleaner.entities.Swimmer;

public class SpeedModifier extends Modifier {
    public SpeedModifier(String resource, Coordinate2D initialLocation, String soundClip, int modifierValue) {
        super(resource, initialLocation, soundClip, modifierValue);
    }

    @Override
    public void modify(Swimmer swimmer) {
        swimmer.adjustSpeed(modifierValue);
    }
}
