import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public final class ComputerRepair {
    static Worker[] workers;
    static Customer[] customers;
    static Logger logger;
    static{
        logger = LogManager.getLogger(ComputerRepair.class);
    }
    static final int numPeople = 0;
    static void Initialize() {
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        boolean workInit = false;
        boolean custInit = false;
        int workNum = 0;
        int custNum = 0;
        int choice = 3;
        logger.info("use own names for charecters or use prexisting ones? 1 for prexisting, 0 for create own");
        while ((choice != 1) || choice != 0) {
            try {
                choice = scan.nextInt();
            } catch (InputMismatchException e) {
                logger.info("Improper input");
            }
        }
        if (choice == 0) {
            while (!workInit) {
                logger.info("Input the number of workers");
                try {
                    workNum = scan.nextInt();
                } catch (InputMismatchException e) {
                    logger.info("Invalid Input");
                }
            }
            while (!custInit) {
                logger.info("Input the number of customers");
                try {
                    custNum = scan.nextInt();
                } catch (InputMismatchException e) {
                    logger.info("Invalid Input");
                }
            }
            workers = new Worker[workNum];
            customers = new Customer[custNum];
            Task newTask = null;
            for (int i = 0; i < workNum; i++) {
                logger.info("Input worker name: must be under 10 characters");
                String name = scan.next();
                workers[i] = new Worker(name, Integer.toString(i), null);
            }
            for (int i = 0; i < custNum; i++) {
                logger.info("Input customer name: must be under 10 characters");
                String name = scan.next();
                int prob = rand.nextInt(4);
                switch (prob) {
                    case 1:
                        newTask = new Update("2.0", 20, false);
                        break;
                    case 2:
                        newTask = new HardwareMaintenance("old", "newest");
                        break;
                    case 3:
                        newTask = new VirusRemoval("CPU", true);
                        break;
                    case 4:
                        newTask = new InstallSoftware("C://", true);
                }
                customers[i] = new Customer(name, name + i + "@email.com", "1234567890", null);
                Machine newMachine = new Machine(newTask, null, "10", "Computer");
                customers[i].setMachine(newMachine);
            }
        } else if (choice == 1) {

            try {
                File file = new File("names.txt");
                Scanner scan2 = new Scanner(file);
                int numWorkers = 20;
                for (int i = 0; i < 20; i++) {
                    String name = scan2.nextLine();
                    Task newTask = null;
                    int prob = rand.nextInt(4);
                    switch (prob) {
                        case 1:
                            newTask = new Update("2.0", 20, false);
                            break;
                        case 2:
                            newTask = new HardwareMaintenance("old", "newest");
                            break;
                        case 3:
                            newTask = new VirusRemoval("CPU", true);
                            break;
                        case 4:
                            newTask = new InstallSoftware("C://", true);
                    }
                    workers[i] = new Worker(name, Integer.toString(i), null);
                    customers[i] = new Customer(name, name + i + "@email.com", "1234567890", null);
                    Machine newMachine = new Machine(newTask, null, "10", "Computer");
                    customers[i].setMachine(newMachine);
                }
                scan2.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        scan.close();
    }

    public static void main(String args[]) {
        Initialize();
        Manager mangerMan = new Manager("ManagerMan", Integer.toString(numPeople), null, workers);
        //will generate LinkedList of worktickets
    }
}

//static block
//add 5 collections