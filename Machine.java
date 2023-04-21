public class Machine {

    private String problem;
    private Customer owner;
    private String operatingSystem;
    private String deviceType;

    public Machine(String newProblem, Customer newOwner, String newOperatingSystem, String newDeviceType) {
        problem = newProblem;
        owner = newOwner;
        operatingSystem = newOperatingSystem;
        deviceType = newDeviceType;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String newProblem) {
        problem = newProblem;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {

    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String newOperatingSystem) {
        operatingSystem = newOperatingSystem;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String newDeviceType) {
        deviceType = newDeviceType;
    }

    public void diagnose() {

    }

}
