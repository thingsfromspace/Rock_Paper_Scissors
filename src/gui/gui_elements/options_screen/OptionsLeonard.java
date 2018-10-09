package gui.gui_elements.options_screen;

import gui.gui_elements.mdButtonLeonard;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * <h1>An options screen</h1>
 * This class is used to provide the user with
 * options to customize the app experience
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class OptionsLeonard {
    // the screen width
    private double screenWidth;
    // the screen height
    private double screenHeight;
    // the system font
    private Font systemFont;
    // the amount of padding (in pixels) around the border of the screen
    private double padding;

    /**
     * This creates the options screen object.
     *
     * @param screenWidth  the screen width
     * @param screenHeight the screen height
     * @param systemFont   the system font
     * @param padding      the amount of padding (in pixels) around the border of the screen
     */
    public OptionsLeonard(double screenWidth, double screenHeight, Font systemFont, double padding) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.systemFont = systemFont;
        this.padding = padding;
    }

    /**
     * This method toggles a specific option (e.g. if it is currently on, it turns it off)
     *
     * @param button     the option button
     * @param preference the current preference setting for the option
     */
    public static void toggle(mdButtonLeonard button, boolean preference) {
        // if the preference is currently set to true
        if (preference) {
            // set the color to red
            button.setButtonColor(Color.RED);
        } else {
            // set the color to green
            button.setButtonColor(Color.LIGHTGREEN);
        }

        button.timesClicked = 0;
    }

    /**
     * This creates a new option screen.
     *
     * @param toggleOptions descriptions of each option
     * @return an array list of elements that comprise the option screen
     */
    public ArrayList<Node> getOptionScreen(String[] toggleOptions) {
        // create a new array to hold elements
        ArrayList<Node> elements = new ArrayList<>(0);

        // loop through each option
        for (int i = 0; i < toggleOptions.length; i++) {
            // this is used to calculate how much to shift the y by
            double yShift = (i) * (1.0 / (((double) toggleOptions.length) * 2.5));
            // obtain an option description
            String option = toggleOptions[i];

            // CREATE A NEW OPTION BUTTON
            mdButtonLeonard optionButton = new mdButtonLeonard(option, 1000, 60, (screenWidth - 1000) / 2,
                    padding + (screenHeight - 2 * padding) * yShift);
            optionButton.setDropShadow();
            optionButton.setAnimation(true);
            optionButton.setButtonColor(PreferencesLeonard.preferences[i] ? Color.LIGHTGREEN : Color.RED);

            // add this to the array of elements
            elements.add(optionButton);
            elements.add(optionButton.addText(option, new Font(systemFont.getFamily(), 40), Color.BLACK));
        }

        // CREATE A BACK BUTTON
        mdButtonLeonard backButton = new mdButtonLeonard("back", 1000, 60, (screenWidth - 1000) / 2,
                screenHeight - padding - 60);
        backButton.setDropShadow();
        backButton.setAnimation(true);
        backButton.setHoverSoundON(PreferencesLeonard.preferences[1]);
        backButton.setClickSoundON(PreferencesLeonard.preferences[0]);
        backButton.setButtonColor(Color.LIGHTGRAY);

        // add this to the elements array
        elements.add(backButton);
        elements.add(backButton.addText("back", new Font(systemFont.getFamily(), 40), Color.BLACK));

        // return the array of elements
        return elements;
    }
}
