package gui.gui_elements.start_screen;

import gui.FilePaths;
import gui.gui_elements.mdButtonLeonard;
import gui.gui_elements.options_screen.PreferencesLeonard;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
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
    // the screen width
    private double screenWidth;
    // the screen height
    private double screenHeight;
    // the font used
    private Font buttonFont;
    // the texts of each button
    private String[] buttonText;
    // the number of buttons
    private int buttons = 0;

    // filepaths
    private FilePaths paths = new FilePaths();

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

    /**
     * This creates a new start screen
     *
     * @return a collection of nodes comprising the start screen
     */
    public ArrayList<Node> getStartScreen() {
        // a collection to hold all start screen elements
        ArrayList<Node> elements = new ArrayList<>(0);

        // the scale for the title image
        double titleScale = 0.28;

        // CREATE A TITLE BUTTON
        mdButtonLeonard titleButton = new mdButtonLeonard("title", 1000, 250, (screenWidth - 1000) / 2,
                50);
        titleButton.setAnimation(false);
        titleButton.setButtonColor(Color.LIGHTPINK);
        titleButton.isButton = false;
        elements.add(titleButton);
        elements.add(titleButton.addImage(new File(paths.paths[2]).getAbsolutePath(),
                titleScale, titleScale));

        // CREATE A PLAY BUTTON
        mdButtonLeonard playButton = new mdButtonLeonard("play", 800, 75, (screenWidth - 800) / 2, 405);
        playButton.setDropShadow();
        playButton.setAnimation(true);
        elements.add(playButton);
        elements.add(playButton.addText(buttonText[0], new Font(buttonFont.getFamily(), 50), Color.BLACK));

        // CREATE AN OPTIONS BUTTON
        mdButtonLeonard optionButton = new mdButtonLeonard("option", 800, 75, (screenWidth - 800) / 2, 530);
        optionButton.setDropShadow();
        optionButton.setAnimation(true);
        optionButton.setClickSoundON(PreferencesLeonard.preferences[0]);
        optionButton.setHoverSoundON(PreferencesLeonard.preferences[1]);
        elements.add(optionButton);
        elements.add(optionButton.addText(buttonText[1], new Font(buttonFont.getFamily(), 50), Color.BLACK));

        // return all of the elements
        return elements;
    }
}
