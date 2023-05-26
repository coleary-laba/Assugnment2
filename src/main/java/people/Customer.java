package people;

import Errors.NotEnoughCash;
import items.Machine;
import main.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Customer implements IPerson {

    private String name;
    private String email;
    private String phone;
    private Machine machine;
    private int funds;
    private int bill;
    private Reaction reaction;
    private static final Logger LOGGER = LogManager.getLogger(Customer.class);

    public Customer(String newName, String newEmail, String newPhone, Machine newMachine) {
        name = newName;
        email = newEmail;
        phone = newPhone;
        machine = newMachine;
        funds = 180;
        bill = 0;
        this.reaction = Reaction.NOREACTION;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public void bill(int newBill) {
        bill = newBill;
    }

    public int getBill() {
        return bill;
    }

    public void processBill() throws NotEnoughCash {
        int paid = 0;
        if ((bill == 0) || (reaction == Reaction.NOREACTION)) {
            LOGGER.info("Improper bill time");
        } else {
            if (funds < bill) {
                throw new NotEnoughCash("No Moneys");
            } else {
                paid = bill;
                funds -= bill;
                Main.billpaied += paid;
                Main.totalpaid += paid;
                LOGGER.info(name + " has paid their bill in full");
                if (funds > 0) {
                    double tipAmount = reaction.getTip() * bill;
                    if (funds > tipAmount) {
                        paid += tipAmount;
                        funds -= tipAmount;
                        Main.totalpaid += tipAmount;
                    } else {
                        paid += funds;
                        Main.totalpaid += funds;
                        funds = 0;
                    }
                }
            }
        }
        LOGGER.info("People.Customer " + name + " has paid a total of " + paid + " for their service");
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine newMachine) {
        machine = newMachine;
        if (machine != null) {
            machine.setOwner(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void printer() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method printInfo = Main.class.getDeclaredMethod("printInformation");
        Customer cust = new Customer("noName", "noMEail", "0000000000", null);
        printInfo.invoke(cust);
    }
}
