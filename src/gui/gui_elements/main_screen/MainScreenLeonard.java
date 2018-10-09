package gui.gui_elements.main_screen;

import gui.gui_elements.main_screen.gamelog.GameHistoryGUILeonard;
import gui.gui_elements.main_screen.play_arena.MainArenaLeonard;
import gui.gui_elements.main_screen.select_agent.AgentCatalogLeonard;
import gui.gui_elements.mdButtonLeonard;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 * <h1>This controls the main screen, and mediates
 * interactions between other display elements</h1>
 * This class is used to control the main play screen.
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class MainScreenLeonard {
    // the system font
    private Font systemFont;

    // the game history log
    private GameHistoryGUILeonard gameHistory;
    // the agent catalog
    private AgentCatalogLeonard agentCatalog;
    // the main arena
    private MainArenaLeonard mainArena;
    // all of the elements in the main screen
    private ArrayList<Node> elements = new ArrayList<>(0);

    /**
     * Constructs a new Main Screen object
     *
     * @param systemFont the system font
     */
    public MainScreenLeonard(Font systemFont) {
        this.systemFont = systemFont;

        // create a new game log
        this.gameHistory = new GameHistoryGUILeonard(systemFont);
        this.gameHistory.getGameHistoryGUI();

        // create a new agent catalog
        this.agentCatalog = new AgentCatalogLeonard(systemFont);
        this.agentCatalog.getAgentCatalog();

        // create a new main arena
        this.mainArena = new MainArenaLeonard(systemFont);
        this.mainArena.getMainArena();
    }

    /**
     * This creates a new main screen.
     *
     * @return an array list of nodes that comprise the main screen
     */
    public ArrayList<Node> getMainScreen() {
        // create a collection to hold the elements
        ArrayList<Node> elements = new ArrayList<>(0);

        // store the elements from the game log, agent catalog, and main arena
        elements.addAll(this.gameHistory.getElements());
        elements.addAll(this.agentCatalog.refresh());
        elements.addAll(this.mainArena.refresh(this.gameHistory));

        // ADD A BACK BUTTON
        mdButtonLeonard backButton = new mdButtonLeonard("back", 560, 40, 295, 655);
        backButton.setAnimation(true);
        backButton.setDropShadow();
        backButton.setButtonColor(Color.LIGHTGRAY);
        backButton.isButton = true;

        // add the back button to this class
        this.elements.add(backButton);
        this.elements.add(backButton.addText("back",
                new Font(systemFont.getFamily(), 20), Color.BLACK));

        // add all elements to the array
        elements.addAll(this.elements);
        // return the elements
        return elements;
    }

    /**
     * This refreshes the main screen
     *
     * @return an array list of nodes that comprise the main screen
     */
    public ArrayList<Node> refresh() {
        // create a collection to hold the elements
        ArrayList<Node> elements = new ArrayList<>(0);

        // update all of the elements of the main screen
        elements.addAll(agentCatalog.refresh());
        elements.addAll(mainArena.refresh(gameHistory));
        elements.addAll(this.elements);

        // return the updated elements
        return elements;
    }
}
