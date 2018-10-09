package game_mechanics;

import game_mechanics.decision.ChoicesLeonard;
import game_mechanics.decision.DecisionAlgorithmLeonard;

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
public class AgentLeonard {
    // holds the agent's decision algorithm
    private final DecisionAlgorithmLeonard decisionAlgorithm;
    // holds all of the agents choices
    @SuppressWarnings("Convert2Diamond")
    public ArrayList<ChoicesLeonard> priorDecisions = new ArrayList<ChoicesLeonard>(0);

    /**
     * Constructs an agent with a specific
     * decision algorithm
     *
     * @param algorithm the new agent's decision algorithm
     */
    public AgentLeonard(DecisionAlgorithmLeonard algorithm) {
        this.decisionAlgorithm = algorithm;
    }

    /**
     * This method returns the specified choice
     * from the agent's log of its previous
     * choices. If the agent has not made the choice
     * yet, this method will return {@link ChoicesLeonard#NOCHOICE}
     *
     * @param choiceIndex the index of the choice you
     *                    want to access, starting
     *                    from zero
     * @return the agent's specified choice
     */
    public ChoicesLeonard getChoice(int choiceIndex) {
        try {
            // return the specified choice
            return priorDecisions.get(choiceIndex);
        } catch (IndexOutOfBoundsException choiceNotMade) {
            // return no choice
            return ChoicesLeonard.NOCHOICE;
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
    public ChoicesLeonard makeDecision() {
        // run the agent's decision algorithm
        ChoicesLeonard decision = decisionAlgorithm.run();

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
    public void forceDecision(ChoicesLeonard choice) {
        // add the choice to the agent's prior decisions
        priorDecisions.add(choice);
    }

    /**
     * Allows the agent to use another
     * decision algorithm to make their decision.
     *
     * @param anotherAlgorithm the other decision algorithm
     */
    @SuppressWarnings("UnusedReturnValue")
    public void forceDecision(DecisionAlgorithmLeonard anotherAlgorithm) {
        // obtain a choice from another decision algorithm
        ChoicesLeonard decision = anotherAlgorithm.run();
        priorDecisions.add(decision);
    }

    /**
     * Obtains the agent's decision algorithm
     *
     * @return the agent's decision algorithm
     */
    public DecisionAlgorithmLeonard getDecisionAlgorithm() {
        // return the decision algorithm
        return this.decisionAlgorithm;
    }
}
