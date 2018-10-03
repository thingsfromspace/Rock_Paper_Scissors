package gui.gui_elements.options_screen;

import gui.gui_elements.mdButton;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Options {
    private double screenWidth;
    private double screenHeight;
    private Font systemFont;
    private double padding;

    public Options(double screenWidth, double screenHeight, Font systemFont, double padding) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.systemFont = systemFont;
        this.padding = padding;
    }

    public static void toggle(mdButton button, boolean preference) {
        if (preference) {
            button.setButtonColor(Color.RED);
        } else {
            button.setButtonColor(Color.LIGHTGREEN);
        }
        button.timesClicked = 0;
    }

    public ArrayList<Node> getOptionScreen(Node parent, String[] toggleOptions) {
        ArrayList<Node> elements = new ArrayList<>(0);

        for (int i = 0; i < toggleOptions.length; i++) {
            double result = (i) * (1.0 / (((double) toggleOptions.length) * 3.5));
            String option = toggleOptions[i];
            mdButton optionButton = new mdButton(option, parent, 1000, 60, (screenWidth - 1000) / 2,
                    padding + (screenHeight - 2 * padding) * result);

            optionButton.setDropShadow();
            optionButton.setAnimation(true);
            optionButton.setButtonColor(Preferences.preferences[i] ? Color.LIGHTGREEN : Color.RED);
            elements.add(optionButton);
            elements.add(optionButton.addText(option, new Font(systemFont.getFamily(), 40), Color.BLACK));
        }

        // add a back button
        mdButton backButton = new mdButton("back", parent, 1000, 60, (screenWidth - 1000) / 2,
                screenHeight - padding - 60);
        backButton.setDropShadow();
        backButton.setAnimation(true);
        backButton.setHoverSoundON(Preferences.preferences[1]);
        backButton.setClickSoundON(Preferences.preferences[0]);
        backButton.setButtonColor(Color.LIGHTGRAY);
        elements.add(backButton);
        elements.add(backButton.addText("back", new Font(systemFont.getFamily(), 40), Color.BLACK));

        return elements;
    }
}
