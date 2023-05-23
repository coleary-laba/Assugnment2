package issues;

import interfaces.IHardware;

public class HardwareMaintenanceTask extends Task implements IHardware {
    private String hardware;

    public HardwareMaintenanceTask(String newHardware, String newVersion) {
        hardware = newHardware;
        version = newVersion;
    }

    public String toString() {
        return "hardware maintenance, status: " + solved;
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
    public boolean solve() {
        if (!version.equals("newest")) {
            optimize("newest");
        } else if (!hardware.equals("best")) {
            replace("best");
        }
        return solved;
    }

    @Override
    public void changeHardware(String newHardware) {
        hardware = newHardware;
    }
}
