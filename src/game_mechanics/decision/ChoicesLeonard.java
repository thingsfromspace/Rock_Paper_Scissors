package game_mechanics.decision;

import gui.FilePathsLeonard;

import java.util.Random;

/**
 * An enumeration used for representing the possible
 * moves in Rock-Paper-Scissors
 */
public enum ChoicesLeonard {
    // the possible RCS choices
    ROCK(1, "ROCK", 4),
    PAPER(3, "PAPER", 5),
    SCISSORS(2, "SCISSORS", 6),
    @SuppressWarnings("SpellCheckingInspection") NOCHOICE(0, "NO CHOICE");

    // a number associated with the choices to make evaluation easier
    private final int typeNum;
    // a string representing the choices
    private final String name;
    // a string that links to the image
    private final String iconLocation;

    /**
     * Creates a new RCP choice
     *
     * @param typeNum the number associated with the choice
     * @param name    the name of the choice
     */
    ChoicesLeonard(int typeNum, String name, int icon) {
        FilePathsLeonard paths = new FilePathsLeonard();

        this.typeNum = typeNum;
        this.name = name;
        this.iconLocation = paths.paths[icon];
    }

    /**
     * Creates a new RCP choice
     *
     * @param typeNum the number associated with the choice
     * @param name    the name of the choice
     */
    ChoicesLeonard(int typeNum, String name) {
        this.typeNum = typeNum;
        this.name = name;
        this.iconLocation = "none";
    }

    /**
     * Returns the icon string
     *
     * @return the icon pathname
     */
    public String getImageString() {
        return iconLocation;
    }

    /**
     * Creates a random RCP move
     *
     * @return a random RCP move
     */
    public static ChoicesLeonard getRandomChoice() {
        ChoicesLeonard choices[] = {ChoicesLeonard.ROCK, ChoicesLeonard.PAPER, ChoicesLeonard.SCISSORS};
        return choices[new Random().nextInt(3)];
    }

    /**
     * converts a number into a choice
     *
     * @param number the number associated with a choice
     * @return the choice associated with the number
     */
    public static ChoicesLeonard getChoiceFromNumber(int number) {
        // if the number is outside of the bounds, return NOCHOICE
        if (number <= 0 || number > 3) return ChoicesLeonard.NOCHOICE;

        // return the corresponding choice
        if (number == 1) return ChoicesLeonard.ROCK;
        else if (number == 2) return ChoicesLeonard.SCISSORS;
        else return ChoicesLeonard.PAPER;
    }

    /**
     * returns the string associated with the choice
     *
     * @return the string associated with the choice
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * returns the number associated with the choice
     *
     * @return the number associated with the choice
     */
    public int getTypeNum() {
        return typeNum;
    }
}
