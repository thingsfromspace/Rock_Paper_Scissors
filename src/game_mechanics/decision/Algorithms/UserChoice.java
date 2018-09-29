package game_mechanics.decision.Algorithms;

import game_mechanics.decision.Choices;
import game_mechanics.decision.DecisionAlgorithm;

import java.util.Scanner;

public class UserChoice extends DecisionAlgorithm {
    private boolean commandLine = false;

    public UserChoice(boolean commandLine) {
        super();
        this.commandLine = commandLine;
    }

    @Override
    public Choices run() {
        if (commandLine) {
            while (true) {
                // this should only be used for testing
                String choices[] = {"rock", "paper", "scissors"};
                Scanner choice = new Scanner(System.in);
                System.out.println("Enter a choice by typing \"ROCK\", \"PAPER\", or \"SCISSORS\".");
                String userChoice = choice.next().toLowerCase();
                int n = java.util.Arrays.asList(choices).indexOf(userChoice);
                if (n == -1) {
                    System.out.println("Please type a valid input.");
                    continue;
                } else {
                    if (n == 0) return Choices.ROCK;
                    if (n == 1) return Choices.PAPER;
                    return Choices.SCISSORS;
                }
            }
        } else return Choices.NOCHOICE;
    }
}
