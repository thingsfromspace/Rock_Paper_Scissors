package gui.gui_elements.main_screen.play_arena;

import game_mechanics.AgentLeonard;
import game_mechanics.Evaluation.EvaluationLeonard;
import game_mechanics.decision.Algorithms.LastChoiceLeonard;
import game_mechanics.decision.Algorithms.RandomChoiceLeonard;
import game_mechanics.decision.Algorithms.TrickyChoiceLeonard;
import game_mechanics.decision.Algorithms.UserChoiceLeonard;
import game_mechanics.decision.ChoicesLeonard;
import gui.FilePathsLeonard;
import gui.gui_elements.main_screen.gamelog.GameHistoryGUILeonard;
import gui.gui_elements.main_screen.select_agent.AgentCatalogLeonard;
import gui.gui_elements.mdButtonLeonard;
import gui.gui_elements.options_screen.PreferencesLeonard;
import javafx.scene.Node;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.util.ArrayList;

/**
 * <h1>This class is the main arena for playing RCP</h1>
 * This class creates a main arena, which is where
 * the user will select moves and view the opponent's
 * moves.
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class MainArenaLeonard {
    public static ChoicesLeonard userChoice;
    // the system font
    private Font systemFont;
    // the color of the player buttons
    private Color playerColor = new Color(.97, .97, .97, 1);
    // a collection of all of the elements in the main arena
    private ArrayList<Node> elements = new ArrayList<>(0);
    // the scaling for player and choice icons
    private double playerImageScale = 0.185;
    private double choiceImageScale = 0.5;
    // the file paths used for the icons
    private FilePathsLeonard paths = new FilePathsLeonard();
    // stores the user's and opponent's most recent decisions
    private ChoicesLeonard userDecision;
    private ChoicesLeonard opposingDecision;
    // creates audio for when the user wins
    private String winningSoundPath = new File(paths.paths[12]).getAbsolutePath();
    public AudioClip winningSound = new AudioClip(new File(winningSoundPath).toURI().toString());
    // creates audio for when the user loses
    private String losingSoundPath = new File(paths.paths[11]).getAbsolutePath();
    public AudioClip losingSound = new AudioClip(new File(losingSoundPath).toURI().toString());
    // creates agents for the user and the opponent
    private AgentLeonard userAgent = new AgentLeonard(new UserChoiceLeonard(false));
    private AgentLeonard opposingAgent;

    // a collection that holds all of the player icons
    private ArrayList<String> playerIcons = new ArrayList<>(0);
    // a collection that holds all of the computer icons
    private ArrayList<String> computerIcons = new ArrayList<>(0);

    /**
     * Creates the Main Arena object
     *
     * @param systemFont the system font
     */
    public MainArenaLeonard(Font systemFont) {
        this.systemFont = systemFont;
    }

    /**
     * Creates and returns a new main arena
     *
     * @return a collection of objects comprising the main arena
     */
    public ArrayList<Node> getMainArena() {
        // all of the elements that we should return
        ArrayList<Node> elements = new ArrayList<>(0);

        // turn down the winning sound, because it is very loud
        winningSound.volumeProperty().setValue(0.5);

        // CREATE ICON BUTTON FOR THE OPPOSING PLAYER
        mdButtonLeonard opponentButton = new mdButtonLeonard("opponent", 100, 100, 525, 25);
        opponentButton.isButton = false;
        opponentButton.setButtonColor(playerColor);
        opponentButton.setAnimation(false);
        opponentButton.setDropShadow();
        this.elements.add(opponentButton);
        // the opponent is currently not selected, so there is no image to show
        this.elements.add(opponentButton.addText("select\nan\nagent", new Font(systemFont.getFamily(), 20),
                Color.BLACK));

        // CREATE CHOICE BUTTON FOR THE OPPOSING PLAYER
        mdButtonLeonard computerButton = new mdButtonLeonard("computer button", 150, 150, 500, 150);
        computerButton.setButtonColor(playerColor);
        computerButton.isButton = false;
        computerButton.setButtonColor(Color.DEEPSKYBLUE);
        computerButton.setAnimation(false);
        computerButton.setDropShadow();
        this.elements.add(computerButton);
        this.elements.add(computerButton.addImage(new File(paths.paths[3]).getAbsolutePath(),
                choiceImageScale, choiceImageScale));

        // create option buttons for each choice

        // ROCK BUTTON
        mdButtonLeonard rockChoice = new mdButtonLeonard("rock", 150, 150, 325, 327.5);
        rockChoice.setButtonColor(playerColor);
        rockChoice.setAnimation(true);
        rockChoice.setDropShadow();
        rockChoice.setButtonColor(Color.LIGHTGREEN);
        this.elements.add(rockChoice);
        this.elements.add(rockChoice.addImage(new File(ChoicesLeonard.ROCK.getImageString()).getAbsolutePath(),
                choiceImageScale, choiceImageScale));

        // PAPER BUTTON
        mdButtonLeonard paperChoice = new mdButtonLeonard("paper", 150, 150, 500, 327.5);
        paperChoice.setButtonColor(playerColor);
        paperChoice.setAnimation(true);
        paperChoice.setDropShadow();
        paperChoice.setButtonColor(Color.LIGHTGREEN);
        this.elements.add(paperChoice);
        this.elements.add(paperChoice.addImage(new File(ChoicesLeonard.PAPER.getImageString()).getAbsolutePath(),
                choiceImageScale, choiceImageScale));

        // SCISSORS BUTTON
        mdButtonLeonard scissorsChoice = new mdButtonLeonard("scissors", 150, 150, 675, 327.5);
        scissorsChoice.setButtonColor(playerColor);
        scissorsChoice.setAnimation(true);
        scissorsChoice.setDropShadow();
        scissorsChoice.setButtonColor(Color.LIGHTGREEN);
        this.elements.add(scissorsChoice);
        this.elements.add(scissorsChoice.addImage(new File(ChoicesLeonard.SCISSORS.getImageString()).getAbsolutePath(),
                choiceImageScale, choiceImageScale));

        // CREATE ICON BUTTON FOR USER
        mdButtonLeonard userButton = new mdButtonLeonard("user", 100, 100, 525, 505);
        userButton.isButton = false;
        userButton.setButtonColor(playerColor);
        userButton.setAnimation(false);
        userButton.setDropShadow();
        this.elements.add(userButton);
        this.elements.add(userButton.addImage(new File(UserChoiceLeonard.getIconStatic()).getAbsolutePath(), playerImageScale,
                playerImageScale));

        // add all of the elements to this class
        elements.addAll(this.elements);
        // return the elements
        return elements;
    }

    /**
     * This refreshes all of the elements and returns them.
     * This method only needs to be run when the user clicks the screen.
     *
     * @param gameHistory the game history gui
     * @return an updated array list of nodes
     */
    public ArrayList<Node> refresh(GameHistoryGUILeonard gameHistory) {
        // stores the new elements
        ArrayList<Node> elements = new ArrayList<>(0);
        // add all of the game history elements to the array
        elements.addAll(gameHistory.getElements());

        // check to see if the user has selected an agent
        if (AgentCatalogLeonard.agentSelected != null) {
            // set the agent appropriately
            selectAgent();

            // add the new player icons
            playerIcons.add(new File(userAgent.getDecisionAlgorithm().getIcon()).getAbsolutePath());
            computerIcons.add(new File(opposingAgent.getDecisionAlgorithm().getIcon()).getAbsolutePath());

            try {
                // if rock was selected
                if (((mdButtonLeonard) this.elements.get(4)).timesClicked > 0) {
                    // update, given that the user has chosen rock
                    updateAfterMove(ChoicesLeonard.ROCK);
                    // reset the clicks to zero
                    ((mdButtonLeonard) this.elements.get(4)).timesClicked = 0;
                    // add a new move to the game log
                    gameHistory.addNewMove(playerIcons,
                            computerIcons, userDecision, opposingDecision);

                } else if (((mdButtonLeonard) this.elements.get(6)).timesClicked > 0) { // if paper was selected
                    // update, given that the user has chosen paper
                    updateAfterMove(ChoicesLeonard.PAPER);
                    // reset the clicks to zero
                    ((mdButtonLeonard) this.elements.get(6)).timesClicked = 0;
                    // add a new move to the game log
                    gameHistory.addNewMove(playerIcons,
                            computerIcons, userDecision, opposingDecision);

                } else if (((mdButtonLeonard) this.elements.get(8)).timesClicked > 0) { // if scissors was selected
                    // update, given that the user has chosen scissors
                    updateAfterMove(ChoicesLeonard.SCISSORS);
                    // reset the clicks to zero
                    ((mdButtonLeonard) this.elements.get(4)).timesClicked = 0;
                    // add a new move to the game log
                    gameHistory.addNewMove(playerIcons,
                            computerIcons, userDecision, opposingDecision);

                }
            } catch (ClassCastException e) {
            }
        } else {
            // the user hasn't set the agent, so their choices don't matter
            for (Node element : this.elements) {
                try {
                    // set all of the clicks to zero
                    ((mdButtonLeonard) element).timesClicked = 0;
                } catch (Exception e) {
                }
            }
        }

        // add all of the elements to the array list
        elements.addAll(this.elements);
        // return the elements
        return elements;
    }

    /**
     * Selects the appropriate agent, given that one has been clicked
     */
    private void selectAgent() {
        // store the icon path to the appropriate agent
        String iconPath = AgentCatalogLeonard.agentSelected.getIcon();
        // get the index of the agent image
        int imageIndex = elements.size() - 12;
        // get the Node that corresponds to the image
        Node newImage = this.elements.get(imageIndex);

        // make sure that we are dealing with an mdButton
        if (newImage.getClass().equals(mdButtonLeonard.class)) {
            // remove the old agent icon
            this.elements.remove(imageIndex + 1);
            // add the new agent icon
            this.elements.add(imageIndex + 1, ((mdButtonLeonard) newImage).addImage(iconPath, playerImageScale, playerImageScale));

            try {
                // if the agent hasn't been set or the current agent has changed
                if (opposingAgent == null || !AgentCatalogLeonard.agentSelected.getClass().equals(opposingAgent.getDecisionAlgorithm().getClass())) {
                    if (AgentCatalogLeonard.agentSelected.getClass().equals(LastChoiceLeonard.class)) {
                        // set the agent to be a last choice agent
                        opposingAgent = new AgentLeonard(new LastChoiceLeonard(userAgent));
                    } else if (AgentCatalogLeonard.agentSelected.getClass().equals(TrickyChoiceLeonard.class)) {
                        // set the agent to be a tricky choice agent
                        opposingAgent = new AgentLeonard(new TrickyChoiceLeonard(userAgent));
                    } else if (AgentCatalogLeonard.agentSelected.getClass().equals(RandomChoiceLeonard.class)) {
                        // set the agent to be a random choice agent
                        opposingAgent = new AgentLeonard(new RandomChoiceLeonard());
                    }
                }
            } catch (NullPointerException e) {
            }
        }
    }

    /**
     * Update the main arena after a new move has been made
     *
     * @param choice
     */
    private void updateAfterMove(ChoicesLeonard choice) {
        // store the user's choice
        userChoice = choice;

        // if the agent is a tricky choice agent, we need to set the self agent
        if (AgentCatalogLeonard.agentSelected.getClass().equals(TrickyChoiceLeonard.class)) {
            ((TrickyChoiceLeonard) opposingAgent.getDecisionAlgorithm()).setSelf(opposingAgent);
        }

        // store the opponent's decision
        opposingDecision = opposingAgent.makeDecision();

        // obtain the user's decision
        userDecision = userAgent.makeDecision();

        // display the opponent's decision
        this.elements.remove(3);
        this.elements.add(3, ((mdButtonLeonard) this.elements.get(2)).addImage(opposingDecision.getImageString(),
                choiceImageScale, choiceImageScale));


        // set the button color depending on whether the user won, lost, or tied
        ((mdButtonLeonard) this.elements.get(2)).setButtonColor(EvaluationLeonard.isWinning(userDecision, opposingDecision) ? Color.LIGHTGREEN :
                EvaluationLeonard.isLosing(userDecision, opposingDecision) ? Color.RED : Color.BLUE);

        // play sound effects if they are turned on
        if (PreferencesLeonard.preferences[2]) {
            // game sound effects are turned on
            if (EvaluationLeonard.isWinning(userDecision, opposingDecision)) {
                // if the user has won, play the winning sound effect
                winningSound.play();
            } else if (EvaluationLeonard.isLosing(userDecision, opposingDecision)) {
                // if the user has lost, play the losing sound effect
                losingSound.play();
            }
        }

        // reset the clicks to zero
        ((mdButtonLeonard) this.elements.get(2)).timesClicked = 0;
    }
}
