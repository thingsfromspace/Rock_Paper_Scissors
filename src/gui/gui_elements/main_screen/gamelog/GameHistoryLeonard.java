package gui.gui_elements.main_screen.gamelog;

import game_mechanics.decision.ChoicesLeonard;

import java.util.ArrayList;

/**
 * <h1>Stores the game history</h1>
 * This class stores the game history of a game.
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class GameHistoryLeonard {
    // holds the user's move history
    private ArrayList<ChoicesLeonard> userHistory = new ArrayList<>(0);
    // holds the computer's move history
    private ArrayList<ChoicesLeonard> computerHistory = new ArrayList<>(0);

    /**
     * Add a move to the log
     *
     * @param userMove     the user's move
     * @param computerMove the computer's move
     */
    public void addMove(ChoicesLeonard userMove, ChoicesLeonard computerMove) {
        userHistory.add(userMove);
        computerHistory.add(computerMove);
    }

    /**
     * This returns the user's move
     *
     * @param indexFromLast the index of the user's move, counting from the most recent move
     * @return the user's indexFromLast'th move
     */
    public ChoicesLeonard getUserMove(int indexFromLast) {
        return userHistory.get(userHistory.size() - indexFromLast);
    }

    /**
     * This returns the computer's move
     *
     * @param indexFromLast the index of the computer's move, counting from the most recent move
     * @return the computer's indexFromLast'th move
     */
    public ChoicesLeonard getComputerMove(int indexFromLast) {
        return computerHistory.get(computerHistory.size() - indexFromLast);
    }

    /**
     * This returns the number of moves made
     *
     * @return the number of moves made
     */
    public int getNumOfMoves() {
        return userHistory.size();
    }
}
