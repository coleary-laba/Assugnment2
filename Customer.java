public class Customer {

    public String name;
    public String email;
    public String phone;
    private Machine machine;

    public Customer(String newName, String newEmail, String newPhone, Machine newMachine) {
        name = newName;
        email = newEmail;
        phone = newPhone;
        machine = newMachine;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine newMachine) {
        machine = newMachine;
    }

    public void submitRequest() {

    }
}
