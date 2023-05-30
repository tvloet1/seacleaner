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

public abstract class MenuButton extends TextEntity implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {
    protected SeaCleaner seaCleaner;
    public MenuButton(Coordinate2D initialLocation, String text, int fontSize, SeaCleaner seaCleaner) {
        super(initialLocation, text);
        setFill(Color.ORANGE);
        setFont(Font.font("Courier New", FontWeight.BOLD, fontSize));
        this.seaCleaner = seaCleaner;
    }

    /**
     * @author Tom Vloet
     * @since 30-MAY-2023
     * Executes the run method when the button is pressed.
     */
    @Override
    public void onMouseButtonPressed(MouseButton mouseButton, Coordinate2D coordinate2D) {
        run();
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Change the color of the text and the cursor display when the mouse enters the text.
     */
    @Override
    public void onMouseEntered() {
        setFill(Color.YELLOW);
        setCursor(Cursor.HAND);
    }

    /**
     * @author Tom Vloet
     * @since 23-APR-2023
     * Change the color of the text and the cursor display when the mouse exits the text.
     */
    @Override
    public void onMouseExited() {
        setFill(Color.ORANGE);
        setCursor(Cursor.DEFAULT);
    }

    /**
     * @author Tom Vloet
     * @since 30-MAY-2023
     * Determines what happens when the menu button is pressed.
     */
    public abstract void run();
}
