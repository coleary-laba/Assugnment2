public class Update extends Task implements  Software{
    private int updateTime;
    private boolean requiresShutdown;

    public Update(String newVersion, int newUpdateTime, boolean newRequiresShutdown) {
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
    public String toString(){
        return "update, status: "+solved;
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
    String solve() {
        if(requiresShutdown){
            updateRestart();
            return "solved";
        }
        else{
            updateMachine();
            return "solved";
        }
    }

    @Override
    public void fixIssues() {
        version = "newest";

    }
}
