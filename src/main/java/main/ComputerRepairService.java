package main;

import people.CustomerService;
import people.Manager;

public final class ComputerRepairService {

    static private Manager manager;
    static private CustomerService customerService;

    public ComputerRepairService(Manager newManager, CustomerService newCustomerService) {
        manager = newManager;
        customerService = newCustomerService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public Manager getManager() {
        return manager;
    }
}