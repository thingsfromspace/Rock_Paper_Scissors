package gui;

import gui.start_screen.Start;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RCPApp extends Application {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        @SuppressWarnings("unused") Start startingScreen = new Start(Font.font("Warnock Pro"), "play", "options");
        Scene scene = new Scene(new StackPane(), 1280, 720);

        primaryStage.setTitle("Rock Paper Scissors");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
