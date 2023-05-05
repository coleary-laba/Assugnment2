public class Recipt implements Item{
    public Customer customer;
    public int amount;

    public Recipt(Customer newCustomer, int newAmount) {
        customer = newCustomer;
        amount = newAmount;
    }

    public void bill() {

    }

    @Override
    public void printItem() {
        ComputerRepair.logger.info("chkchhhk *Printing Noises*");
    }
}
