package game_mechanics.decision;

import java.util.ArrayList;

public abstract class DecisionAlgorithm {
    protected ArrayList<Input> inputs = new ArrayList<Input>(0);

    public DecisionAlgorithm(Input ... decisionInputs) {
        for(int i = 0; i < decisionInputs.length; i++) {
            inputs.add(decisionInputs[i]);
        }
    }

    public abstract Choices run();
}
