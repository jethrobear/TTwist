package net.furrybrigade.ttwist;

import android.content.Context;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordIterface {
    private Context context;
    final private String FILENAME = "words.txt";
    static final public int MAXIMUM_WORD_GUESS = 8;
    static final public int MINIMUM_WORD_GUESS = 3;

    public WordIterface(Context context) throws IOException {
        this.context = context;
    }

    public List<WordDefinition> readTextFile() throws IOException {
        // Read the definition file
        InputStream is = context.getAssets().open(FILENAME);
        List<String> lines = IOUtils.readLines(is, "UTF-8");

        // Try to parse each lines
        ArrayList<WordDefinition> result = new ArrayList<>();
        for (String keyValuePair : lines) {
            String[] keyValue = keyValuePair.split("\t");
            if (keyValue.length == 2)
                result.add(new WordDefinition(keyValue));
        }

        return result;
    }

    public List<String> generateRandomLetters() {
        ArrayList<String> result = new ArrayList<>();
        for (int idx = 0; idx < MAXIMUM_WORD_GUESS; idx++)
            result.add(Character.toString((char) RandomUtils.nextInt(65, 91)));
        return result;
    }

    /**
     * Get a random word from wordList that has the same length as MAXIMUM
     *
     * @param wordList Dictionary to use
     * @return List of characters that the random word has
     */
    public List<String> generateRandomLettersFromMax(List<WordDefinition> wordList) {
        ArrayList<WordDefinition> longWords = new ArrayList<>();
        for (WordDefinition definition : wordList)
            if (definition.word.length() == MAXIMUM_WORD_GUESS)
                longWords.add(definition);
        WordDefinition word = longWords.get(RandomUtils.nextInt(0, longWords.size()));

        ArrayList<String> spelling = new ArrayList<>();
        for (char chx : word.word.toCharArray())
            spelling.add(Character.toString(chx));
        Collections.shuffle(spelling);  // Randomize before returning
        return spelling;
    }

    public List<WordOnGame> getWordsFromClues(List<WordDefinition> wordList, List<String> clues) {
        ArrayList<WordOnGame> result = new ArrayList<>();
        for (WordDefinition definition : wordList) {
            // Create a deepcopy of clues, just in case
            ArrayList<String> checklist = new ArrayList<>(clues);

            // Check if the word can fit into the clues
            boolean wordDidFit = true;
            for (char chx : definition.word.toCharArray()) {
                String stx = Character.toString(chx);
                if (checklist.contains(stx))
                    checklist.remove(stx);
                else
                    wordDidFit = false;
            }

            // Add the word if it fits all the clues
            if (wordDidFit
                    && definition.word.length() >= MINIMUM_WORD_GUESS
                    && definition.word.length() <= MAXIMUM_WORD_GUESS)
                result.add(new WordOnGame(definition));
        }

        // Sort word list by size
        Collections.sort(result, new Comparator<WordDefinition>() {
            @Override
            public int compare(WordDefinition o1, WordDefinition o2) {
                return o1.word.length() - o2.word.length();
            }
        });
        return result;
    }
}
