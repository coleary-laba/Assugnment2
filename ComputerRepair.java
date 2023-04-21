public class ComputerRepair {
    static Worker[] workers;
    static Customer[] customers;

    public static void main(String args[]){
        workers = new Worker[10];
        for(int i = 0; i < 10; i++){
            workers[i] = new Worker("name"+i, Integer.toString(i), null);
            customers[i] = new Customer("name"+i, "name"+1+"@email.com", "1234567890", null);
            customers[i].setMachine(new Machine(null, customers[i], "10", "Computer"));
        }
        Manager mangerMan = new Manager("ManagerMan", Integer.toString(10), null, workers);
    }
}
