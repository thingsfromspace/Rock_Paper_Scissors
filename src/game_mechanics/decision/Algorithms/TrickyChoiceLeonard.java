package game_mechanics.decision.Algorithms;

import game_mechanics.AgentLeonard;
import game_mechanics.Evaluation.EvaluationLeonard;
import game_mechanics.decision.ChoicesLeonard;
import game_mechanics.decision.DecisionAlgorithmLeonard;
import game_mechanics.decision.InputLeonard;
import gui.FilePathsLeonard;

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
public class TrickyChoiceLeonard extends DecisionAlgorithmLeonard {
    private static FilePathsLeonard paths = new FilePathsLeonard();
    private AgentLeonard thisAgent;
    private ChoicesLeonard opponentLastMove;

    /**
     * Constructs a Decision Algorithm in reference
     * to the opponent
     *
     * @param opponent the algorithm's opponent
     */
    public TrickyChoiceLeonard(AgentLeonard opponent) {
        super(new InputLeonard(opponent, 1));
    }

    /**
     * Constructs a Tricky Choice Decision algorithm
     */
    public TrickyChoiceLeonard() {
    }

    /**
     * Returns the string to the Tricky Choice Icon
     *
     * @return the path to the icon image
     */
    public static String getIconStatic() {
        // return the path to the icon for this agent
        return paths.paths[9];
    }

    /**
     * This allows the agent to know it's own past
     * decisions. IT MUST be run before making decisions
     * with this agent
     * @param selfAgent the agent that this algorithm is implemented in
     */
    public void setSelf(AgentLeonard selfAgent) {
        this.thisAgent = selfAgent;
    }

    /**
     * Runs the decision procedure and returns the specified decision.
     * @return the specified decision
     */
    @Override
    public ChoicesLeonard run() {
        // has this decision algorithm been assigned to an agent?
        if (thisAgent == null) {
            // return no choice if this agent has not been assigned to anything
            thisAgent.priorDecisions.add(ChoicesLeonard.NOCHOICE);
            return ChoicesLeonard.NOCHOICE;
        }

        // get this agent's last move
        ChoicesLeonard SelfLastMove = thisAgent.getChoice(thisAgent.numOfChoices() - 1);

        // is this the first move ?
        if (SelfLastMove == ChoicesLeonard.NOCHOICE) {
            // if this is the first move, than the opponent has not make a previous move
            opponentLastMove = ChoicesLeonard.NOCHOICE;

        } else {
            // obtain the opponent's previous move
            opponentLastMove = inputs.get(0).getInputsNoEnd()[inputs.get(0).getInputsNoEnd().length - 1];
        }

        // if the opponent hasn't moved yet or the last move was a tie, just move randomly
        if (opponentLastMove == ChoicesLeonard.NOCHOICE || SelfLastMove == opponentLastMove) {
            // add a random move
            thisAgent.priorDecisions.add(ChoicesLeonard.getRandomChoice());
            return ChoicesLeonard.getRandomChoice();
        }

        // if this agent lost last time, play the move that beats its opponent's last move
        if (thisAgent.getChoice(thisAgent.numOfChoices() - 1) != EvaluationLeonard.getWinningMove(opponentLastMove)) {
            // get the winning move to this agent's last move
            thisAgent.priorDecisions.add(EvaluationLeonard.getWinningMove(opponentLastMove));
            return EvaluationLeonard.getWinningMove(opponentLastMove);
        }

        // otherwise, play the move that beats the move that beats this agent's last move
        thisAgent.priorDecisions.add(EvaluationLeonard.getWinningMove(EvaluationLeonard.getWinningMove(
                thisAgent.getChoice(thisAgent.numOfChoices() - 1))));
        return EvaluationLeonard.getWinningMove(EvaluationLeonard.getWinningMove(thisAgent.getChoice(thisAgent.numOfChoices() - 2)));
    }

    /**
     * Returns the string to the Tricky Choice Icon
     *
     * @return the path to the icon image
     */
    @Override
    public String getIcon() {
        // return the path to the icon for this agent
        return getIconStatic();
    }
}
