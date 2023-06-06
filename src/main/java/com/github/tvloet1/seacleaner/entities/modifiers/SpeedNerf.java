package com.github.tvloet1.seacleaner.entities.modifiers;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.tvloet1.seacleaner.entities.Swimmer;

public class SpeedNerf extends Modifier {
    public SpeedNerf(Coordinate2D initialLocation, boolean SoundEffectsOn, int modifierValue) {
        super("sprites/modifiers/speedNerf.png", initialLocation, "audio/speedNerf.mp3", SoundEffectsOn, modifierValue);
    }

    @Override
    public void modify(Swimmer swimmer) {
        swimmer.adjustSpeed(modifierValue);
    }
}
