package gui.gui_elements.main_screen.gamelog;

import game_mechanics.Evaluation.EvaluationLeonard;
import game_mechanics.decision.ChoicesLeonard;
import gui.gui_elements.mdButtonLeonard;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;

/**
 * <h1>This class creates a 'game history' board
 * so that the user can analyze their past moves.</h1>
 * This creates a game history log, which displays
 * the icons of both players, as well as their moves.
 * Moves can be added to this log.
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class GameHistoryGUILeonard {
    // the font used
    private Font systemFont;

    // holds the game history
    private GameHistoryLeonard gameHistory = new GameHistoryLeonard();
    // holds the numbering for the game log
    private String numbering[];

    // holds all of the elements of the game log
    private ArrayList<Node> elements = new ArrayList<>(0);
    // holds only the number elements of the game log
    private ArrayList<Node> numberElements = new ArrayList<>(0);

    // the background button
    private mdButtonLeonard background;
    // the title button
    private mdButtonLeonard title;
    // the title text
    private Text titleText;

    /**
     * Constructs a Game Log
     *
     * @param font the font used for the game log
     */
    public GameHistoryGUILeonard(Font font) {
        this.systemFont = font;
    }

    /**
     * Creates a new game history log with no moves
     *
     * @return a set of elements that comprises the game log
     */
    public ArrayList<Node> getGameHistoryGUI() {
        // create an array to hold all of the game log elements
        ArrayList<Node> elements = new ArrayList<>(0);

        // CREATE THE BACKGROUND BUTTON
        mdButtonLeonard background = new mdButtonLeonard("game history background", 300, 670,
                955, 25);
        background.setDropShadow();
        background.setButtonColor(Color.LIGHTGRAY);
        background.isButton = false;
        this.elements.add(background);
        this.background = background;

        // CREATE THE BACKGROUND TITLE BUTTON AND TEXT
        mdButtonLeonard backgroundTitle = new mdButtonLeonard("game history title", 300, 40,
                955, 25);
        backgroundTitle.setButtonColor(Color.WHITE);
        backgroundTitle.isButton = false;
        this.elements.add(backgroundTitle);
        Text backgroundText = backgroundTitle.addText("game history", new Font(systemFont.getFamily(), 20),
                Color.BLACK);
        this.elements.add(backgroundText);
        this.title = backgroundTitle;
        this.titleText = backgroundText;

        // resets the numbering buttons
        getNumberingButtons((670 - 50) / 56);

        // add all of the elements to the appropriate arrays and return the elements.
        elements.addAll(this.elements);
        elements.addAll(this.numberElements);
        return elements;
    }

    /**
     * This makes all of the numbering buttons
     *
     * @param maxLength the maximum amount of moves displayed on the game log
     */
    private void getNumberingButtons(int maxLength) {
        // create a string array to hold all of the numbers
        String numbering[] = new String[maxLength];

        // essentially fill all except for the first with an empty string
        for (int i = 0; i < maxLength; i++) {
            if (i == 0) numbering[i] = "1.";
            else numbering[i] = "";
        }
        this.numbering = numbering;

        // make the appropriate buttons now that we have the numbers
        makeAllNumberingButtons();
    }

    /**
     * Given all of the numbers are set, this makes buttons for the numbers
     */
    private void makeAllNumberingButtons() {
        // clear any previous numbers
        numberElements.clear();
        // loop through and make buttons, add them to elements, and return button strings
        for (int i = 0; i < numbering.length; i++) {
            // check to make sure that we don't add a button for a blank string
            if (numbering[i].length() > 0) {
                // create a new button for the number
                mdButtonLeonard numberButton = makeNumberButton(numbering[i], 960,
                        elements.get(1).getLayoutBounds().getHeight() + elements.get(1).getLayoutY() + 30 + i * 56);
                // add the button and the text to the numberElements array
                numberElements.add(numberButton);
                numberElements.add(numberButton.addText(numbering[i], new Font(systemFont.getFamily(), 16), Color.BLACK));
            }
        }
    }

    /**
     * Creates a square button for a number
     *
     * @param number the string of the number
     * @param x      the x coo of the button
     * @param y      the y coo of the button
     * @return the new button
     */
    private mdButtonLeonard makeNumberButton(String number, double x, double y) {
        // create the button
        mdButtonLeonard numberButton = new mdButtonLeonard(number, 51, 51, x, y);
        // set the color to white and turn off button functions (e.g. animation)
        numberButton.setButtonColor(Color.WHITE);
        numberButton.isButton = false;

        // return the new button
        return numberButton;
    }

    /**
     * This function allows you to add a new move to the game history log
     *
     * @param playerIcon1 the strings of all of the user Icons
     * @param playerIcon2 the strings of all of the opponent Icons
     * @param choice1     the first choice (by the user)
     * @param choice2     the second choice (by the computer)
     */
    public void addNewMove(ArrayList<String> playerIcon1, ArrayList<String> playerIcon2,
                           ChoicesLeonard choice1, ChoicesLeonard choice2) {
        // add the new move to the game history log
        gameHistory.addMove(choice1, choice2);
        // get the number of moves
        int numOfMoves = gameHistory.getNumOfMoves();

        // if the number of moves exceeds the maximum moves that the screen can display
        if (numOfMoves + 1 >= (670 - 50) / 56) {
            for (int i = numbering.length - 1; i >= 0; i--) {
                // loop through all of the numbers and update them
                if (i == 0) {
                    // add a new number
                    numbering[i] = ((Integer) (numOfMoves + 1)).toString() + ".";
                } else {
                    // move the numbers up by one
                    numbering[i] = numbering[i - 1];
                }
            }
        } else {
            // loop through all of the numbers and update them
            for (int i = numOfMoves + 1; i >= 0; i--) {
                if (i != 0) {
                    // add a new number
                    numbering[i] = numbering[i - 1];
                } else {
                    // move the numbers up by one
                    numbering[i] = ((Integer) (numOfMoves + 1)).toString() + ".";
                }
            }

        }

        // re-make all of the numbering buttons
        makeAllNumberingButtons();

        // define numbers for the game log
        int width = 234;
        int height = 51;
        double x = 1280 - 320 + 56;
        double y = 70;

        // clear all of the previous elements
        this.elements.clear();

        // add back in the background, title, and titleText
        this.elements.add(background);
        this.elements.add(title);
        this.elements.add(titleText);

        // loop through the most recent moves and display them in the log
        for (int i = numOfMoves - 1; i >= (numOfMoves - 1) - 9 && i >= 0; i--) {
            // create a button for a certain move in the log
            createButton(width, height, x, y + (numOfMoves - i) * 56, playerIcon1.get(i), playerIcon2.get(i),
                    gameHistory.getUserMove(numOfMoves - i),
                    gameHistory.getComputerMove(numOfMoves - i));
        }
    }

    /**
     * This creates a single button for the log which represents one full move
     *
     * @param width       the width of the button
     * @param height      the height of the button
     * @param x           the x coo of the button
     * @param y           the y coo of the button
     * @param playerIcon1 the first player's icon
     * @param playerIcon2 the second player's icon
     * @param choice1     the first player's choice
     * @param choice2     the second player's choice
     */
    private void createButton(double width, double height, double x, double y, String playerIcon1, String playerIcon2,
                              ChoicesLeonard choice1, ChoicesLeonard choice2) {

        // define the scales for the different icons
        double playerIconScale = 0.09;
        double choiceIconScale = 0.125;

        // get the container color depending on whether the user won or lost
        Color containerColor = EvaluationLeonard.isWinning(choice1, choice2) ?
                Color.LIGHTGREEN : EvaluationLeonard.isLosing(choice1, choice2) ? Color.RED : Color.BLUE;

        // CREATE THE OUTSIDE CONTAINER OF THE BUTTON
        mdButtonLeonard container = new mdButtonLeonard("container", width, height,
                x, y);
        container.isButton = false;
        container.setButtonColor(containerColor);
        this.elements.add(container);

        // CREATE THE INNER CONTAINER FOR THE USER'S MOVE
        mdButtonLeonard container2 = new mdButtonLeonard("left container", width / 2 - 15, height - 5,
                x + 2.5, y + 2.5);
        container2.setButtonColor(Color.WHITE);
        container2.isButton = false;
        this.elements.add(container2);

        // CREATE THE INNER CONTAINER FOR THE COMPUTER'S MOVE
        mdButtonLeonard container3 = new mdButtonLeonard("left container", width / 2 - 15, height - 5,
                x + 2.5 + 117 + 10, y + 2.5);
        container3.setButtonColor(Color.WHITE);
        container3.isButton = false;
        this.elements.add(container3);

        // CREATE A BUTTON FOR THE FIRST PLAYER'S ICON
        mdButtonLeonard iconButton1 = new mdButtonLeonard("icon1", height - 5, height - 5, x + 2.5, y + 2.5);
        iconButton1.setButtonColor(Color.WHITE);
        this.elements.add(iconButton1);
        iconButton1.isButton = false;
        this.elements.add(iconButton1.addImage(new File(playerIcon1).getAbsolutePath(), playerIconScale, playerIconScale));

        // CREATE A BUTTON FOR THE SECOND PLAYER'S ICON
        mdButtonLeonard iconButton2 = new mdButtonLeonard("icon1", height - 5, height - 5,
                x + 2.5 + 117 + 10, y + 2.5);
        iconButton2.setButtonColor(Color.WHITE);
        this.elements.add(iconButton2);
        iconButton2.isButton = false;
        this.elements.add(iconButton2.addImage(new File(playerIcon2).getAbsolutePath(), playerIconScale, playerIconScale));

        // CREATE AN ICON FOR THE FIRST PLAYER'S CHOICE
        mdButtonLeonard choiceIcon1 = new mdButtonLeonard("choice1", height - 5, height - 5,
                (height) + x + 2.5, y + 2.5);
        choiceIcon1.setButtonColor(Color.WHITE);
        this.elements.add(choiceIcon1);
        choiceIcon1.isButton = false;
        this.elements.add(choiceIcon1.addImage(new File(choice1.getImageString()).getAbsolutePath(), choiceIconScale, choiceIconScale));

        // CREATE AN ICON FOR THE COMPUTER'S CHOICE
        mdButtonLeonard choiceIcon2 = new mdButtonLeonard("choice1", height - 5, height - 5,
                (height) + x + 2.5 + 117 + 10, y + 2.5);
        choiceIcon2.setButtonColor(Color.WHITE);
        this.elements.add(choiceIcon2);
        choiceIcon2.isButton = false;
        this.elements.add(choiceIcon2.addImage(new File(choice2.getImageString()).getAbsolutePath(), choiceIconScale, choiceIconScale));
    }

    /**
     * This simply returns all of the elements used by this class
     * @return An array list of Nodes
     */
    public ArrayList<Node> getElements() {
        ArrayList<Node> allElements = elements;
        allElements.addAll(numberElements);
        return allElements;
    }
}
