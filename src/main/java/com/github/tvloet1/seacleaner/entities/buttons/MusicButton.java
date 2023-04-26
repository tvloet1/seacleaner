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

public class MusicButton extends TextEntity implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {
    private SeaCleaner seaCleaner;
    public MusicButton(Coordinate2D initialLocation, SeaCleaner seaCleaner) {
        super(initialLocation,"Music: On");
        this.seaCleaner = seaCleaner;
        setFont(Font.font("Courier New", FontWeight.BOLD, 30));
        setFill(Color.ORANGE);
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Change the display text for music on to music of and vice versa.
     */
    private void setMusicText() {
        String music;
        if(seaCleaner.getMusicOn()) {
            music = "on";
        } else {
            music = "off";
        }
        setText("Music: " + music);
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Switches the music on or off and updates the diplay.
     */
    @Override
    public void onMouseButtonPressed(MouseButton mouseButton, Coordinate2D coordinate2D) {
        seaCleaner.switchMusic();
        setMusicText();
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Change the color of the text when the mouse exits the text.
     */
    @Override
    public void onMouseExited() {
        setFill(Color.ORANGE);
        setCursor(Cursor.DEFAULT);
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Change the color of the text when the mouse enters the text.
     */
    @Override
    public void onMouseEntered() {
        setFill(Color.YELLOW);
        setCursor(Cursor.HAND);
    }
}
