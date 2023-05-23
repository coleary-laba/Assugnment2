package issues;

import interfaces.ISoftware;

public class VirusRemovalTask extends Task implements ISoftware {
    private String virusLocation;
    private boolean isRemovable;

    public VirusRemovalTask(String newVirusLocation, boolean newIsRemovable) {
        virusLocation = newVirusLocation;
        isRemovable = newIsRemovable;
    }

    public String toString() {
        return "virus removal, status: " + solved;
    }

    public String getVirusLocation() {
        return virusLocation;
    }

    public void setVirusLocation(String newVirusLocation) {
        virusLocation = newVirusLocation;
    }

    public void remove() {
        if (isRemovable) {
            fixIssues();
            solved = true;
        } else {
            solved = false;
        }
    }

    public boolean getRemovable() {
        return isRemovable;
    }

    public void setRemovable(boolean newRemovable) {
        isRemovable = newRemovable;
    }

    @Override
    public boolean solve() {
        remove();
        return solved;
    }

    @Override
    public void fixIssues() {
        virusLocation = null;
    }
}
