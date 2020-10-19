package net.furrybrigade.ttwist;

public class WordOnGame extends WordDefinition {
    private TransitionalState state= TransitionalState.UNINITIALIZED;;
    public boolean isGuessed= false;;

    /**
     * Create a new word definition by assuming Index 0 as the word and Index 1 as the definition
     *
     * @param args
     */
    public WordOnGame(String[] args) {
        super(args);
    }

    public WordOnGame(WordDefinition arg) {
        super(new String[]{arg.word, arg.definition});
    }

    public void setState(TransitionalState state) {
        this.state = state;
    }

    public TransitionalState getState() {
        return state;
    }
}
