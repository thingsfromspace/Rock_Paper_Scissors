package game_mechanics.decision;

import java.util.Random;

public enum Choices {
    ROCK(1, "ROCK"), PAPER(3, "PAPER"), SCISSORS(2, "SCISSORS"), NOCHOICE(0, "NO CHOICE");

    private int typeNum;
    private String name;

    public static Choices getRandomChoice() {
        Choices choices[] = {Choices.ROCK, Choices.PAPER, Choices.SCISSORS};
        return choices[new Random().nextInt(3)];
    }

    Choices(int typeNum, String name) {
        this.typeNum = typeNum;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getTypeNum() {
        return typeNum;
    }

    public static Choices getChoiceFromNumber(int n) {
        if (n <= 0 || n > 3) return Choices.NOCHOICE;

        if (n == 1) return Choices.ROCK;
        else if (n == 2) return Choices.SCISSORS;
        else return Choices.PAPER;
    }
}
