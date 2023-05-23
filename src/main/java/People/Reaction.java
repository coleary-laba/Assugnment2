package People;

public enum Reaction {
    HAPPY("I am very happy with my service", 0.4, "( ͡❛ ͜ʖ ͡❛)"),
    IRATE("I am absolutely irate at my service, it was horribly", 0, "(╯ ͡❛ ͜ʖ ͡❛)╯┻━┻ "),
    PLEASED("My service was satisfactory, I am pleased", 0.3, "(: "),
    APPRECIATIVE("Thank you so much for my service, I appreciate it", 0.2, "\\( ͡❛ ͜ʖ ͡❛)/"),
    DISAPPOINTED("I am disappointed in my service.  Oh well", 0.1, "|: "),
    NOREACTION("None", 0, "");
    public final String label;
    public final double tip;
    public final String face;

    Reaction(String label, double tip, String face) {
        this.label = label;
        this.tip = tip;
        this.face = face;
    }

    public String getReaction(Reaction reaction) {
        return reaction.face + reaction.label;
    }
}