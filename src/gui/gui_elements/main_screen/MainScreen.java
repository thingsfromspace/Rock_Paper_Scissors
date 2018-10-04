package gui.gui_elements.main_screen;

import game_mechanics.Agent;
import game_mechanics.decision.Algorithms.LastChoice;
import game_mechanics.decision.Algorithms.RandomChoice;
import game_mechanics.decision.Choices;
import gui.gui_elements.main_screen.gamelog.GameHistoryGUI;
import javafx.scene.Node;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class MainScreen {
    private double screenWidth;
    private double screenHeight;
    private Font systemFont;
    private ArrayList<Agent> agents;
    private GameHistoryGUI gameHistory;

    public MainScreen(double screenWidth, double screenHeight, Font systemFont, Agent... agents) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.systemFont = systemFont;

        for (Agent agent : agents) this.agents.add(agent);
    }

    public ArrayList<Node> getMainScreen(Node parent) {
        gameHistory = new GameHistoryGUI(screenWidth, screenHeight, systemFont, parent);
        gameHistory.getGameHistoryGUI();

        for (int i = 0; i < 25; i++) {
            gameHistory.addNewMove(LastChoice.getIcon(),
                    RandomChoice.getIcon(), Choices.ROCK, Choices.ROCK);
        }
        return gameHistory.getElements();
    }

    public ArrayList<ArrayList<Node>> refreshElements(GameHistoryGUI gameHistory) {
        ArrayList<ArrayList<Node>> elements = new ArrayList<>();
        elements.add(gameHistory.getElements());

        return elements;
    }
}
