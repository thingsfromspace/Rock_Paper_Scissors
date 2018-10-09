package gui.gui_elements.main_screen.select_agent;

import game_mechanics.decision.Algorithms.LastChoiceLeonard;
import game_mechanics.decision.Algorithms.RandomChoiceLeonard;
import game_mechanics.decision.Algorithms.TrickyChoiceLeonard;
import game_mechanics.decision.DecisionAlgorithmLeonard;
import gui.gui_elements.mdButtonLeonard;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.util.ArrayList;

/**
 * <h1>A catalog which allows the user to select an agent</h1>
 * This class creates a catalog of possible agents which the
 * user can select from to play against.
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class AgentCatalogLeonard {
    // this holds the decision algorithm of the current agent selected
    public static DecisionAlgorithmLeonard agentSelected;
    // the system font
    private Font systemFont;
    // this holds the possible decision algorithms
    private ArrayList<DecisionAlgorithmLeonard> algorithms = new ArrayList<DecisionAlgorithmLeonard>(0);
    // this holds all of the elements for the agent catalog
    private ArrayList<Node> elements = new ArrayList<>(0);
    // this holds all of the agent icons for the agent catalog
    private ArrayList<mdButtonLeonard> icons = new ArrayList<>(0);

    /**
     * Constructs an Agent Catalog object
     *
     * @param systemFont the font used for the catalog
     */
    public AgentCatalogLeonard(Font systemFont) {
        this.systemFont = systemFont;

        // add the three decision algorithms to the class
        algorithms.add(new RandomChoiceLeonard());
        algorithms.add(new TrickyChoiceLeonard());
        algorithms.add(new LastChoiceLeonard());
    }

    /**
     * This construct a new agent catalog
     *
     * @return an array list of nodes comprising the agent catalog
     */
    public ArrayList<Node> getAgentCatalog() {
        // create a structure to hold all of the elements
        ArrayList<Node> elements = new ArrayList<>(0);

        // the scale for the decision algorithm icons
        double algorithmImageScale = .3;

        // CREATE THE BACKGROUND BUTTON
        mdButtonLeonard background = new mdButtonLeonard("AgentLeonard catalog background", 170, 670,
                25, 25);
        background.setDropShadow();
        background.setButtonColor(Color.LIGHTGRAY);
        background.isButton = false;
        this.elements.add(background);

        // CREATE THE TITLE BUTTON
        mdButtonLeonard title = new mdButtonLeonard("AgentLeonard Catalog", 170, 45,
                25, 25);
        title.setButtonColor(Color.WHITE);
        title.isButton = false;
        this.elements.add(title);
        this.elements.add(title.addText("agent catalog", new Font(systemFont.getFamily(), 20), Color.BLACK));

        // loop through all of the decision algorithms
        for (int i = 0; i < algorithms.size(); i++) {
            // CONSTRUCT A NEW BUTTON FOR EACH DECISION ALGORITHM
            mdButtonLeonard agentImage = new mdButtonLeonard("Algorithm Image" + i, 150, 150,
                    32.5, 30 + 45 + ((double) i) * 160);
            agentImage.setDropShadow();
            agentImage.isButton = true;
            agentImage.setAnimation(true);
            agentImage.setButtonColor(new Color(.92, .92, .92, 1));

            // add the nodes to this class
            this.elements.add(agentImage);
            this.elements.add(agentImage.addImage(new File(algorithms.get(i).getIcon()).getAbsolutePath(),
                    algorithmImageScale, algorithmImageScale));

            // add the icons to this class
            this.icons.add(agentImage);
        }

        elements.addAll(this.elements);
        return elements;
    }

    /**
     * This selects a certain agent in the agent catalog
     *
     * @param algorithm the algorithm which is being selected
     */
    private void selectAgent(DecisionAlgorithmLeonard algorithm) {
        // loop through all of the decision algorithms
        for (int i = 0; i < algorithms.size(); i++) {
            // check if the algorithms are equal using their icons
            if (algorithms.get(i).getIcon().equals(algorithm.getIcon())) {
                // set the selected icon button color to green
                icons.get(i).setButtonColor(Color.LAWNGREEN);
                // set the agent selected
                agentSelected = algorithms.get(i);
            } else {
                // set other icon button colors to gray
                icons.get(i).setButtonColor(new Color(.92, .92, .92, 1));
            }
        }
    }

    /**
     * This method refreshes the agent catalog to respond to any new events
     *
     * @return an array of nodes that comprise the agent catalog
     */
    public ArrayList<Node> refresh() {
        // loop through all of the algorithms
        for (int i = 0; i < algorithms.size(); i++) {
            try {
                // get a specified agent
                mdButtonLeonard agent = ((mdButtonLeonard) elements.get(3 + i * 2));

                // if the agent has been clicked
                if (agent.timesClicked > 0) {
                    // reset the agent clicks
                    agent.timesClicked = 0;
                    // select the agent
                    selectAgent(algorithms.get(i));
                }
            } catch (ClassCastException e) {
            }
        }
        // return the updated elements
        return elements;
    }
}
