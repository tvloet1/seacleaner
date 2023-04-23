package com.github.tvloet1.seacleaner.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.api.userinput.MouseEnterListener;
import com.github.hanyaeger.api.userinput.MouseExitListener;
import com.github.tvloet1.seacleaner.SeaCleaner;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SoundEffectsButton extends TextEntity implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {
    private SeaCleaner seaCleaner;
    public SoundEffectsButton(Coordinate2D initialLocation, SeaCleaner seaCleaner) {
        super(initialLocation,"Sound effects: On");
        this.seaCleaner = seaCleaner;
        setFont(Font.font("Courier New", FontWeight.BOLD, 30));
        setFill(Color.ORANGE);
    }

    private void setSoundEffectsText() {
        String soundEffects;
        if(seaCleaner.getSoundEffectsOn()) {
            soundEffects = "on";
        } else {
            soundEffects = "off";
        }
        setText("Sound effects: " + soundEffects);
    }

    @Override
    public void onMouseButtonPressed(MouseButton mouseButton, Coordinate2D coordinate2D) {
        seaCleaner.switchSoundEffectsOn();
        setSoundEffectsText();
    }

    @Override
    public void onMouseExited() {
        setFill(Color.ORANGE);
        setCursor(Cursor.DEFAULT);
    }

    @Override
    public void onMouseEntered() {
        setFill(Color.YELLOW);
        setCursor(Cursor.HAND);
    }
}
