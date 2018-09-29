package game_mechanics.decision.Algorithms;

import game_mechanics.decision.Choices;
import game_mechanics.decision.DecisionAlgorithm;

public class RandomChoice extends DecisionAlgorithm {

    public RandomChoice() {
        super();
    }

    @Override
    public Choices run() {
        return Choices.getRandomChoice();
    }
}
