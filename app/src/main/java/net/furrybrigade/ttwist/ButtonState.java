package net.furrybrigade.ttwist;

public class ButtonState {
    String label;
    boolean state = true;
    int selectionIndex = -1;

    ButtonState(String label) {
        this.label = label;
    }

    void toggleState(int selectionIndex){
        state = !state;
        if(!state)
            this.selectionIndex = selectionIndex;
        else
            this.selectionIndex = -1;
    }

    void clearState(){
        state = true;
        selectionIndex = -1;
    }
}
