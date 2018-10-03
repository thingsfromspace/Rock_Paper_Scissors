package gui.start_screen;

import gui.gui_elements.mdButton;
import gui.gui_elements.options_screen.Preferences;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

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
    private double screenWidth;
    private double screenHeight;
    private Font buttonFont;
    private String[] buttonText;
    private int buttons = 0;

    /**
     * Constructs the Start Screen
     *
     * @param buttonFont the font of the text for the buttons
     * @param buttonText The text for each button on the start screen
     */
    public Start(double screenWidth, double screenHeight, Font buttonFont, String... buttonText) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.buttonFont = buttonFont;
        this.buttonText = buttonText;
        this.buttons = buttonText.length;
    }

    @SuppressWarnings({"SameReturnValue", "unused"})
    public ArrayList<Node> getStartScreen(Node parent, Color button1Color, Color button2Color) {
        ArrayList<Node> elements = new ArrayList<>(0);

        // create a "button" for the title
        mdButton titleButton = new mdButton("title", parent, 1000, 250, (screenWidth - 1000) / 2,
                50);
        titleButton.setAnimation(false);
        titleButton.setButtonColor(Color.LIGHTPINK);
        titleButton.isButton = false;
        elements.add(titleButton);
        elements.add(titleButton.addText("[Title Image]",
                new Font(buttonFont.getFamily(), 150), Color.BLACK));

        // create a play button and an options button
        mdButton playButton = new mdButton("play", parent, 800, 75, (screenWidth - 800) / 2, 405);
        playButton.setDropShadow();
        playButton.setAnimation(true);
        elements.add(playButton);
        elements.add(playButton.addText(buttonText[0], new Font(buttonFont.getFamily(), 50), Color.BLACK));

        mdButton optionButton = new mdButton("option", parent, 800, 75, (screenWidth - 800) / 2, 530);
        optionButton.setDropShadow();
        optionButton.setAnimation(true);
        optionButton.setClickSoundON(Preferences.preferences[0]);
        optionButton.setHoverSoundON(Preferences.preferences[1]);
        elements.add(optionButton);
        elements.add(optionButton.addText(buttonText[1], new Font(buttonFont.getFamily(), 50), Color.BLACK));

        return elements;
    }
}
