package issues;

import interfaces.ISoftware;

public class UpdateTask extends Task implements ISoftware {

    private int updateTime;
    private boolean requiresShutdown;

    public UpdateTask(Version newVersion, int newUpdateTime, boolean newRequiresShutdown) {
        version = newVersion;
        updateTime = newUpdateTime;
        requiresShutdown = newRequiresShutdown;
    }

    public void updateMachine() {
        fixIssues();
        updateTime = 0;
        solved = true;
    }

    public void updateRestart() {
        fixIssues();
        updateTime = 0;
        solved = true;
    }

    public int getUpdateTime() {
        return updateTime;
    }


    public boolean getRequiresShutdown() {
        return requiresShutdown;
    }

    public void setUpdateTime(int newUpdateTime) {
        updateTime = newUpdateTime;
    }

    public void setRequiresShutdown(boolean newRequiresShutdown) {
        requiresShutdown = newRequiresShutdown;
    }

    @Override
    public boolean solve() {
        if (requiresShutdown) {
            updateRestart();
        } else {
            updateMachine();
        }
        return solved;
    }

    @Override
    public void fixIssues() {
        version = Version.NEWEST;
    }

    @Override
    public String toString() {
        return "update, status: " + solved;
    }
}
