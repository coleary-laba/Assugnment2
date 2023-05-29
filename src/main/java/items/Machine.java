package items;

import interfaces.IPerson;
import issues.Task;
import people.Customer;

public class Machine {

    private Task problem;
    private IPerson owner;
    private String operatingSystem;
    private String deviceType;

    public Machine(Task newProblem, IPerson newOwner, String newOperatingSystem, String newDeviceType) {
        problem = newProblem;
        owner = newOwner;
        operatingSystem = newOperatingSystem;
        deviceType = newDeviceType;
    }

    public Task getProblem() {
        return problem;
    }

    public void setProblem(Task newProblem) {
        problem = newProblem;
    }

    public Customer getOwner() {
        return (Customer) owner;
    }

    public void setOwner(IPerson owner) {
        this.owner = owner;
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
