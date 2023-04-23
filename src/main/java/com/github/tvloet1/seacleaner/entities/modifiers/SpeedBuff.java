package com.github.tvloet1.seacleaner.entities.modifiers;

import com.github.hanyaeger.api.Coordinate2D;

public class SpeedBuff extends Modifier {
    public SpeedBuff(Coordinate2D initialLocation, boolean SoundEffectsOn) {
        super("sprites/modifiers/speedBuff.png", initialLocation, "audio/speedBuff.mp3", SoundEffectsOn);
    }

    @Override
    public int execute() {
        return 1;
    }
}
