package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RCPApp extends Application {
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new StackPane(), 1280, 720);

        primaryStage.setTitle("Rock Paper Scissors");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
