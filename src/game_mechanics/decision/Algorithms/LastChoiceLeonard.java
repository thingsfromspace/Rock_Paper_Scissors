package game_mechanics.decision.Algorithms;

import game_mechanics.AgentLeonard;
import game_mechanics.decision.ChoicesLeonard;
import game_mechanics.decision.DecisionAlgorithmLeonard;
import game_mechanics.decision.InputLeonard;
import gui.FilePaths;

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
public class LastChoiceLeonard extends DecisionAlgorithmLeonard {
    private static FilePaths paths = new FilePaths();

    /**
     * Constructs the LastChoiceLeonard decision algorithm
     *
     * @param opponent the opponent
     */
    public LastChoiceLeonard(AgentLeonard opponent) {
        super(new InputLeonard(opponent, 1, true));
    }

    /**
     * Constructs the LastChoiceLeonard decision algorithm with no opponent
     */
    public LastChoiceLeonard() {
    }

    /**
     * Returns the string to the Last Choice Icon
     *
     * @return the path to the icon image
     */
    public static String getIconStatic() {
        // return the path to the icon for this agent
        return paths.paths[7];
    }

    /**
     * Chooses the opponent's last choice
     * @return the opponent's last choice
     */
    @Override
    public ChoicesLeonard run() {
        // get the opponents previous decisions
        ChoicesLeonard[] previousDecision;
        previousDecision = inputs.get(0).getInputs();

        // return their previous decision, or a random decision if this agent is the first to move
        if (previousDecision[0] == ChoicesLeonard.NOCHOICE) return ChoicesLeonard.getRandomChoice();
        return previousDecision[0];
    }

    /**
     * Returns the string to the Last Choice Icon
     *
     * @return the path to the icon image
     */
    @Override
    public String getIcon() {
        // return the path to the icon for this agent
        return getIconStatic();
    }
}
