package game_mechanics.Tests;

import game_mechanics.Agent;
import game_mechanics.decision.Algorithms.RandomChoice;
import game_mechanics.decision.Algorithms.UserChoice;

public class UserChoiceAgentTest {
    public static void main(String args[]) {
        Agent userAgent = new Agent(new UserChoice(true));
        Agent randomAgent = new Agent(new RandomChoice());

        for (int i = 0; i < 5; i++) {
            userAgent.makeDecision();
            randomAgent.makeDecision();

            System.out.println("\nYour Decision: " + userAgent.getChoice(i));
            System.out.println("Opponent's Decision: " + randomAgent.getChoice(i));
        }
    }
}
