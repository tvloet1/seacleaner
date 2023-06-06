package com.github.tvloet1.seacleaner.entities.modifiers;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.tvloet1.seacleaner.entities.Swimmer;

public class SpeedBuff extends Modifier {
    public SpeedBuff(Coordinate2D initialLocation, boolean SoundEffectsOn, int modifierValue) {
        super("sprites/modifiers/speedBuff.png", initialLocation, "audio/speedBuff.mp3", SoundEffectsOn, modifierValue);
    }

    @Override
    public void modify(Swimmer swimmer) {
        swimmer.adjustSpeed(modifierValue);
    }
}
