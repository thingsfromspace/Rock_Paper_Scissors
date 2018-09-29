package game_mechanics.Tests;

import game_mechanics.Agent;
import game_mechanics.decision.Algorithms.LastChoice;
import game_mechanics.decision.Algorithms.RandomChoice;
import game_mechanics.decision.Choices;

public class LastChoiceAgentTest {
    public static void main(String args[]) {
        Agent randomAgent = new Agent(new RandomChoice());
        Agent lastChoiceAgent = new Agent(new LastChoice(randomAgent));

        randomTestSetup.setupRandom(lastChoiceAgent, randomAgent, 100);

        Choices agentChoice = lastChoiceAgent.makeDecision();

        if(agentChoice == randomAgent.getChoice(99)) System.out.println("All Tests Passed for Last Choice Agent.");
        else {
            System.out.println("Test 0 not passed.");
            System.out.println("\tAgent's choice: " + agentChoice);
            System.out.println("\tOpponent's last choice: " + randomAgent.getChoice(99));
        }
    }
}
