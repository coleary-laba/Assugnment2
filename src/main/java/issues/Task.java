package issues;

public abstract class Task {
    protected Version version;
    protected boolean solved;

    public Version gerVersion() {
        return version;
    }

    public void setVersion(Version newVersion) {
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

    public abstract boolean solve();

}
