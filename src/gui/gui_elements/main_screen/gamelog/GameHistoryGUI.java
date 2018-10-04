package gui.gui_elements.main_screen.gamelog;

import game_mechanics.Evaluation.Evaluation;
import game_mechanics.decision.Choices;
import gui.gui_elements.mdButton;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.util.ArrayList;

public class GameHistoryGUI {
    private double screenWidth;
    private double screenHeight;
    private Font systemFont;
    private Node parent;
    private GameHistory gameHistory = new GameHistory();
    private String numbering[];
    private ArrayList<Node> elements = new ArrayList<>(0);
    private ArrayList<Node> numberElements = new ArrayList<>(0);

    public GameHistoryGUI(double screenWidth, double screenHeight, Font font, Node parent) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.systemFont = font;
        this.parent = parent;
    }

    public ArrayList<Node> getGameHistoryGUI() {
        ArrayList<Node> elements = new ArrayList<>(0);

        // background button
        mdButton background = new mdButton("game history background", parent, 300, 670,
                955, 25);
        background.setDropShadow();
        background.setButtonColor(Color.LIGHTGRAY);
        background.isButton = false;
        this.elements.add(background);

        // background title
        mdButton backgroundTitle = new mdButton("game history title", parent, 300, 40,
                955, 25);
        backgroundTitle.setButtonColor(Color.WHITE);
        backgroundTitle.isButton = false;
        this.elements.add(backgroundTitle);
        this.elements.add(backgroundTitle.addText("game history", new Font(systemFont.getFamily(), 20),
                Color.BLACK));

        getNumberingButtons((670 - 50) / 56);

        elements.addAll(this.elements);
        elements.addAll(this.numberElements);
        return elements;
    }

    private void getNumberingButtons(int maxLength) {
        String numbering[] = new String[maxLength];
        for (int i = 0; i < maxLength; i++) {
            if (i == 0) numbering[i] = "1.";
            else numbering[i] = "";
        }
        this.numbering = numbering;
        makeAllNumberingButtons();
    }

    private void makeAllNumberingButtons() {
        numberElements.clear();
        // loop through and make buttons, add them to elements, and return button strings
        for (int i = 0; i < numbering.length; i++) {
            if (numbering[i].length() > 0) {
                double ycoo = elements.get(1).getLayoutBounds().getHeight() + elements.get(1).getLayoutY() + 30 + i * 56;
                mdButton numberButton = makeNumberButton(numbering[i], 960,
                        elements.get(1).getLayoutBounds().getHeight() + elements.get(1).getLayoutY() + 30 + i * 56);
                numberElements.add(numberButton);
                numberElements.add(numberButton.addText(numbering[i], new Font(systemFont.getFamily(), 16), Color.BLACK));
            }
        }
    }

    private mdButton makeNumberButton(String number, double x, double y) {
        mdButton numberButton = new mdButton(number, parent, 51, 51, x, y);
        numberButton.setButtonColor(Color.WHITE);
        numberButton.isButton = false;
        return numberButton;
    }

    public void addNewMove(String playerIcon1, String playerIcon2, Choices choice1, Choices choice2) {
        gameHistory.addMove(choice1, choice2);
        int numOfMoves = gameHistory.getNumOfMoves();

        if (numOfMoves + 1 >= (670 - 50) / 56) {
            for (int i = numbering.length - 1; i >= 0; i--) {
                if (i == 0) numbering[i] = ((Integer) (numOfMoves + 1)).toString() + ".";
                else numbering[i] = numbering[i - 1];
            }
        } else {
            for (int i = numOfMoves + 1; i >= 0; i--) {
                if (i != 0) {
                    numbering[i] = numbering[i - 1];
                } else numbering[i] = ((Integer) (numOfMoves + 1)).toString() + ".";
            }

        }

        makeAllNumberingButtons();

        if (numOfMoves <= 10) {
            int width = 234;
            int height = 51;
            double x = 1280 - 320 + 56;
            double y = 70 + (numOfMoves) * 56;

            double playerIconScale = 0.09;
            double choiceIconScale = 0.125;

            Color containerColor = Evaluation.isWinning(choice1, choice2) ?
                    Color.LIGHTGREEN : Evaluation.isLosing(choice1, choice2) ? Color.RED : Color.BLUE;

            mdButton container = new mdButton("container", parent, width, height,
                    x, y);
            container.setButtonColor(containerColor);
            this.elements.add(container);

            mdButton container2 = new mdButton("left container", parent, width / 2 - 15, height - 5,
                    x + 2.5, y + 2.5);
            container2.setButtonColor(Color.WHITE);
            this.elements.add(container2);

            mdButton container3 = new mdButton("left container", parent, width / 2 - 15, height - 5,
                    x + 2.5 + 117 + 10, y + 2.5);
            container3.setButtonColor(Color.WHITE);
            this.elements.add(container3);

            mdButton iconButton1 = new mdButton("icon1", parent, height - 5, height - 5, x + 2.5, y + 2.5);
            iconButton1.setButtonColor(Color.WHITE);
            this.elements.add(iconButton1);
            this.elements.add(iconButton1.addImage(new File(playerIcon1).getAbsolutePath(), playerIconScale, playerIconScale));

            mdButton iconButton2 = new mdButton("icon1", parent, height - 5, height - 5,
                    x + 2.5 + 117 + 10, y + 2.5);
            iconButton2.setButtonColor(Color.WHITE);
            this.elements.add(iconButton2);
            this.elements.add(iconButton2.addImage(new File(playerIcon2).getAbsolutePath(), playerIconScale, playerIconScale));

            mdButton choiceIcon1 = new mdButton("choice1", parent, height - 5, height - 5,
                    (height) + x + 2.5, y + 2.5);
            choiceIcon1.setButtonColor(Color.WHITE);
            this.elements.add(choiceIcon1);
            this.elements.add(choiceIcon1.addImage(new File(choice1.getImageString()).getAbsolutePath(), choiceIconScale, choiceIconScale));

            mdButton choiceIcon2 = new mdButton("choice1", parent, height - 5, height - 5,
                    (height) + x + 2.5 + 117 + 10, y + 2.5);
            choiceIcon2.setButtonColor(Color.WHITE);
            this.elements.add(choiceIcon2);
            this.elements.add(choiceIcon2.addImage(new File(choice2.getImageString()).getAbsolutePath(), choiceIconScale, choiceIconScale));
        }
    }

    public ArrayList<Node> getElements() {
        ArrayList<Node> allElements = elements;
        allElements.addAll(numberElements);
        return allElements;
    }
}
