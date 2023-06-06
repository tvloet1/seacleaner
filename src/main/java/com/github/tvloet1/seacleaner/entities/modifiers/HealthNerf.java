package com.github.tvloet1.seacleaner.entities.modifiers;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.tvloet1.seacleaner.entities.Swimmer;

public class HealthNerf extends Modifier {
    public HealthNerf(Coordinate2D initialLocation, boolean soundEffectsOn, int modifierValue) {
        super("sprites/modifiers/healthNerf.png", initialLocation, "audio/speedNerf.mp3", soundEffectsOn, modifierValue);
    }
    @Override
    public void modify(Swimmer swimmer) {
        swimmer.adjustHealth(modifierValue);
    }
}
