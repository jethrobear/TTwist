package net.furrybrigade.ttwist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CluesLayout extends ConstraintLayout implements AppCompatToggleButton.OnCheckedChangeListener {
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
                // Collect serialized data
                List<String> states = new ArrayList<>();
                for (int buttonId : buttonIds)
                    states.add(((ToggleClueButton) findViewById(buttonId)).serializeState());
                Collections.shuffle(states);
                for (int idx = 0; idx < states.size(); idx++)
                    ((ToggleClueButton) findViewById(buttonIds[idx])).deserializeState(states.get(idx));
                ((TextView) findViewById(R.id.txtClueGuess)).setText(getStringValue());
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
                for (int buttonId : buttonIds) {
                    if (((ToggleClueButton) findViewById(buttonId)).isChecked())
                        lastIndex++;
                }
                for (int buttonId : buttonIds) {
                    ToggleClueButton clue = findViewById(buttonId);
                    if (clue.selectionIndex == lastIndex) {
                        clue.clearState();
                        break;
                    }
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
            ((ToggleClueButton) findViewById(viewId)).setOnCheckedChangeListener(this);
    }

    public void setClues(List<String> clues) {
        // TODO: This can cause a mismatch in the number of clues and buttons
        for (int idx = 0; idx < clues.size(); idx++)
            ((Button) (findViewById(buttonIds[idx]))).setText(clues.get(idx));
    }

    public void clear() {
        for (int buttonId : buttonIds)
            ((ToggleClueButton) findViewById(buttonId)).clearState();
        ((TextView) findViewById(R.id.txtClueGuess)).setText(getStringValue());
    }

    private String getStringValue() {
        StringBuilder stringBuilder = new StringBuilder();
        String[] stringConcat = new String[WordIterface.MAXIMUM_WORD_GUESS];
        for (int buttonId : buttonIds) {
            ToggleClueButton clue = findViewById(buttonId);
            if (clue.isChecked())
                stringConcat[clue.selectionIndex - 1] = clue.getText().toString();
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int lastIndex = 0;
        for (int buttonId : buttonIds) {
            if (((ToggleClueButton) findViewById(buttonId)).isChecked())
                lastIndex++;
        }
        ((ToggleClueButton) findViewById(buttonView.getId())).setSelectionIndex(lastIndex);
        ((TextView) findViewById(R.id.txtClueGuess)).setText(getStringValue());
    }
}
