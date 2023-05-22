public class InstallSoftware extends Task implements Software{
    private String softwareDrive;
    private boolean spaceAvailable;

    public InstallSoftware(String newLocation, boolean newSpaceAvailable) {
        softwareDrive = newLocation;
        spaceAvailable = newSpaceAvailable;
    }

    public String toString(){
        return "Software installation, status: "+solved;
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
    String solve() {
        fixIssues();
        return "solved";
    }

    @Override
    public void fixIssues() {
        solved = spaceAvailable;
    }
}
