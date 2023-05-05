public class VirusRemoval extends Task implements Software{
    private String virusLocation;
    private boolean isRemovable;

    public VirusRemoval(String newVirusLocation, boolean newIsRemovable) {
        virusLocation = newVirusLocation;
        isRemovable = newIsRemovable;
    }

    public String toString(){
        return "virus removal, status: "+solved;
    }
    public String getVirusLocation() {
        return virusLocation;
    }

    public void setVirusLocation(String newVirusLocation) {
        virusLocation = newVirusLocation;
    }
    public void remove(){
        if(isRemovable){
            fixIssues();
            solved = true;
        }
        else{
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
    void solve() {
        remove();
    }

    @Override
    public void fixIssues() {
        virusLocation = null;
    }
}
