package game_mechanics.Tests;

import game_mechanics.Agent;
import game_mechanics.decision.Algorithms.RandomChoice;

public class randomTestSetup {
    private randomTestSetup() {}

    public static void setupRandom(Agent agentOne, Agent agentTwo, int iterations) {
        for(int i = 0; i < iterations; i++) {
            agentOne.forceDecision(new RandomChoice());
            agentTwo.forceDecision(new RandomChoice());
        }
    }
}
