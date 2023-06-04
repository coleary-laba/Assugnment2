package items;

import interfaces.IItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import people.Customer;

public class Recipt implements IItem {

    private Customer customer;
    private int amount;
    private static final Logger LOGGER = LogManager.getLogger(Recipt.class);

    public Recipt(Customer newCustomer, int newAmount) {
        customer = newCustomer;
        amount = newAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void printItem() {
        LOGGER.info("chkchhhk *Printing Noises*");
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
