package People;

import Items.Machine;
import Main.Main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Customer implements Person {

    public String name;

    public String email;
    public String phone;
    private Machine machine;
    private int funds;
    private int bill;

    public Customer() {

    }

    public Reaction reaction;

    public Customer(String newName, String newEmail, String newPhone, Machine newMachine) {

        name = newName;
        email = newEmail;
        phone = newPhone;
        machine = newMachine;
        funds = 180;
        bill = 0;
        this.reaction = Reaction.NOREACTION;

    }

    public void bill(int newBill) {
        bill = newBill;
    }

    public int getBill() {
        return bill;
    }

    public void processBill() {
        int paid = 0;
        if ((bill == 0) || (reaction == Reaction.NOREACTION)) {
            Main.logger.info("Improper bill time");
        } else {
            if (funds < bill) {
                Main.logger.info("Uh oh! " + name + " can not pay, and has fled");
            } else {
                paid = bill;
                funds -= bill;
                Main.billpaied += paid;
                Main.totalpaid += paid;
                Main.logger.info(name + " has paid their bill in full");
                if (funds > 0) {
                    double tipAmount = reaction.tip * bill;
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
        Main.logger.info("People.Customer " + name + " has paid a total of " + paid + " for their service");
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

    public void printer() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method printInfo = Main.class.getDeclaredMethod("printInformation");
        Customer cust = new Customer();
        printInfo.invoke(cust);
    }
}
