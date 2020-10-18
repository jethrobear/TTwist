package net.furrybrigade.ttwist;

public class WordOnGame extends WordDefinition {
    public boolean isGuessed;

    /**
     * Create a new word definition by assuming Index 0 as the word and Index 1 as the definition
     *
     * @param args
     */
    public WordOnGame(String[] args) {
        super(args);
        isGuessed = false;
    }

    public WordOnGame(WordDefinition arg) {
        super(new String[]{arg.word, arg.definition});
    }

    public void setGuessed() {
        isGuessed = true;
    }
}
