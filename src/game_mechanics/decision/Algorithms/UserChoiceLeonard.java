package game_mechanics.decision.Algorithms;

import game_mechanics.decision.ChoicesLeonard;
import game_mechanics.decision.DecisionAlgorithmLeonard;
import gui.FilePathsLeonard;
import gui.gui_elements.main_screen.play_arena.MainArenaLeonard;

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
public class UserChoiceLeonard extends DecisionAlgorithmLeonard {
    // file paths
    private static FilePathsLeonard paths = new FilePathsLeonard();
    // controls whether the input will be through the command line (used for testing)
    private final boolean commandLine;

    /**
     * Creates the UserChoiceLeonard decision algorithm
     *
     * @param commandLine whether the input will be through the command line
     */
    public UserChoiceLeonard(boolean commandLine) {
        super();
        this.commandLine = commandLine;
    }

    /**
     * Returns the string to the User Choice Icon
     *
     * @return the path to the icon image
     */
    public static String getIconStatic() {
        // return the path to the icon for this agent
        return paths.paths[10];
    }

    /**
     * Obtains input from the user and makes
     * that choice
     * @return the user's choice
     */
    @Override
    public ChoicesLeonard run() {
        if (commandLine) {
            // the command line should only be used for testing
            while (true) {
                // possible choices
                String choices[] = {"rock", "paper", "scissors"};

                // obtain input form the user
                Scanner choice = new Scanner(System.in);
                System.out.println("Enter a choice by typing \"ROCK\", \"PAPER\", or \"SCISSORS\".");
                String userChoice = choice.next().toLowerCase();

                // get the index of the input in che choices array
                int n = java.util.Arrays.asList(choices).indexOf(userChoice);
                if (n == -1) {
                    // if their input is not in the array, prompt them to try again
                    System.out.println("Please type a valid input.");
                    return ChoicesLeonard.NOCHOICE;
                } else {
                    // return the user's input
                    if (n == 0) return ChoicesLeonard.ROCK;
                    if (n == 1) return ChoicesLeonard.PAPER;
                    return ChoicesLeonard.SCISSORS;
                }
            }
        } else {
            // get input from the main arena
            return MainArenaLeonard.userChoice;
        }
    }

    /**
     * Returns the string to the Random Choice Icon
     *
     * @return the path to the icon image
     */
    @Override
    public String getIcon() {
        // return the path to the icon for this agent
        return getIconStatic();
    }
}
