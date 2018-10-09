package game_mechanics.Tests;

import game_mechanics.AgentLeonard;
import game_mechanics.decision.Algorithms.RandomChoiceLeonard;

/**
 * <h1>Set up a Testing Environment</h1>
 * Allows you to quickly set up a Testing
 * environment with random moves.
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
class randomTestSetupLeonard {
    private randomTestSetupLeonard() {
    }

    public static void setupRandom(AgentLeonard agentOne, AgentLeonard agentTwo, int iterations) {
        // have the agents make decisions iterations times
        for(int i = 0; i < iterations; i++) {
            agentOne.forceDecision(new RandomChoiceLeonard());
            agentTwo.forceDecision(new RandomChoiceLeonard());
        }
    }
}
