package game_mechanics.decision.Algorithms;

import game_mechanics.decision.Choices;
import game_mechanics.decision.DecisionAlgorithm;

import java.util.Scanner;

/**
 * <h1>Allows the user to select a choice</h1>
 * This "decision algorithm" allows the user
 * to select a choice
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class UserChoice extends DecisionAlgorithm {
    // controls whether the input will be through the command line
    private final boolean commandLine;

    /**
     * Creates the UserChoice decision algorithm
     *
     * @param commandLine whether the input will be through the command line
     */
    public UserChoice(boolean commandLine) {
        super();
        this.commandLine = commandLine;
    }


    /**
     * Obtains input from the user and makes
     * that choice
     * @return the user's choice
     */
    @Override
    public Choices run() {
        if (commandLine) {
            // the command line should only be used for testing
            while (true) {
                // obtain input form the user
                String choices[] = {"rock", "paper", "scissors"};
                Scanner choice = new Scanner(System.in);
                System.out.println("Enter a choice by typing \"ROCK\", \"PAPER\", or \"SCISSORS\".");
                String userChoice = choice.next().toLowerCase();
                int n = java.util.Arrays.asList(choices).indexOf(userChoice);
                if (n == -1) {
                    System.out.println("Please type a valid input.");
                } else {
                    // return the user's input
                    if (n == 0) return Choices.ROCK;
                    if (n == 1) return Choices.PAPER;
                    return Choices.SCISSORS;
                }
            }
        } else return Choices.NOCHOICE;
    }

    /**
     * Returns the string to the User Choice Icon
     *
     * @return the path to the icon image
     */
    public static String getIcon() {
        return "src/gui/gui_elements/Assets/user_image.png";
    }
}
