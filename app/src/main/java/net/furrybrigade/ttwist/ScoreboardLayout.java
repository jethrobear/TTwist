package net.furrybrigade.ttwist;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ScoreboardLayout extends ConstraintLayout {
    public ScoreboardLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ScoreboardLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScoreboardLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ScoreboardLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.scoreboard_layout, this);
    }

    public void setPoints(int points) {
        ((TextView) findViewById(R.id.txtPoint)).setText(String.valueOf(points));
        invalidate();
    }
}
