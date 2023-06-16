package com.github.tvloet1.seacleaner.enums;

public enum HealthEnum {
    MINIMUM(0);

    private final int value;

    HealthEnum(int value) {
        this.value = value;
    }

    /**
     * @author Tom Vloet
     * @since 16-JUN-2023
     * Returns the value of the HealthEnum.
     */
    public int getValue() {
        return value;
    }
}
