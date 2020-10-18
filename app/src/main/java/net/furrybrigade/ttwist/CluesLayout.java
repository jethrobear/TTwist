package net.furrybrigade.ttwist;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class CluesLayout extends ConstraintLayout implements View.OnClickListener {
    private List<ButtonState> clues;
    private Submittable submittable;

    static final int[] buttonIds = new int[]{R.id.btnClue1, R.id.btnClue2, R.id.btnClue3,
            R.id.btnClue4, R.id.btnClue5, R.id.btnClue6, R.id.btnClue7, R.id.btnClue8};


    public CluesLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CluesLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CluesLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CluesLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.clue_layout, this);

        // Add actions to the interface
        findViewById(R.id.btnShuffle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomize();
            }
        });
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
        findViewById(R.id.btnBackspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lastIndex = 0;
                for(ButtonState buttonState : clues){
                    if(!buttonState.state)
                        lastIndex++;
                }
                if(lastIndex > 0) {
                    for (int idx = 0; idx < clues.size(); idx++)
                        if (clues.get(idx).selectionIndex == lastIndex - 1) {
                            clues.get(idx).toggleState(-1);
                            break;
                        }
                    updateLayout();
                }
            }
        });
        findViewById(R.id.btnSubmit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                submittable.onSubmit(getStringValue());
            }
        });
        for (int viewId : buttonIds)
            findViewById(viewId).setOnClickListener(this);
    }

    public void setClues(List<String> clues) {
        this.clues = new ArrayList<>();
        for (String stx : clues)
            this.clues.add(new ButtonState(stx));
        updateLayout();
    }

    public void randomize() {
        Collections.shuffle(clues);
        updateLayout();
    }

    public void clear() {
        for (ButtonState clue : clues)
            clue.clearState();
        updateLayout();
    }

    private void updateLayout() {
        for (int idx = 0; idx < WordIterface.MAXIMUM_WORD_GUESS; idx++) {
            Button buttonWidget = findViewById(buttonIds[idx]);
            buttonWidget.setText(clues.get(idx).label);

            buttonWidget.setBackgroundColor(Color.RED);
            if (clues.get(idx).state)
                buttonWidget.setBackgroundColor(Color.GREEN);

            ((TextView) findViewById(R.id.txtClueGuess)).setText(getStringValue());
        }
    }

    private String getStringValue() {
        StringBuilder stringBuilder = new StringBuilder();
        String[] stringConcat = new String[WordIterface.MAXIMUM_WORD_GUESS];
        for (ButtonState clue : clues) {
            if (clue.selectionIndex > -1)
                stringConcat[clue.selectionIndex] = clue.label;
        }
        for (String chx : stringConcat)
            if (chx != null)
                stringBuilder.append(chx);
        return stringBuilder.toString();
    }

    public void setSubmitListener(Submittable submittable) {
        this.submittable = submittable;
    }

    @Override
    public void onClick(View v) {
        int idx = ArrayUtils.indexOf(buttonIds, v.getId());
        int lastIndex = 0;
        for(ButtonState buttonState : clues){
            if(!buttonState.state)
                lastIndex++;
        }
        clues.get(idx).toggleState(lastIndex);
        updateLayout();
    }
}
