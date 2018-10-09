package gui.gui_elements.Testing;

import gui.gui_elements.mdButtonLeonard;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

/**
 * <h1>This class was used for testing the mdButton class</h1>
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class ButtonTest extends Application {

    // parameters used for testing
    private final double buttonWidth = 600;
    private final double buttonHeight = 200;

    private final double buttonWidth2 = 375;

    private final double screenWidth = 1280;
    private final double screenHeight = 720;

    /**
     * The main entry point for the buttonTest class
     *
     * @param args
     */
    public static void main(String args[]) {
        launch(args);
    }

    /**
     * This sets up a simple testing stage.
     *
     * @param primaryStage the stage which stuff is displayed on
     */
    @Override
    public void start(Stage primaryStage) {
        // create a new group to hold the display elements
        Group root = new Group();

        // create the two example buttons
        mdButtonLeonard exampleButton = new mdButtonLeonard("", buttonWidth, buttonHeight, (screenWidth / 2) - (buttonWidth / 2),
                -150 + (screenHeight / 2) - ((2 * buttonHeight + 100) / 2));
        mdButtonLeonard exampleButton2 = new mdButtonLeonard("", buttonWidth2, buttonWidth2, (screenWidth / 2) - (buttonWidth2 / 2),
                (exampleButton.getY() + buttonHeight + 75));

        // conveniently holds the buttons
        mdButtonLeonard[] buttons = {exampleButton, exampleButton2};

        // turn on animation for the buttons
        for (mdButtonLeonard button : buttons) {
            button.setAnimation(true);
        }

        // These arrays hold the button text and button images
        ArrayList<Text> buttonText = new ArrayList<>(0);
        ArrayList<ImageView> buttonImages = new ArrayList<>(0);

        // set the drop shadows for the buttons
        exampleButton.setDropShadow();
        exampleButton2.setDropShadow();

        // add text to the first button
        buttonText.add(exampleButton.addText("Hello tanner", new Font("Warnock Pro", 100), Color.BLACK));
        // add an image to the second image
        buttonImages.add(exampleButton2.addImage(new File("src/gui/gui_elements/Testing/pigImage.png").getAbsolutePath(),
                .25, .25));

        // add the buttons to the display group
        root.getChildren().addAll(exampleButton, buttonText.get(0), exampleButton2, buttonImages.get(0));

        // create a new scene with the group
        Scene scene = new Scene(root, 1280, 720, Color.WHITE);
        // set the stage
        primaryStage.setScene(scene);
        // set the title
        primaryStage.setTitle("Button Test");
        // show the stage
        primaryStage.show();

        // handles animation every frame
        new AnimationTimer() {
            /**
             * This method handles screen events
             * @param now timestamp of current frame
             */
            @Override
            public void handle(long now) {
                for (mdButtonLeonard button : buttons) {
                    // animate the button
                    button.animate();
                }
            }
        }.start();
    }
}
