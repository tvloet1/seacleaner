package com.github.tvloet1.seacleaner.entities.modifiers;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.tvloet1.seacleaner.entities.Swimmer;

public class HealthBuff extends Modifier {
    public HealthBuff(Coordinate2D initialLocation, int modifierValue) {
        super("sprites/modifiers/healthBuff.png", initialLocation, "audio/soundHeal.mp3", modifierValue);
    }

    @Override
    public void modify(Swimmer swimmer) {
        swimmer.adjustHealth(modifierValue);
    }
}
