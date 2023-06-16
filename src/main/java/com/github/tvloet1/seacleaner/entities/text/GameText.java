package com.github.tvloet1.seacleaner.entities.text;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameText extends TextEntity {
    private String text;

    public GameText(Coordinate2D initialLocation, String text) {
        super(initialLocation, text);
        this.text = text;
        setFont(Font.font("Courier New", FontWeight.BOLD, 30));
        setFill(Color.ORANGE);
    }
    /**
     * @param value
     * @author Tom Vloet
     * @since 03-JUN-2023
     * Concatenates the text string with the parameter value and displays this on the screen.
     */
    public void setTextValue(int value) {
        setText(text + ": " + value);
    }
}
