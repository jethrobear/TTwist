package net.furrybrigade.ttwist;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<WordOnGame> wordList;

    public WordListAdapter(List<WordOnGame> wordList) {
        this.wordList = wordList;
    }

    @Override
    public int getItemViewType(int position) {
        return wordList.get(position).word.length();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blank_3_layout,
                parent, false);
        return new Blank3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Blank3ViewHolder blankHolder = (Blank3ViewHolder) holder;
        WordOnGame def = wordList.get(position);
        String word = def.word;
        if (def.isGuessed)
            for (int idx = 0; idx < word.length(); idx++) {
                blankHolder.blanks[idx].setText(Character.toString(word.charAt(idx)));
                blankHolder.blanks[idx].setBackgroundResource(R.drawable.round_guess);
            }

        // Remove additional TextViews from UI
        for (int idx = word.length(); idx < Dictionary.MAXIMUM_WORD_GUESS; idx++)
            blankHolder.blanks[idx].setVisibility(View.INVISIBLE);
        if (def.getState() == TransitionalState.UNINITIALIZED)
            def.setState(TransitionalState.UNSOLVED);
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    class Blank3ViewHolder extends RecyclerView.ViewHolder {
        public TextView[] blanks;

        public Blank3ViewHolder(View v) {
            super(v);
            blanks = new TextView[]{
                    v.findViewById(R.id.txtBlank1),
                    v.findViewById(R.id.txtBlank2),
                    v.findViewById(R.id.txtBlank3),
                    v.findViewById(R.id.txtBlank4),
                    v.findViewById(R.id.txtBlank5),
                    v.findViewById(R.id.txtBlank6),
                    v.findViewById(R.id.txtBlank7),
                    v.findViewById(R.id.txtBlank8)
            };
        }
    }
}
