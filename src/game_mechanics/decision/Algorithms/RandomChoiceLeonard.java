package game_mechanics.decision.Algorithms;

import game_mechanics.decision.ChoicesLeonard;
import game_mechanics.decision.DecisionAlgorithmLeonard;
import gui.FilePaths;

/**
 * <h1>Chooses randomly</h1>
 * This decision algorithm chooses its
 * decision randomly
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class RandomChoiceLeonard extends DecisionAlgorithmLeonard {
    private static FilePaths paths = new FilePaths();

    /**
     * Create the RandomChoiceLeonard decision algorithm
     */
    public RandomChoiceLeonard() {
        super();
    }

    /**
     * Returns the string to the Random Choice Icon
     *
     * @return the path to the icon image
     */
    public static String getIconStatic() {
        // return the path to the icon for this agent
        return paths.paths[8];
    }

    /**
     * Make a random choice
     *
     * @return a random choice
     */
    @Override
    public ChoicesLeonard run() {
        // make a random decision
        return ChoicesLeonard.getRandomChoice();
    }

    /**
     * Returns the string to the Random Choice Icon
     *
     * @return the path to the icon image
     */
    @Override
    public String getIcon() {

        // return the path to the icon for this agent
        return getIconStatic();
    }
}
