package game_mechanics.decision.Algorithms;

import game_mechanics.Agent;
import game_mechanics.Evaluation.Evaluation;
import game_mechanics.decision.Choices;
import game_mechanics.decision.DecisionAlgorithm;
import game_mechanics.decision.Input;

/**
 * <h1>Follows a more complex decision procedure
 * to exploit predictable human players</h1>
 * This decision algorithm works as follows:
 * If the algorithm just lost, it will play
 * the move that beats its opponent's last move,
 * as the opponent is likely to play the same move again.
 * If the algorithm just won, it will predict
 * that the opponent will play the move that beats
 * its current move, and will thus play the move
 * that beats the move that beats its current
 * move.
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class TrickyChoice extends DecisionAlgorithm {
    private Agent thisAgent;

    /**
     * Constructs a Decision Algorithm in reference
     * to the opponent
     *
     * @param opponent the algorithm's opponent
     */
    public TrickyChoice(Agent opponent) {
        super(new Input(opponent, 1));
    }

    /**
     * This allows the agent to know it's own past
     * decisions. IT MUST be run before making decisions
     * with this agent
     * @param selfAgent the agent that this algorithm is implemented in
     */
    public void setSelf(Agent selfAgent) {
        this.thisAgent = selfAgent;
    }

    /**
     * Runs the decision procedure and returns the specified decision.
     * @return the specified decision
     */
    @Override
    public Choices run() {
        // return no choice if this agent has not been assigned to anything
        if (thisAgent == null) return Choices.NOCHOICE;

        // get this agent's last move
        Choices SelfLastMove = thisAgent.getChoice(thisAgent.numOfChoices() - 1);

        // get the opponent's last move
        Choices opponentLastMove;
        if (SelfLastMove == Choices.NOCHOICE) opponentLastMove = Choices.NOCHOICE;
        else opponentLastMove = inputs.get(0).getInputsNoEnd()[inputs.get(0).getInputsNoEnd().length - 2];

        // if the opponent hasn't moved yet or the last move was a tie, just move randomly
        if (opponentLastMove == Choices.NOCHOICE || SelfLastMove == opponentLastMove) {
            return Choices.getRandomChoice();
        }

        // if this agent lost last time, play the move that beats its opponent's last move
        if (thisAgent.getChoice(thisAgent.numOfChoices() - 1) != Evaluation.getWinningMove(opponentLastMove)) {
            return Evaluation.getWinningMove(opponentLastMove);
        }

        // otherwise, play the move that beats the move that beats this agent's last move
        return Evaluation.getWinningMove(Evaluation.getWinningMove(thisAgent.getChoice(thisAgent.numOfChoices() - 1)));
    }
}
