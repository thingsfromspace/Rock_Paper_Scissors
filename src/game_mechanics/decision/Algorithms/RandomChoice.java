package game_mechanics.decision.Algorithms;

import game_mechanics.decision.Choices;
import game_mechanics.decision.DecisionAlgorithm;

/**
 * <h1>Chooses randomly</h1>
 * This decision algorithm chooses its
 * decision randomly
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class RandomChoice extends DecisionAlgorithm {
    /**
     * Create the RandomChoice decision algorithm
     */
    public RandomChoice() {
        super();
    }

    /**
     * Make a random choice
     *
     * @return a random choice
     */
    @Override
    public Choices run() {
        return Choices.getRandomChoice();
    }

    /**
     * Returns the string to the Random Choice Icon
     *
     * @return the path to the icon image
     */
    public static String getIcon() {
        return "src/gui/gui_elements/Assets/red_circle.png";
    }
}
