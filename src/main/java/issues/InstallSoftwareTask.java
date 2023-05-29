package issues;

import interfaces.ISoftware;

public class InstallSoftwareTask extends Task implements ISoftware {

    private String softwareDrive;
    private boolean spaceAvailable;

    public InstallSoftwareTask(String newLocation, boolean newSpaceAvailable) {
        softwareDrive = newLocation;
        spaceAvailable = newSpaceAvailable;
    }

    public String getSoftwareLocation() {
        return softwareDrive;
    }

    public void setSoftwareDrive(String newSoftwareDrive) {
        softwareDrive = newSoftwareDrive;
    }

    public boolean getSpaceAvailable() {
        return spaceAvailable;
    }

    public void setSpaceAvailable(boolean newSpace) {
        spaceAvailable = newSpace;
    }

    @Override
    public boolean solve() {
        fixIssues();
        return solved;
    }

    @Override
    public void fixIssues() {
        solved = spaceAvailable;
    }

    @Override
    public String toString() {
        return "Software installation, status: " + solved;
    }
}
