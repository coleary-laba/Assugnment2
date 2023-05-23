package items;

import interfaces.IItem;
import people.Customer;

public class Recipt implements IItem {


    private Customer customer;
    private int amount;

    public Recipt(Customer newCustomer, int newAmount) {
        customer = newCustomer;
        amount = newAmount;
    }

    public void bill() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @Override
    public void printItem() {
        System.out.println("chkchhhk *Printing Noises*");
    }
}
