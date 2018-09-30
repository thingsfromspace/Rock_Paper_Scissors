package game_mechanics;

import game_mechanics.decision.Choices;
import game_mechanics.decision.DecisionAlgorithm;

import java.util.ArrayList;

/**
 * <h1>Creates a RCP agent</h1>
 * This class is the structure for an agent. The
 * main properties are an agent are 1) Its algorithm,
 * or decision-type, and 2) its previous decisions.
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class Agent {
    // holds all of the agents choices
    @SuppressWarnings("Convert2Diamond")
    private final ArrayList<Choices> priorDecisions = new ArrayList<Choices>(0);

    // holds the agent's decision algorithm
    private final DecisionAlgorithm decisionAlgorithm;

    /**
     * Constructs an agent with a specific
     * decision algorithm
     *
     * @param algorithm the new agent's decision algorithm
     */
    public Agent(DecisionAlgorithm algorithm) {
        this.decisionAlgorithm = algorithm;
    }

    /**
     * This method returns the specified choice
     * from the agent's log of its previous
     * choices. If the agent has not made the choice
     * yet, this method will return {@link game_mechanics.decision.Choices#NOCHOICE}
     *
     * @param choiceIndex the index of the choice you
     *                    want to access, starting
     *                    from zero
     * @return the agent's specified choice
     */
    public Choices getChoice(int choiceIndex) {
        try {
            // return the specified choice
            return priorDecisions.get(choiceIndex);
        } catch (IndexOutOfBoundsException choiceNotMade) {
            // return no choice
            return Choices.NOCHOICE;
        }
    }

    /**
     * Returns the number of choices the agent has made
     * @return how many choices the agent has made
     */
    public int numOfChoices() {
        return priorDecisions.size();
    }

    /**
     * Allows the agent to make a decision,
     * and returns that decision
     * @return the decision the agent has made
     */
    public Choices makeDecision() {
        // run the agent's decision algorithm
        Choices decision = decisionAlgorithm.run();

        // update the agent's log of her decisions
        priorDecisions.add(decision);

        return decision;
    }

    /**
     * Allows you to force the agent
     * to make a certain decision
     *
     * @param choice the decision the agent must make
     */
    @SuppressWarnings("UnusedReturnValue")
    public void forceDecision(Choices choice) {
        priorDecisions.add(choice);
    }

    /**
     * Allows the agent to use another
     * decision algorithm to make their decision.
     *
     * @param anotherAlgorithm the other decision algorithm
     */
    @SuppressWarnings("UnusedReturnValue")
    public void forceDecision(DecisionAlgorithm anotherAlgorithm) {
        Choices decision = anotherAlgorithm.run();
        priorDecisions.add(decision);
    }
}
