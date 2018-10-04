package gui;

import gui.gui_elements.main_screen.MainScreen;
import gui.gui_elements.mdButton;
import gui.gui_elements.options_screen.Options;
import gui.gui_elements.options_screen.Preferences;
import gui.start_screen.Start;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RCPApp extends Application {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    public static ArrayList<mdButton> buttons = new ArrayList<>(0);
    public static ArrayList<ImageView> buttonImages = new ArrayList<>(0);

    public static void main(String args[]) {
        launch(args);
    }

    public static ArrayList<Text> buttonText = new ArrayList<>(0);
    private boolean startScreenShowing = true;
    private boolean optionScreenShowing = false;
    private boolean mainScreenShowing = false;

    @Override
    public void start(Stage primaryStage) {
        Start startingScreen = new Start(WIDTH, HEIGHT,
                Font.font("Europa"), "play", "options");

        Options optionScreen = new Options(WIDTH, HEIGHT,
                Font.font("Europa"), 50);

        MainScreen mainScreen = new MainScreen(WIDTH, HEIGHT, Font.font("Europa"));

        Group displayGroup = new Group();

        displayGroup.setOnMousePressed(e -> {
            for (mdButton button : buttons) {
                if (button.contains(e.getX(), e.getY())) {
                    button.clicked();
                    System.out.println(button);
                }
            }
        });

        changeToStart(startingScreen, displayGroup);

        Scene scene = new Scene(displayGroup, 1280, 720);

        primaryStage.setTitle("Rock Paper Scissors");
        primaryStage.setScene(scene);
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (mdButton button : buttons) {
                    button.animate();
                }

                if (startScreenShowing) {
                    if (buttons.get(1).timesClicked > 0) {
                        changeToMain(mainScreen, displayGroup);
                        startScreenShowing = false;
                        mainScreenShowing = true;
                    }
                    if (startScreenShowing && buttons.get(2).timesClicked > 0) {
                        changeToOptions(optionScreen, displayGroup);
                        optionScreenShowing = true;
                        startScreenShowing = false;
                    }
                } else if (optionScreenShowing) {
                    if (buttons.get(0).timesClicked > 0) {
                        Options.toggle(buttons.get(0), Preferences.preferences[0]);
                        Preferences.preferences[0] = !Preferences.preferences[0];
                    } else if (buttons.get(1).timesClicked > 0) {
                        Options.toggle(buttons.get(1), Preferences.preferences[1]);
                        Preferences.preferences[1] = !Preferences.preferences[1];
                    } else if (buttons.get(2).timesClicked > 0) {
                        changeToStart(startingScreen, displayGroup);
                        startScreenShowing = true;
                        optionScreenShowing = false;
                    }
                }
            }
        }.start();
    }

    private void changeToOptions(Options optionScreen, Group displayGroup) {
        buttons.clear();
        buttonImages.clear();
        buttonText.clear();

        displayGroup.getChildren().clear();


        String toggleOptions[] = {"toggle click sound", "toggle hover sound"};
        for (Node element : optionScreen.getOptionScreen(displayGroup, toggleOptions)) {
            displayGroup.getChildren().add(element);
            if (element.getClass().equals(mdButton.class)) {
                buttons.add((mdButton) element);
            }
            if (element.getClass().equals(ImageView.class)) buttonImages.add((ImageView) element);
            if (element.getClass().equals(Text.class)) buttonText.add((Text) element);
        }
    }

    private void changeToStart(Start startScreen, Group displayGroup) {
        buttons.clear();
        buttonImages.clear();
        buttonText.clear();

        displayGroup.getChildren().clear();

        for (Node element : startScreen.getStartScreen(displayGroup, Color.BLACK, Color.BLACK)) {
            displayGroup.getChildren().add(element);
            if (element.getClass().equals(mdButton.class)) {
                buttons.add((mdButton) element);
            }
            if (element.getClass().equals(ImageView.class)) buttonImages.add((ImageView) element);
            if (element.getClass().equals(Text.class)) buttonText.add((Text) element);
        }

    }

    private void changeToMain(MainScreen mainScreen, Group displayGroup) {
        buttons.clear();
        buttonImages.clear();
        buttonText.clear();

        displayGroup.getChildren().clear();

        for (Node element : mainScreen.getMainScreen(displayGroup)) {
            displayGroup.getChildren().add(element);
            if (element.getClass().equals(mdButton.class)) {
                buttons.add((mdButton) element);
            }
            if (element.getClass().equals(ImageView.class)) buttonImages.add((ImageView) element);
            if (element.getClass().equals(Text.class)) buttonText.add((Text) element);
        }
    }
}
