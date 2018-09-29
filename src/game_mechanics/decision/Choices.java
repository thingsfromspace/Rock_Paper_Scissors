package game_mechanics.decision;

import java.util.Random;

public enum Choices {
    ROCK(1, "ROCK"), PAPER(2, "PAPER"), SCISSORS(3, "SCISSORS"), NOCHOICE(0, "NO CHOICE");

    private int typeNum;
    private String name;

    public static Choices getRandomChoice() {
        Choices choices[] = {Choices.ROCK, Choices.PAPER, Choices.SCISSORS};
        return choices[new Random().nextInt(3)];
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getTypeNum() {
        return typeNum;
    }
    private Choices(int typeNum, String name) {
        this.typeNum = typeNum;
        this.name = name;
    }
}
