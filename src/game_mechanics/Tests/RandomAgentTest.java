package game_mechanics.Tests;

import game_mechanics.Agent;
import game_mechanics.decision.Algorithms.RandomChoice;
import game_mechanics.decision.Choices;

/**
 * <h1>Tests the Random Choice Agent</h1>
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
class RandomAgentTest {
    /**
     * Performs the tests on the Random Choice Agent
     * and prints out the results.
     * @param args command line arguments
     */
    public static void main(String args[]) {
        Agent randomAgent = new Agent(new RandomChoice());
        int scissors = 0;
        int paper = 0;
        int rock = 0;
        for(int i = 0; i < 1_000_000; i++) {
            Choices choice = randomAgent.makeDecision();
            if(choice == Choices.ROCK) rock++;
            else if(choice == Choices.PAPER) paper++;
            else scissors++;
        }
        if((326_000 < paper && paper < 340_000) && (326_000 < scissors && scissors < 340_000) &&
           (326_000 < rock && rock < 340_000)) {
            System.out.println("All Tests Passed for Random Agent.\n\tTimes ROCK was chosen: " + rock + "\n\t" +
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
