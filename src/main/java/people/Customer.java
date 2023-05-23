package people;

import items.Machine;
import main.Main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Customer implements Person {
    private String name;
    private String email;
    private String phone;
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
        Customer cust = new Customer();
        printInfo.invoke(cust);
    }
}
