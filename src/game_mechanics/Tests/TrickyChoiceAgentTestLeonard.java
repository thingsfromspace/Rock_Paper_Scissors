package game_mechanics.Tests;

import game_mechanics.AgentLeonard;
import game_mechanics.decision.Algorithms.RandomChoiceLeonard;
import game_mechanics.decision.Algorithms.TrickyChoiceLeonard;
import game_mechanics.decision.ChoicesLeonard;

/**
 * <h1>Test the Tricky Choice AgentLeonard</h1>
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
class TrickyChoiceAgentTestLeonard {
    /**
     * Performs the tests on the Tricky Choice AgentLeonard
     * and prints out the results.
     * @param args command line arguments
     */
    public static void main(String args[]) {
        // create a random agent and a tricky choice agent
        AgentLeonard random1 = new AgentLeonard(new RandomChoiceLeonard());
        TrickyChoiceLeonard algorithm1 = new TrickyChoiceLeonard((random1));
        AgentLeonard test1 = new AgentLeonard(algorithm1);
        algorithm1.setSelf(test1);

        System.out.println(test1.priorDecisions.size());

        // TEST 1: Make sure that it behaves correctly when losing
        boolean firstTest = true;

        // have it lose
        random1.forceDecision(ChoicesLeonard.ROCK);

        test1.forceDecision(ChoicesLeonard.SCISSORS);

        // make sure it behaves correctly
        random1.makeDecision();
        if (test1.makeDecision() != ChoicesLeonard.PAPER) {
            firstTest = false;
        } else {
            System.out.println(test1.priorDecisions.size());
        }

        // have it lose
        random1.forceDecision(ChoicesLeonard.PAPER);
        test1.forceDecision(ChoicesLeonard.ROCK);
        // make sure it behaves correctly
        random1.makeDecision();
        if (test1.makeDecision() != ChoicesLeonard.SCISSORS) firstTest = false;

        // have it lose
        random1.forceDecision(ChoicesLeonard.SCISSORS);
        test1.forceDecision(ChoicesLeonard.PAPER);
        // make sure it behaves correctly
        random1.makeDecision();
        if (test1.makeDecision() != ChoicesLeonard.ROCK) firstTest = false;


        // TEST 2: Make sure that it behaves correctly when winning
        boolean secondTest = true;

        // have it win
        random1.forceDecision(ChoicesLeonard.ROCK);
        test1.forceDecision(ChoicesLeonard.PAPER);
        // make sure it behaves correctly
        random1.makeDecision();
        ChoicesLeonard decision = test1.makeDecision();
        System.out.println(decision);
        if (decision != ChoicesLeonard.ROCK) secondTest = false;

        // have it win
        random1.forceDecision(ChoicesLeonard.PAPER);
        test1.forceDecision(ChoicesLeonard.SCISSORS);
        // make sure it behaves correctly
        random1.makeDecision();
        if (test1.makeDecision() != ChoicesLeonard.PAPER) secondTest = false;

        // have it win
        random1.forceDecision(ChoicesLeonard.SCISSORS);
        test1.forceDecision(ChoicesLeonard.ROCK);
        // make sure it behaves correctly
        random1.makeDecision();
        if (test1.makeDecision() != ChoicesLeonard.SCISSORS) secondTest = false;


        // Report test results
        System.out.println("First test: " + (firstTest ? "Success" : "Fail"));
        System.out.println("Second test: " + (secondTest ? "Success" : "Fail"));
    }
}
