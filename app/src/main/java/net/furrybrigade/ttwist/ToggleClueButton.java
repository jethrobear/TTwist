package net.furrybrigade.ttwist;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatToggleButton;

public class ToggleClueButton extends AppCompatToggleButton {
    public int selectionIndex;

    public ToggleClueButton(@NonNull Context context) {
        super(context);
        clearState();
    }

    public ToggleClueButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        clearState();
    }

    public ToggleClueButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        clearState();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        setTextOn(text);
        setTextOff(text);
    }

    public void setSelectionIndex(int selectionIndex){
        this.selectionIndex = selectionIndex;
    }

    void clearState(){
        setChecked(false);
        selectionIndex = -1;
    }

    String serializeState(){
        String isChecked = isChecked() ? "T" : "F";
        String label = getText().toString();
        String selectionIndex = String.valueOf(this.selectionIndex);
        return isChecked + label + selectionIndex;
    }

    void deserializeState(String serializedState){
        char[] chx = serializedState.toCharArray();
        setChecked(chx[0] == 'T');
        setText(Character.toString(chx[1]));
        selectionIndex = Integer.parseInt(serializedState.substring(2));
    }
}
