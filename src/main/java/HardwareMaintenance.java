public class HardwareMaintenance extends Task implements Hardware{
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
        changeHardware(newHardware);
        solved = true;

    }

    public void optimize(String newVersion) {
        version = newVersion;
        solved = true;

    }

    public String getHardware() {
        return hardware;
    }


    @Override
    String solve() {
        if(!version.equals("newest")){
            optimize("newest");
            return "solved";
        }
        else if(!hardware.equals("best")){
            replace("best");
            return "solved";
        }
        else {
            return "unsolved";
        }
    }

    @Override
    public void changeHardware(String newHardware) {
        hardware = newHardware;
    }
}
