package gui.gui_elements.main_screen.gamelog;

import game_mechanics.decision.Choices;

import java.util.ArrayList;

public class GameHistory {
    private ArrayList<Choices> userHistory = new ArrayList<>(0);
    private ArrayList<Choices> computerHistory = new ArrayList<>(0);

    public void addMove(Choices userMove, Choices computerMove) {
        userHistory.add(userMove);
        computerHistory.add(computerMove);
    }

    public Choices getUserMove(int indexFromLast) {
        return userHistory.get(userHistory.size() - indexFromLast);
    }

    public Choices getComputerMove(int indexFromLast) {
        return computerHistory.get(computerHistory.size() - indexFromLast);
    }

    public int getNumOfMoves() {
        return userHistory.size();
    }
}
