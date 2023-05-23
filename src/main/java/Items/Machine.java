package Items;

import Issues.Task;
import People.Customer;
import People.Person;

public class Machine {

    private Task problem;
    private final Person owner;
    private String operatingSystem;
    private String deviceType;

    public Machine(Task newProblem, Person newOwner, String newOperatingSystem, String newDeviceType) {
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

    public void setOwner(Person owner) {

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
