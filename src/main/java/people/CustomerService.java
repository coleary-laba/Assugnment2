package people;

import items.Machine;

import java.util.ArrayList;

public class CustomerService implements IPerson {

    private String workId;
    private final String name;
    private final String task;
    private final ArrayList<Customer> customers;
    private Machine machine;

    public CustomerService(String newName, String newWorkId, String newTask, ArrayList<Customer> newCustomers) {
        name = newName;
        workId = newWorkId;
        task = newTask;
        customers = newCustomers;
    }

    @Override
    public void setMachine(Machine newMachine) {
        machine = newMachine;
        if (machine != null) {
            machine.setOwner(this);
        }
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
