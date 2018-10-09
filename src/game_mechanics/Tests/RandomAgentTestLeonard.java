package game_mechanics.Tests;

import game_mechanics.AgentLeonard;
import game_mechanics.decision.Algorithms.RandomChoiceLeonard;
import game_mechanics.decision.ChoicesLeonard;

/**
 * <h1>Tests the Random Choice AgentLeonard</h1>
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
class RandomAgentTestLeonard {
    /**
     * Performs the tests on the Random Choice AgentLeonard
     * and prints out the results.
     * @param args command line arguments
     */
    public static void main(String args[]) {
        // create a random agent
        AgentLeonard randomAgent = new AgentLeonard(new RandomChoiceLeonard());

        // used to count the number each move was chosen
        int scissors = 0;
        int paper = 0;
        int rock = 0;

        // make one million choices
        for(int i = 0; i < 1_000_000; i++) {
            ChoicesLeonard choice = randomAgent.makeDecision();
            if (choice == ChoicesLeonard.ROCK) rock++;
            else if (choice == ChoicesLeonard.PAPER) paper++;
            else scissors++;
        }

        // check that the agent chooses roughly equal numbers of rock, paper, and scissors
        if((326_000 < paper && paper < 340_000) && (326_000 < scissors && scissors < 340_000) &&
           (326_000 < rock && rock < 340_000)) {
            System.out.println("All Tests Passed for Random AgentLeonard.\n\tTimes ROCK was chosen: " + rock + "\n\t" +
                    "Times PAPER was chosen: " + paper + "\n\tTimes SCISSORS was chosen: " +
                    scissors);
        }
        else {
            System.out.println("Tests failed. Random agent does not decide randomly.");
            System.out.println("\tTimes ROCK was chosen: " + rock + "\n\t" +
            "Times PAPER was chosen: " + paper + "\n\tTimes SCISSORS was chosen: " +
                    scissors);
        }
    }
}
