package game_mechanics.Tests;

import game_mechanics.Agent;
import game_mechanics.decision.Algorithms.RandomChoice;
import game_mechanics.decision.Algorithms.UserChoice;

/**
 * <h1>Test the User Choice Agent</h1>
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
class UserChoiceAgentTest {
    /**
     * Performs the tests on the User Choice Agent
     * and prints out the results.
     * @param args command line arguments
     */
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
