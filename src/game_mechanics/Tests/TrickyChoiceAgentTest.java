package game_mechanics.Tests;

import game_mechanics.Agent;
import game_mechanics.decision.Algorithms.RandomChoice;
import game_mechanics.decision.Algorithms.TrickyChoice;
import game_mechanics.decision.Choices;

/**
 * <h1>Test the Tricky Choice Agent</h1>
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
class TrickyChoiceAgentTest {
    /**
     * Performs the tests on the Tricky Choice Agent
     * and prints out the results.
     * @param args command line arguments
     */
    public static void main(String args[]) {
        Agent random1 = new Agent(new RandomChoice());
        TrickyChoice algorithm1 = new TrickyChoice((random1));
        Agent test1 = new Agent(algorithm1);
        algorithm1.setSelf(test1);


        // TEST 1: Make sure that it behaves correctly when losing
        boolean firstTest = true;

        // have it lose
        random1.forceDecision(Choices.ROCK);
        test1.forceDecision(Choices.SCISSORS);
        // make sure it behaves correctly
        random1.makeDecision();
        if (test1.makeDecision() != Choices.PAPER) firstTest = false;

        // have it lose
        random1.forceDecision(Choices.PAPER);
        test1.forceDecision(Choices.ROCK);
        // make sure it behaves correctly
        random1.makeDecision();
        if (test1.makeDecision() != Choices.SCISSORS) firstTest = false;

        // have it lose
        random1.forceDecision(Choices.SCISSORS);
        test1.forceDecision(Choices.PAPER);
        // make sure it behaves correctly
        random1.makeDecision();
        if (test1.makeDecision() != Choices.ROCK) firstTest = false;


        // TEST 2: Make sure that it behaves correctly when winning
        boolean secondTest = true;

        // have it win
        random1.forceDecision(Choices.ROCK);
        test1.forceDecision(Choices.PAPER);
        // make sure it behaves correctly
        random1.makeDecision();
        if (test1.makeDecision() != Choices.ROCK) secondTest = false;

        // have it win
        random1.forceDecision(Choices.PAPER);
        test1.forceDecision(Choices.SCISSORS);
        // make sure it behaves correctly
        random1.makeDecision();
        if (test1.makeDecision() != Choices.PAPER) secondTest = false;

        // have it win
        random1.forceDecision(Choices.SCISSORS);
        test1.forceDecision(Choices.ROCK);
        // make sure it behaves correctly
        random1.makeDecision();
        if (test1.makeDecision() != Choices.SCISSORS) secondTest = false;


        // Report test results
        System.out.println("First test: " + (firstTest ? "Success" : "Fail"));
        System.out.println("Second test: " + (secondTest ? "Success" : "Fail"));
    }
}
