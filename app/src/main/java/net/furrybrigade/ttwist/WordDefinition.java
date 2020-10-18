package net.furrybrigade.ttwist;

public class WordDefinition {
    public String word;
    public String definition;

    /**
     * Create a new word definition by assuming Index 0 as the word and Index 1 as the definition
     *
     * @param args
     */
    public WordDefinition(String[] args) {
        word = args[0];
        definition = args[1];
    }
}
