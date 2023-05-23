package issues;

import interfaces.IHardware;

public class HardwareMaintenanceTask extends Task implements IHardware {
    private Hardware hardware;

    public HardwareMaintenanceTask(Hardware newHardware, Version newVersion) {
        hardware = newHardware;
        version = newVersion;
    }

    @Override
    public String toString() {
        return "hardware maintenance, status: " + solved;
    }

    public void maintain() {
        solved = true;
    }

    public void replace(Hardware newHardware) {
        changeHardware(newHardware);
        solved = true;

    }

    public void optimize(Version newVersion) {
        version = newVersion;
        solved = true;

    }

    public Hardware getHardware() {
        return hardware;
    }


    @Override
    public boolean solve() {
        if (!version.equals(Version.NEWEST)) {
            optimize(Version.NEWEST);
        } else if (!version.equals(Version.BEST)) {
            replace(Hardware.CPU);
        }
        return solved;
    }

    @Override
    public void changeHardware(Hardware newHardware) {
        hardware = newHardware;
    }
}
