package people;

import interfaces.IPerson;
import items.Machine;

import java.util.Vector;

public class Manager extends Worker implements IPerson {

    private String workId;
    private String manTask;
    private static Vector<Worker> employees;
    private Machine machine;

    public Manager(String newName, String newWorkId, String newTask, Vector<Worker> newEmployees) {
        name = newName;
        workId = newWorkId;
        manTask = newTask;
        employees = newEmployees;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getManTask() {
        return manTask;
    }

    public void setManTask(String manTask) {
        this.manTask = manTask;
    }

    public static Vector<Worker> getEmployees() {
        return employees;
    }

    public static void setEmployees(Vector<Worker> employees) {
        Manager.employees = employees;
    }

    public void setMachine(Machine newMachine) {
        machine = newMachine;
        if (machine != null) {
            machine.setOwner(this);
        }
    }

}
