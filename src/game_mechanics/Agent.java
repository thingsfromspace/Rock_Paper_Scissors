package game_mechanics;

import game_mechanics.decision.Choices;
import game_mechanics.decision.DecisionAlgorithm;

import java.util.ArrayList;

public class Agent {
    private ArrayList<Choices> priorDecisions = new ArrayList<Choices>(0);
    private DecisionAlgorithm decisionAlgorithm;

    public Choices getChoice(int choiceIndex) {
        try {
            return priorDecisions.get(choiceIndex);
        } catch (IndexOutOfBoundsException choiceNotMade) {
            return Choices.NOCHOICE;
        }
    }

    public int numOfChoices() {
        return priorDecisions.size();
    }

    public Agent(DecisionAlgorithm algorithm) {
        this.decisionAlgorithm = algorithm;
    }

    public Choices makeDecision() {
        Choices decision = decisionAlgorithm.run();
        priorDecisions.add(decision);
        return decision;
    }

    public Choices forceDecision(Choices choice) {
        priorDecisions.add(choice);
        return choice;
    }

    public Choices forceDecision(DecisionAlgorithm anotherAlgorithm) {
        Choices decision = anotherAlgorithm.run();
        priorDecisions.add(decision);
        return decision;
    }
}
