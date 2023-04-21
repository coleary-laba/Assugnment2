public class Update extends Task{
    private int updateTime;
    private boolean requiresShutdown;

    public Update(String newVersion, int newUpdateTime, boolean newRequiresShutdown) {
        version = newVersion;
        updateTime = newUpdateTime;
        requiresShutdown = newRequiresShutdown;
    }

    public void updateMachine() {
        version = "newestVersion";
        updateTime = 0;
        solved = true;
    }

    public void updateRestart() {
        version = "newestVersion";
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
    void solve() {
        if(requiresShutdown){
            updateRestart();
        }
        else{
            updateMachine();
        }
    }
}
