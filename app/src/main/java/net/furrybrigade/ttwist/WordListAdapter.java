package net.furrybrigade.ttwist;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {
    public List<WordOnGame> wordList;

    public WordListAdapter(List<WordOnGame> wordList) {
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        return new WordListAdapter.ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String display;
        if (wordList.get(position).isGuessed)
            display = wordList.get(position).word;
        else {
            char[] hint = new char[wordList.get(position).word.length()];
            Arrays.fill(hint, '□');
            display = new String(hint);
        }
        holder.textView.setText(display);
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(@NonNull TextView itemView) {
            super(itemView);
            textView = itemView;
        }
    }
}
