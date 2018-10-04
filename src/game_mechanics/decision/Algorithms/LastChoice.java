package game_mechanics.decision.Algorithms;

import game_mechanics.Agent;
import game_mechanics.decision.Choices;
import game_mechanics.decision.DecisionAlgorithm;
import game_mechanics.decision.Input;

/**
 * <h1>Chooses the opponent's last choice</h1>
 * This decision algorithm simply chooses
 * its opponent's last choice. If the opponent
 * has not moved yet, then this algorithm will
 * chose randomly.
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class LastChoice extends DecisionAlgorithm {
    /**
     * Constructs the LastChoice decision algorithm
     *
     * @param opponent the opponent
     */
    public LastChoice(Agent opponent) {
        super(new Input(opponent, 1, true));
    }

    /**
     * Chooses the opponent's last choice
     * @return the opponent's last choice
     */
    @Override
    public Choices run() {
        // get the opponents previous decisions
        Choices[] previousDecision;
        previousDecision = inputs.get(0).getInputs();

        // return their previous decision, or a random decision if this agent is the first to move
        if (previousDecision[0] == Choices.NOCHOICE) return Choices.getRandomChoice();
        return previousDecision[0];
    }

    /**
     * Returns the string to the Last Choice Icon
     *
     * @return the path to the icon image
     */
    public static String getIcon() {
        return "src/gui/gui_elements/Assets/green_square.png";
    }
}
