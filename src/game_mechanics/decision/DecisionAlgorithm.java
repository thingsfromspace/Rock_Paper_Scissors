package game_mechanics.decision;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The framework for a decision algorithm
 */
public abstract class DecisionAlgorithm {
    // the inputs to the decision algorithm
    @SuppressWarnings("Convert2Diamond")
    protected final ArrayList<Input> inputs = new ArrayList<Input>(0);

    /**
     * creates the Decision Algorithm with the specified inputs
     *
     * @param decisionInputs the inputs to the decision algorithm
     */
    protected DecisionAlgorithm(Input... decisionInputs) {
        Collections.addAll(inputs, decisionInputs);
    }

    /**
     * runs the decision algorithm
     * @return the output to the decision algorithm
     */
    public abstract Choices run();
}
