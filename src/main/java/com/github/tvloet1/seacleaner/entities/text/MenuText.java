package com.github.tvloet1.seacleaner.entities.text;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuText extends TextEntity {
    public MenuText(Coordinate2D initialLocation, String text, int fontSize) {
        super(initialLocation, text);
        setAnchorPoint(AnchorPoint.CENTER_CENTER);
        setFill(Color.ORANGE);
        setFont(Font.font("Courier New", FontWeight.BOLD, fontSize));
    }
}
