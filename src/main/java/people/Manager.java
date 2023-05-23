package people;

import items.Machine;

import java.util.ArrayList;

public class Manager extends Worker implements IPerson {

    private String workId;
    private String manTask;
    private static ArrayList<Worker> employees;
    private Machine machine;

    public Manager(String newName, String newWorkId, String newTask, ArrayList<Worker> newEmployees) {
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

    public static ArrayList<Worker> getEmployees() {
        return employees;
    }

    public static void setEmployees(ArrayList<Worker> employees) {
        Manager.employees = employees;
    }

    public void setMachine(Machine newMachine) {
        machine = newMachine;
        if (machine != null) {
            machine.setOwner(this);
        }
    }

}
