package gui.start_screen;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * <h1>Creates the start screen</h1>
 * This class creates a simple start screen
 * with a list of buttons that the user can
 * select to proceed in the game.
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
@SuppressWarnings("ALL")
public class Start {
    @SuppressWarnings({"FieldCanBeLocal", "CanBeFinal", "unused"})
    private Font buttonFont;
    @SuppressWarnings({"FieldCanBeLocal", "CanBeFinal"})
    private String[] buttonText;
    private int buttons = 0;

    /**
     * Constructs the Start Screen
     *
     * @param buttonFont the font of the text for the buttons
     * @param buttonText The text for each button on the start screen
     */
    public Start(Font buttonFont, String... buttonText) {
        this.buttonFont = buttonFont;
        this.buttonText = buttonText;
    }

    @SuppressWarnings({"SameReturnValue", "unused"})
    public Parent getStartScreen(Color backgroundColor, Color buttonColor) {
        return null;
    }
}
