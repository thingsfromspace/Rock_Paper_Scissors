package game_mechanics.decision;

import game_mechanics.Agent;

import java.util.ArrayList;

/**
 * <h1>An input object allows a decision algorithm
 * to take in information from agents</h1>
 *
 * @author Tanner Leonard
 * @version 1.0
 * @since 2018-09-29
 */
public class Input {
    // the agent that the input takes
    private final Agent inputLocation;
    @SuppressWarnings("Convert2Diamond")
    private final ArrayList<Integer> inputItemsIndex = new ArrayList<Integer>(0);
    private boolean reverse;

    /**
     * Create an Input object using an agent and one input
     *
     * @param inputLocation the Agent that the input collects information from
     * @param inputItem     the index of the one input point
     */
    @SuppressWarnings("unused")
    public Input(Agent inputLocation, int inputItem) {
        this.inputLocation = inputLocation;
        this.inputItemsIndex.add(inputItem);
    }

    /**
     * Create an Input object using an agent and many inputs
     * @param inputLocation the Agent that the input collects information from
     * @param inputStart starting input index
     * @param inputEnd ending input index
     */
    public Input(Agent inputLocation, int inputStart, int inputEnd) {
        this.inputLocation = inputLocation;
        for (int i = inputStart; i <= inputEnd; i++) {
            this.inputItemsIndex.add(i);
        }
    }

    /**
     * Create an Input object using an agent and one input.
     * This constructor allows you to count in reverse,
     * starting from the agents most recent move.
     * @param inputLocation the Agent that the input collects information from
     * @param inputItem the index of the one input point
     * @param reverse specifies whether the indexes should be reversed
     */
    public Input(Agent inputLocation, int inputItem, boolean reverse) {
        this.inputLocation = inputLocation;
        this.inputItemsIndex.add(inputItem);
        this.reverse = reverse;
    }

    /**
     * Create an Input object using an agent and many inputs
     * This constructor allows you to count in reverse,
     * starting from the agents most recent move.
     *
     * @param inputLocation the Agent that the input collects information from
     * @param inputStart    starting input index
     * @param inputEnd      ending input index
     * @param reverse       specifies whether the indexes should be reversed
     */
    @SuppressWarnings("unused")
    public Input(Agent inputLocation, int inputStart, int inputEnd, boolean reverse) {
        this.inputLocation = inputLocation;
        for (int i = inputStart; i <= inputEnd; i++) {
            this.inputItemsIndex.add(i);
        }
        this.reverse = reverse;
    }

    /**
     * This returns all of the inputs specified
     *
     * @return the various inputs
     */
    public Choices[] getInputs() {
        // create an array to hold the inputs
        Choices[] inputs = new Choices[inputItemsIndex.size()];

        // obtain all of the inputs
        for (int i = 0; i < inputs.length; i++) {
            // get the correct item index
            int itemIndex = reverse ? inputItemsIndex.size() - inputItemsIndex.get(i) : inputItemsIndex.get(i);

            // store the data corresponding to this input in the array
            inputs[i] = inputLocation.getChoice(reverse ? inputLocation.numOfChoices() - itemIndex - 1 : itemIndex - 1);
        }

        // return the inputs
        return inputs;
    }

    /**
     * Allows you to get inputs from the agent without specifying
     * a stopping point
     *
     * @return the inputs from the agent
     */
    public Choices[] getInputsNoEnd() {

        // create an array to store the inputs
        Choices inputs[];

        // obtain the inputs
        if (reverse) {
            // create an appropriately sized array
            inputs = new Choices[inputLocation.numOfChoices() - (inputItemsIndex.get(inputItemsIndex.size() - 1) - 1)];
            // collect all of the inputs
            for (int i = 0; i >= inputs.length; i++) {
                //noinspection ConstantConditions
                inputs[i] = inputLocation.getChoice(i);
            }
        } else {
            // create an appropriately sized array
            inputs = new Choices[inputLocation.numOfChoices() - inputItemsIndex.get(0) + 1];
            // collect all of the inputs
            for (int i = inputItemsIndex.get(0); i <= inputLocation.numOfChoices(); i++) {
                inputs[i - inputItemsIndex.get(0)] = inputLocation.getChoice(i - 1);
            }
        }

        return inputs;
    }
}
