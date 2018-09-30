package game_mechanics.Tests;

import game_mechanics.Agent;
import game_mechanics.decision.Algorithms.RandomChoice;

/**
 * <h1>Set up a Testing Environment</h1>
 * Allows you to quickly set up a Testing
 * environment with random moves.
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
class randomTestSetup {
    private randomTestSetup() {}

    public static void setupRandom(Agent agentOne, Agent agentTwo, int iterations) {
        for(int i = 0; i < iterations; i++) {
            agentOne.forceDecision(new RandomChoice());
            agentTwo.forceDecision(new RandomChoice());
        }
    }
}
