package gui.start_screen.gui_elements.Testing;

import gui.start_screen.gui_elements.mdButton;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ButtonTest extends Application {

    private final double buttonWidth = 800;
    private final double buttonHeight = 175;

    private final double screenWidth = 1280;
    private final double screenHeight = 720;

    @SuppressWarnings("CanBeFinal")
    private mdButton exampleButton = new mdButton(buttonWidth, buttonHeight, (screenWidth / 2) - (buttonWidth / 2),
            (screenHeight / 2) - ((2 * buttonHeight + 100) / 2));

    @SuppressWarnings("CanBeFinal")
    private mdButton exampleButton2 = new mdButton(buttonWidth, buttonHeight, (screenWidth / 2) - (buttonWidth / 2),
            (screenHeight - (exampleButton.getY() + buttonHeight)));

    @SuppressWarnings("CanBeFinal")
    private mdButton[] buttons = {exampleButton, exampleButton2};

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        exampleButton.setDsEffects(10, 10, 5, Color.DARKGRAY);
        exampleButton.setDropShadow();

        exampleButton2.setDsEffects(10, 10, 5, Color.DARKGRAY);
        exampleButton2.setDropShadow();

        Group root = new Group(exampleButton, exampleButton2);
        Scene scene = new Scene(root, 1280, 720, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Button Test");
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (mdButton button : buttons) {
                    button.animate();
                }
            }
        }.start();
    }
}
