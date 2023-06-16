package com.github.tvloet1.seacleaner.enums;

public enum SceneEnum {
    TITLE_SCENE(0),
    LEVEL1(1),
    END_SCENE_WIN(2),
    END_SCENE_LOSE(3);

    private final int value;

    /**
     * @author Tom Vloet
     * @since 16-JUN-2023
     * Returns the value of the SceneEnum.
     */
    SceneEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
