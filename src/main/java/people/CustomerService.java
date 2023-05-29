package people;

import interfaces.IPerson;
import items.Machine;

import java.util.Queue;

public class CustomerService implements IPerson {

    private String workId;
    private final String name;
    private final String task;
    private final Queue<Customer> customers;
    private Machine machine;

    public CustomerService(String newName, String newWorkId, String newTask, Queue<Customer> newCustomers) {
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

    public Queue<Customer> getCustomers() {
        return customers;
    }
}
