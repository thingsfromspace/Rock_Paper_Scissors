package game_mechanics.decision;

import java.util.Random;

/**
 * An enumeration used for representing the possible
 * moves in Rock-Paper-Scissors
 */
public enum Choices {
    // the possible RCS choices
    ROCK(1, "ROCK"), PAPER(3, "PAPER"), SCISSORS(2, "SCISSORS"), @SuppressWarnings("SpellCheckingInspection") NOCHOICE(0, "NO CHOICE");

    // a number associated with the choices to make evaluation easier
    private final int typeNum;
    // a string representing the choices
    private final String name;

    /**
     * Creates a new RCP choice
     *
     * @param typeNum the number associated with the choice
     * @param name    the name of the choice
     */
    Choices(int typeNum, String name) {
        this.typeNum = typeNum;
        this.name = name;
    }

    /**
     * Creates a random RCP move
     *
     * @return a random RCP move
     */
    public static Choices getRandomChoice() {
        Choices choices[] = {Choices.ROCK, Choices.PAPER, Choices.SCISSORS};
        return choices[new Random().nextInt(3)];
    }

    /**
     * converts a number into a choice
     *
     * @param number the number associated with a choice
     * @return the choice associated with the number
     */
    public static Choices getChoiceFromNumber(int number) {
        // if the number is outside of the bounds, return NOCHOICE
        if (number <= 0 || number > 3) return Choices.NOCHOICE;

        // return the corresponding choice
        if (number == 1) return Choices.ROCK;
        else if (number == 2) return Choices.SCISSORS;
        else return Choices.PAPER;
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
