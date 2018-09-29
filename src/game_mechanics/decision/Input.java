package game_mechanics.decision;

import game_mechanics.Agent;

import java.util.ArrayList;

public class Input {
    private Agent inputLocation;
    private ArrayList<Integer> inputItemsIndex = new ArrayList<Integer>(0);
    private boolean reverse;

    public Choices[] getInputs() {
        Choices[] inputs = new Choices[inputItemsIndex.size()];
        for(int i = 0; i < inputs.length; i++) {
            int itemIndex = reverse ? inputItemsIndex.size() - inputItemsIndex.get(i) : inputItemsIndex.get(i);

            inputs[i] = inputLocation.getChoice(reverse ? inputLocation.numOfChoices() - itemIndex - 1: itemIndex - 1);
        }

        return inputs;
    }

    public Choices[] getInputs(boolean noEnd) {

        Choices inputs[];

        if (!noEnd) {
            inputs = new Choices[inputItemsIndex.get(inputItemsIndex.size() - 1)];
            for(int i = 0; i < inputs.length; i++) {
                inputs[i] = inputLocation.getChoice(i);
            }
        } else {
            inputs = new Choices[inputLocation.numOfChoices() - inputItemsIndex.get(0) + 1];
            for(int i = inputItemsIndex.get(0); i <= inputLocation.numOfChoices(); i++) {
                inputs[i - inputItemsIndex.get(0)] = inputLocation.getChoice(i);
            }
        }

        return inputs;
    }

    public Input(Agent inputLocation, int inputItem) {
        this.inputLocation = inputLocation;
        this.inputItemsIndex.add(inputItem);
    }

    public Input(Agent inputLocation, int inputStart, int inputEnd) {
        this.inputLocation = inputLocation;
        for (int i = inputStart; i <= inputEnd; i++) {
            this.inputItemsIndex.add(i);
        }
    }

    public Input(Agent inputLocation, int inputItem, boolean reverse) {
        this.inputLocation = inputLocation;
        this.inputItemsIndex.add(inputItem);
        this.reverse = reverse;
    }

    public Input(Agent inputLocation, int inputStart, int inputEnd, boolean reverse) {
        this.inputLocation = inputLocation;
        for (int i = inputStart; i <= inputEnd; i++) {
            this.inputItemsIndex.add(i);
        }
        this.reverse = reverse;
    }
}
