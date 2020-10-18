package net.furrybrigade.ttwist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Submittable {
    private List<WordOnGame> wordList;
    private List<String> clues;
    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the stage's information
        try {
            Dictionary dictionary = new Dictionary(getApplicationContext());
            List<WordDefinition> dictionaryList = dictionary.readTextFile();
            clues = dictionary.generateRandomLettersFromMax(dictionaryList);
            wordList = dictionary.getWordsFromClues(dictionaryList, clues);

            // Place clues in the Clue Layout
            CluesLayout cluesLayout = findViewById(R.id.cluesFragment);
            cluesLayout.setSubmitListener(this);
            cluesLayout.setClues(clues);

            // Place the word list
            ((RecyclerView) findViewById(R.id.wordlistLayout)).setLayoutManager(
                    new LinearLayoutManager(this));
            ((RecyclerView) findViewById(R.id.wordlistLayout)).setAdapter(
                    new WordListAdapter(wordList));
        } catch (IOException ioe) {
            // TODO: Show why error?
        }
    }

    @Override
    public void onSubmit(String wordToCheck) {
        for (int idx = 0; idx < wordList.size(); idx++) {
            if (wordList.get(idx).word.equals(wordToCheck)) {
                // Add points to only freshly guessed words
                if (!wordList.get(idx).isGuessed) {
                    points += wordList.get(idx).word.length();
                    ((ScoreboardLayout) findViewById(R.id.scoreboardLayout)).setPoints(points);
                }
                wordList.get(idx).setGuessed();
                ((RecyclerView) findViewById(R.id.wordlistLayout)).setAdapter(
                        new WordListAdapter(wordList));
                findViewById(R.id.wordlistLayout).invalidate();
                ((CluesLayout) findViewById(R.id.cluesFragment)).clear(); // TODO: Show warning for guessed words already

                break;
            }
        }
    }
}