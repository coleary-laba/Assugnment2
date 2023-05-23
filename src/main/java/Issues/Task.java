package Issues;

public abstract class Task {
    protected String version;
    protected boolean solved;

    public String gerVersion() {
        return version;
    }

    public void setVersion(String newVersion) {
        version = newVersion;
    }

    public boolean getSolved() {
        return solved;
    }

    public void setSolved(boolean newSolved) {
        solved = newSolved;
    }

    public String toString() {
        return null;
    }

    public abstract String solve();

}
