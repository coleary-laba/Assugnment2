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

//static block
//add 5 collections
//read number of unique names - stringUtils and fileUtils
//5 lambda functions
//3 custom lamba functions with egnertics
//5 complex enums with fields methods blocks
//7 collection streaming in the hierarchy with terminal and non terminal operations
//Using reflection, extract information (modifiers return types params)
//About fields constuctors methods
//create object cal method using only refelction