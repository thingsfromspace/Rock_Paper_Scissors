package gui.gui_elements.Testing;

import gui.gui_elements.mdButton;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ButtonTest extends Application {

    private final double buttonWidth = 600;
    private final double buttonHeight = 200;

    private final double buttonWidth2 = 375;

    private final double screenWidth = 1280;
    private final double screenHeight = 720;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        @SuppressWarnings("CanBeFinal")
        mdButton exampleButton = new mdButton("", root, buttonWidth, buttonHeight, (screenWidth / 2) - (buttonWidth / 2),
                -150 + (screenHeight / 2) - ((2 * buttonHeight + 100) / 2));

        @SuppressWarnings("CanBeFinal")
        mdButton exampleButton2 = new mdButton("", root, buttonWidth2, buttonWidth2, (screenWidth / 2) - (buttonWidth2 / 2),
                (exampleButton.getY() + buttonHeight + 75));

        @SuppressWarnings("CanBeFinal")
        mdButton[] buttons = {exampleButton, exampleButton2};

        ArrayList<Text> buttonText = new ArrayList<>(0);
        ArrayList<ImageView> buttonImages = new ArrayList<>(0);

        exampleButton.setDropShadow();

        exampleButton2.setDropShadow();

        buttonText.add(exampleButton.addText("Hello tanner", new Font("Warnock Pro", 100), Color.BLACK));
        buttonImages.add(exampleButton2.addImage("/Users/tannerleonardmac/Documents/code_google_drive/AP_Computer_Science/Rock Paper Scissors/src/gui/gui_elements/Testing/pigImage.png"));

        root.getChildren().addAll(exampleButton, buttonText.get(0), exampleButton2, buttonImages.get(0));
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
