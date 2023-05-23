package people;

public enum Reaction {

    HAPPY("I am very happy with my service", 0.4, "( ͡❛ ͜ʖ ͡❛)"),
    IRATE("I am absolutely irate at my service, it was horribly", 0, "(╯ ͡❛ ͜ʖ ͡❛)╯┻━┻ "),
    PLEASED("My service was satisfactory, I am pleased", 0.3, "(: "),
    APPRECIATIVE("Thank you so much for my service, I appreciate it", 0.2, "\\( ͡❛ ͜ʖ ͡❛)/"),
    DISAPPOINTED("I am disappointed in my service.  Oh well", 0.1, "|: "),
    NOREACTION("None", 0, "");

    private final String label;
    private final double tip;
    private final String face;

    Reaction(String label, double tip, String face) {
        this.label = label;
        this.tip = tip;
        this.face = face;
    }

    public String getLabel() {
        return label;
    }

    public double getTip() {
        return tip;
    }

    public String getFace() {
        return face;
    }

    public String getReaction(Reaction reaction) {
        return reaction.face + reaction.label;
    }
}