package game_mechanics.Tests;

import game_mechanics.AgentLeonard;
import game_mechanics.decision.Algorithms.RandomChoiceLeonard;
import game_mechanics.decision.Algorithms.UserChoiceLeonard;

/**
 * <h1>Test the User Choice AgentLeonard</h1>
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
class UserChoiceAgentTestLeonard {
    /**
     * Performs the tests on the User Choice AgentLeonard
     * and prints out the results.
     * @param args command line arguments
     */
    public static void main(String args[]) {
        // create a userAgent and a randomAgent
        AgentLeonard userAgent = new AgentLeonard(new UserChoiceLeonard(true));
        AgentLeonard randomAgent = new AgentLeonard(new RandomChoiceLeonard());

        for (int i = 0; i < 5; i++) {
            // this will let the user input a choice
            userAgent.makeDecision();
            randomAgent.makeDecision();

            // you need to manually confirm that this is correct
            System.out.println("\nYour Decision: " + userAgent.getChoice(i));
            System.out.println("Opponent's Decision: " + randomAgent.getChoice(i));
        }
    }
}
