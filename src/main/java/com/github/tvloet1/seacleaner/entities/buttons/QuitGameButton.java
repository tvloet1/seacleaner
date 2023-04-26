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

public class QuitGameButton extends TextEntity implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {

    private SeaCleaner seacleaner;

    public QuitGameButton(Coordinate2D initialLocation, SeaCleaner seacleaner) {
        super(initialLocation,"Quit game");
        setFill(Color.ORANGE);
        setFont(Font.font("Courier New", FontWeight.BOLD, 30));
        this.seacleaner = seacleaner;
    }
    /**
     * @author Tom Vloet
     * @since 16-APR-2023
     * Closes the application.
     */
    @Override
    public void onMouseButtonPressed(MouseButton mouseButton, Coordinate2D coordinate2D) {
        seacleaner.quit();
    }

    /**
     * @author Tom Vloet
     * @since 16-APR-2023
     * Change the color of the text when the mouse enters the text.
     */
    @Override
    public void onMouseEntered() {
        setFill(Color.YELLOW);
        setCursor(Cursor.HAND);
    }

    /**
     * @author Tom Vloet
     * @since 16-APR-2023
     * Change the color of the text when the mouse exits the text.
     */
    @Override
    public void onMouseExited() {
        setFill(Color.ORANGE);
        setCursor(Cursor.DEFAULT);
    }
}
