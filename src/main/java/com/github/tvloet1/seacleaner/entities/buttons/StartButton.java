package com.github.tvloet1.seacleaner.entities.buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.api.userinput.MouseEnterListener;
import com.github.hanyaeger.api.userinput.MouseExitListener;

import com.github.tvloet1.seacleaner.SeaCleaner;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StartButton extends TextEntity
		implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {

	private SeaCleaner seacleaner;

	public StartButton(Coordinate2D initialLocation, SeaCleaner seacleaner) {
		super(initialLocation, "Play game");
		setFill(Color.ORANGE);
		setFont(Font.font("Courier New", FontWeight.BOLD, 50));
		this.seacleaner = seacleaner;
	}

	/**
	 * @author Tom Vloet
	 * @since 04-APR-2023
	 * Change the color of the text when the mouse exits the text.
	 */
	@Override
	public void onMouseExited() {
		setFill(Color.ORANGE);
		setCursor(Cursor.DEFAULT);
	}

	/**
	 * @author Tom Vloet
	 * @since 04-APR-2023
	 * Change the color of the text when the mouse enters the text.
	 */
	@Override
	public void onMouseEntered() {
		setFill(Color.YELLOW);
		setCursor(Cursor.HAND);
	}

	/**
	 * @author Tom Vloet
	 * @since 04-APR-2023
	 * Play sound and move to next scene.
	 */
	@Override
	public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2d) {
		playSound();
		seacleaner.endMusicScene();
		seacleaner.setActiveScene(1);
	}

	/**
	 * @author Tom Vloet
	 * @since 23-APR-2023
	 * Instantiates sound-clip and plays it
	 */
	private void playSound() {
		if (seacleaner.getSoundEffectsOn()) {
			var startGameSound = new SoundClip("audio/soundStartGame.wav");
			startGameSound.play();
		}
	}

}
