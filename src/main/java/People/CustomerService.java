package People;

import Items.Machine;

import java.util.ArrayList;

public class CustomerService implements Person {
    public String workId;
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

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
