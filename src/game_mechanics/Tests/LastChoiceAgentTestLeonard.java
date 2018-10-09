package game_mechanics.Tests;

import game_mechanics.AgentLeonard;
import game_mechanics.decision.Algorithms.LastChoiceLeonard;
import game_mechanics.decision.Algorithms.RandomChoiceLeonard;
import game_mechanics.decision.ChoicesLeonard;

/**
 * <h1>Tests the Last Choice AgentLeonard for errors</h1>
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
class LastChoiceAgentTestLeonard {
    /**
     * Performs the tests on the Last Choice AgentLeonard
     * and prints out the results.
     * @param args command line arguments
     */
    public static void main(String args[]) {
        // create a random agent
        AgentLeonard randomAgent = new AgentLeonard(new RandomChoiceLeonard());
        // create a last choice agent
        AgentLeonard lastChoiceAgent = new AgentLeonard(new LastChoiceLeonard(randomAgent));

        // set up a testing stage with the two agents
        randomTestSetupLeonard.setupRandom(lastChoiceAgent, randomAgent, 100);

        // have the last choice agent make a decision
        ChoicesLeonard agentChoice = lastChoiceAgent.makeDecision();

        // make sure that the agent chooses correctly according to its decision algorithm
        if (agentChoice == randomAgent.getChoice(99))
            System.out.println("All Tests Passed for Last Choice AgentLeonard.");
        else {
            System.out.println("Test 0 not passed.");
            System.out.println("\tAgentLeonard's choice: " + agentChoice);
            System.out.println("\tOpponent's last choice: " + randomAgent.getChoice(99));
        }
    }
}
