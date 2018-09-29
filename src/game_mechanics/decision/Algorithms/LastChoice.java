package game_mechanics.decision.Algorithms;

import game_mechanics.Agent;
import game_mechanics.decision.Choices;
import game_mechanics.decision.DecisionAlgorithm;
import game_mechanics.decision.Input;

public class LastChoice extends DecisionAlgorithm {
    public LastChoice(Agent opponent) {
        super(new Input(opponent, 1, true));
    }

    @Override
    public Choices run() {
        Choices[] previousDecision;
        previousDecision = inputs.get(0).getInputs();
        return previousDecision[0];
    }
}
