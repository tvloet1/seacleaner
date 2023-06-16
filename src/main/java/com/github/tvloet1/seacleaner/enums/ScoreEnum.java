package com.github.tvloet1.seacleaner.enums;

public enum ScoreEnum {
    MAXIMUM(10);

    private final int value;

    ScoreEnum(int value) {
        this.value = value;
    }

    /**
     * @author Tom Vloet
     * @since 16-JUN-2023
     * Returns the value of the ScoreEnum.
     */
    public int getValue() {
        return value;
    }
}
