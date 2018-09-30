package game_mechanics.Evaluation;

import game_mechanics.decision.Choices;

/**
 * <h1>Provides tools for evaluating RCP games</h1>
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class Evaluation {
    private Evaluation() {
    }

    /**
     * returns the winning move in reference to
     * another move
     *
     * @param opponentChoice the opponent's choice
     * @return the winning move in this scenario
     */
    public static Choices getWinningMove(Choices opponentChoice) {
        int num = (opponentChoice.getTypeNum() - 1) % 3;
        return Choices.getChoiceFromNumber(num == 0 ? 3 : num);
    }
}
