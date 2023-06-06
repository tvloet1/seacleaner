package com.github.tvloet1.seacleaner.entities.modifiers;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.tvloet1.seacleaner.entities.Swimmer;

public class HealthBuff extends Modifier {
    public HealthBuff(Coordinate2D initialLocation, boolean soundEffectsOn, int modifierValue) {
        super("sprites/modifiers/healthBuff.png", initialLocation, "audio/speedBuff.mp3", soundEffectsOn, modifierValue);
    }

    @Override
    public void modify(Swimmer swimmer) {
        System.out.println("modifierValue: " + modifierValue);
        swimmer.adjustHealth(modifierValue);
    }
}
