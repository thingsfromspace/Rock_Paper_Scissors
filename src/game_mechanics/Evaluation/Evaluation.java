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

    /**
     * Determines whether choice1 wins against choice2.
     *
     * @param choice1 the first choice
     * @param choice2 the second choice
     * @return a boolean representing the question "Does choice 1 win against choice 2?"
     */
    public static boolean isWinning(Choices choice1, Choices choice2) {
        return choice1.getTypeNum() - choice2.getTypeNum() == -1 || (choice1.getTypeNum() == 3 && choice2.getTypeNum() == 1);
    }

    /**
     * Determines whether choice1 loses against choice2.
     *
     * @param choice1 the first choice
     * @param choice2 the second choice
     * @return a boolean representing the question "Does choice 1 lose against choice 2?"
     */
    public static boolean isLosing(Choices choice1, Choices choice2) {
        return choice1.getTypeNum() - choice2.getTypeNum() == 1 || (choice1.getTypeNum() == 1 && choice2.getTypeNum() == 3);
    }
}
