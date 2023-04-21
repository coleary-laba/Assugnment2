public class HardwareMaintenance extends Task{
    private String hardware;

    public HardwareMaintenance(String newHardware, String newVersion) {
        hardware = newHardware;
        version = newVersion;
    }
    public String toString(){
        return "hardware maintenance, status: "+solved;
    }
    public void maintain() {
        solved = true;
    }

    public void replace(String newHardware) {
        hardware = newHardware;
        solved = true;

    }

    public void optimize(String newVersion) {
        version = newVersion;
        solved = true;

    }

    public String getHardware() {
        return hardware;
    }


    public void setHardware(String newHardware) {
        hardware = newHardware;
    }

    @Override
    void solve() {
        if(!version.equals("newest")){
            optimize("newest");
        }
        if(!hardware.equals("best")){
            replace("best");
        }
    }
}
