package game_mechanics.decision.Algorithms;

import game_mechanics.Agent;
import game_mechanics.decision.Choices;
import game_mechanics.decision.DecisionAlgorithm;
import game_mechanics.decision.Input;

public class TrickyChoice extends DecisionAlgorithm {
    private Agent thisAgent;

    public TrickyChoice(Agent opponent) {
        super(new Input(opponent, 1, 1));
    }

    public void setSelf(Agent selfAgent) {
        this.thisAgent = selfAgent;
    }

    @Override
    public Choices run() {
        if (thisAgent == null) return Choices.NOCHOICE;

        Choices SelfLastMove = thisAgent.getChoice(thisAgent.numOfChoices() - 1);

        Choices opponentLastMove;
        if (SelfLastMove == Choices.NOCHOICE) opponentLastMove = Choices.NOCHOICE;
        else opponentLastMove = inputs.get(0).getInputs(true)[inputs.get(0).getInputs(true).length - 2];

        if (opponentLastMove == Choices.NOCHOICE || SelfLastMove == opponentLastMove) {
            return Choices.getRandomChoice();
        }

        if (thisAgent.getChoice(thisAgent.numOfChoices() - 1) != getWinningMove(opponentLastMove)) {
            return getWinningMove(opponentLastMove);
        }

        return getWinningMove(getWinningMove(thisAgent.getChoice(thisAgent.numOfChoices() - 1)));
    }

    private Choices getWinningMove(Choices opponentChoice) {
        int num = (opponentChoice.getTypeNum() - 1) % 3;
        return Choices.getChoiceFromNumber(num == 0 ? 3 : num);
    }
}
