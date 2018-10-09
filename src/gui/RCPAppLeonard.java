package gui;

import gui.gui_elements.main_screen.MainScreenLeonard;
import gui.gui_elements.mdButtonLeonard;
import gui.gui_elements.options_screen.OptionsLeonard;
import gui.gui_elements.options_screen.PreferencesLeonard;
import gui.gui_elements.start_screen.Start;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * <h1>The Rock Paper Scissors Application</h1>
 * This is the entry point for the RCP app.
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class RCPAppLeonard extends Application {

    // screen dimensions
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    // Collections to hold buttons and button images
    public static ArrayList<mdButtonLeonard> buttons = new ArrayList<>(0);
    private static ArrayList<ImageView> buttonImages = new ArrayList<>(0);
    // boolean conditions used to keep track of game state
    private boolean startScreenShowing = true;
    // number of screen clicks
    private int clicks = 0;
    private boolean optionScreenShowing = false;
    private boolean mainScreenShowing = false;

    /**
     * Launches the RCPApp
     *
     * @param args command line arguments (not used in this program)
     */
    public static void main(String args[]) {
        launch(args);
    }

    /**
     * this method starts the application.
     *
     * @param primaryStage the stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {

        // create a new group to hold display elements
        Group displayGroup = new Group();

        // create a starting screen
        Start startingScreen = new Start(WIDTH, HEIGHT,
                Font.font("Europa"), "play", "options");

        // create an options screen
        OptionsLeonard optionScreen = new OptionsLeonard(WIDTH, HEIGHT,
                Font.font("Europa"), 50);

        // create a main screen
        MainScreenLeonard mainScreen = new MainScreenLeonard(Font.font("Europa"));

        // Adds a procedure for when the mouse is pressed on the screen
        displayGroup.setOnMousePressed(e -> {
            // loop through all buttons
            for (mdButtonLeonard button : buttons)
                loop:{
                    // if the click is inside the button
                    if (button.contains(e.getX(), e.getY())) {
                        // the button has been clicked
                        button.clicked();
                        // System.out.println(button);
                        clicks++;
                        break loop;
                    }
                }
        });

        // go to the start screen
        changeToStart(startingScreen, displayGroup);

        // create a new screen with the displayGroup elements
        Scene scene = new Scene(displayGroup, 1280, 720);

        // set the title, scene
        primaryStage.setTitle("Rock Paper Scissors");
        primaryStage.setScene(scene);

        // display the stage
        primaryStage.show();

        // this allows us to animate elements on the screen
        new AnimationTimer() {
            /**
             * This method handles screen events
             *
             * @param now timestamp of current frame
             */
            @Override
            public void handle(long now) {
                // loop through every button
                for (Node button : displayGroup.getChildren()) {
                    try {
                        // animate the button
                        ((mdButtonLeonard) button).animate();
                    } catch (ClassCastException e) {
                    }
                }

                // if the user has clicked the screen
                if (clicks > 0) {
                    // we need to deal with a new click

                    // if the start screen is showing
                    if (startScreenShowing) {
                        if (buttons.get(1).timesClicked > 0) {
                            // if the first (play) button has been clicked

                            // change to the main screen
                            changeToMain(mainScreen, displayGroup);

                            // update game state
                            startScreenShowing = false;
                            mainScreenShowing = true;
                        }
                        if (startScreenShowing && buttons.get(2).timesClicked > 0) {
                            // if the second (options) button has been clicked

                            // change to the options screen
                            changeToOptions(optionScreen, displayGroup);
                            optionScreenShowing = true;

                            // update game state
                            startScreenShowing = false;
                        }
                    } else if (optionScreenShowing) {
                        // the options screen is showing
                        if (buttons.get(0).timesClicked > 0) {
                            // if the first option was toggled, update the button and the preferences
                            OptionsLeonard.toggle(buttons.get(0), PreferencesLeonard.preferences[0]);
                            PreferencesLeonard.preferences[0] = !PreferencesLeonard.preferences[0];
                        } else if (buttons.get(1).timesClicked > 0) {
                            // if the second option was toggled, update the button and the preferences
                            OptionsLeonard.toggle(buttons.get(1), PreferencesLeonard.preferences[1]);
                            PreferencesLeonard.preferences[1] = !PreferencesLeonard.preferences[1];
                        } else if (buttons.get(2).timesClicked > 0) {
                            // if the third option was toggled, update the button and the preferences
                            OptionsLeonard.toggle(buttons.get(2), PreferencesLeonard.preferences[2]);
                            PreferencesLeonard.preferences[2] = !PreferencesLeonard.preferences[2];
                        } else if (buttons.get(3).timesClicked > 0) {
                            // if the back button was clicked

                            // change to the start screen
                            changeToStart(startingScreen, displayGroup);

                            // update the game state
                            startScreenShowing = true;
                            optionScreenShowing = false;
                        }
                    } else if (mainScreenShowing) {
                        // if the main screen is showing
                        if (buttons.get(buttons.size() - 1).timesClicked > 0) {
                            // if the back button was clicked

                            // change to the start screen
                            changeToStart(startingScreen, displayGroup);

                            // update the game state
                            mainScreenShowing = false;
                            startScreenShowing = true;
                        } else {
                            // otherwise, update the main screen

                            // clear all screen elements
                            displayGroup.getChildren().clear();

                            // refresh the screen and add all elements
                            for (Node node : mainScreen.refresh()) {
                                try {
                                    // add the elements to the display group
                                    displayGroup.getChildren().add(node);
                                } catch (IllegalArgumentException e) {
                                    // some of the nodes are added twice. I didn't have time to figure out why, but it doesn't really
                                    // matter. This catch statement essentially just skips the node if it is already present.
                                } catch (NullPointerException e) {
                                }
                            }
                        }
                    }

                    // reset the clicks back to zero
                    clicks = 0;
                }
            }
        }.start();
    }

    /**
     * This method changes to the options screen
     *
     * @param optionScreen the options screen you want to switch to
     * @param displayGroup the display group that holds all on-screen elements
     */
    private void changeToOptions(OptionsLeonard optionScreen, Group displayGroup) {
        // clear all on screen elements
        buttons.clear();
        buttonImages.clear();
        displayGroup.getChildren().clear();

        // loop through all elements in the new option screen
        for (Node element : optionScreen.getOptionScreen(PreferencesLeonard.preferenceNames)) {
            // add the element to the display group
            displayGroup.getChildren().add(element);
            if (element.getClass().equals(mdButtonLeonard.class)) {
                // if it is a button, add it to the collection of buttons
                buttons.add((mdButtonLeonard) element);
            }
            if (element.getClass().equals(ImageView.class)) {
                // if it is an image, add it to the collection of images
                buttonImages.add((ImageView) element);
            }
        }
    }

    /**
     * This method changes to the start screen
     * @param startScreen the start screen you want to switch to
     * @param displayGroup the display group that holds all on-screen elements
     */
    private void changeToStart(Start startScreen, Group displayGroup) {
        buttons.clear();
        buttonImages.clear();
        displayGroup.getChildren().clear();

        // loop through all elements in the new start screen
        for (Node element : startScreen.getStartScreen()) {
            try {
                // add the element to the display group
                displayGroup.getChildren().add(element);
                if (element.getClass().equals(mdButtonLeonard.class)) {
                    // if it is a button, add it to the collection of buttons
                    buttons.add((mdButtonLeonard) element);
                }
                if (element.getClass().equals(ImageView.class)) {
                    // if it is an image, add it to the collection of images
                    buttonImages.add((ImageView) element);
                }
            } catch (NullPointerException e) {
            }
        }

    }

    /**
     * This method changes to the main screen
     *
     * @param mainScreen   the main screen you want to switch to
     * @param displayGroup the display group that holds all on-screen elements
     */
    private void changeToMain(MainScreenLeonard mainScreen, Group displayGroup) {
        buttons.clear();
        buttonImages.clear();
        displayGroup.getChildren().clear();

        // loop through all elements in the new main screen
        for (Node element : mainScreen.getMainScreen()) {
            try {
                // add the element to the display group
                displayGroup.getChildren().add(element);
            } catch (Exception e) {
                // some of the nodes are added twice. I didn't have time to figure out why, but it doesn't really
                // matter. This catch statement essentially just skips the node if it is already present.
            }
            if (element.getClass().equals(mdButtonLeonard.class)) {
                // if it is a button, add it to the collection of buttons
                buttons.add((mdButtonLeonard) element);
            }
            if (element.getClass().equals(ImageView.class)) {
                // if it is an image, add it to the collection of images
                buttonImages.add((ImageView) element);
            }
        }
    }
}
