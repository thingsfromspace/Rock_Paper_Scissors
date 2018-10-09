package game_mechanics.decision;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The framework for a decision algorithm
 */
public abstract class DecisionAlgorithmLeonard {
    // the inputs to the decision algorithm
    protected final ArrayList<InputLeonard> inputs = new ArrayList<InputLeonard>(0);

    /**
     * creates the Decision Algorithm with the specified inputs
     *
     * @param decisionInputs the inputs to the decision algorithm
     */
    protected DecisionAlgorithmLeonard(InputLeonard... decisionInputs) {
        Collections.addAll(inputs, decisionInputs);
    }

    /**
     * runs the decision algorithm
     * @return the output to the decision algorithm
     */
    public abstract ChoicesLeonard run();

    /**
     * Returns the string to the Decision Algorithm Icon
     *
     * @return the path to the icon image
     */
    public abstract String getIcon();
}
