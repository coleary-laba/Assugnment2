import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


interface CounterOfThings<T>{
    int search(T single, T[] collection);
}
interface Generator<T>{
    String[] arrayGenerator(ArrayList<T> collection);
}
interface billTotal<T>{
    int generateTotal(ArrayList<T> collection);
}
public class Main {
    static ArrayList<Worker> workers;
    static ArrayList<Customer> customers;
    static Logger logger;

    static ComputerRepair computerRepairService;
    static final int numPeople = 10;
    static ArrayList<Recipt> recipts;
    static int billpaied = 0;
    static int totalpaid = 0;


    public static void main(String[] args) {

        CounterOfThings<String> NameSearcher;
        Generator<Worker> arrayGenWorker;
        Generator<Customer> arrayGenCustomer;
        billTotal<Customer> billTotalFinder;
        NameSearcher = (single, collection) ->{
            int count = 0;
            for(int i = 0; i < collection.length; i++){
                if(single.equals(collection[i])){
                    count++;
                }
            }
            return count;
        };
        arrayGenWorker = (collection)->{
            String[] retArray = new String[collection.size()];
            for(int i = 0; i < collection.size(); i++){
                retArray[i]=collection.get(i).name;
            }
            return retArray;
        };
        arrayGenCustomer = (collection)->{
            String[] retArray = new String[collection.size()];
            for(int i = 0; i < collection.size(); i++){
                retArray[i]=collection.get(i).name;
            }
            return retArray;
        };
        billTotalFinder = (collection)->{
            int total = 0;
            for(int i = 0; i< collection.size(); i++){
                total += collection.get(i).getBill();
            }
            return total;
        };
        logger = LogManager.getLogger(ComputerRepair.class);
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        boolean workInit = false;
        boolean initChoiceMade = false;
        int workNum = 0;
        int choice = 3;
        workers = new ArrayList<Worker>();
        customers = new ArrayList<Customer>();
        List<String> lines = new ArrayList<String>();
        randomNum randNum = (x, y) -> (rand.nextInt(y - x) + x);
        actualNum custNumber = (a, b) -> a + b;
        logger.info("use own names for charecters or use prexisting ones? 1 for prexisting, 0 for create own");
        while (!initChoiceMade) {
            try {
                choice = Integer.parseInt(scan.next());
            } catch (InputMismatchException e) {
                logger.info("Improper input");
            }
            if (choice == 1 || choice == 0) {
                initChoiceMade = true;
            }
        }
        if (choice == 0) {
            logger.info("Roger, using names you make");
            while (!workInit) {
                logger.info("Input the number of workers (will also be number of customers");
                try {
                    workNum = Integer.parseInt(scan.next());
                } catch (InputMismatchException e) {
                    logger.info("Invalid Input");
                }
            }
            for (int i = 0; i < workNum; i++) {
                logger.info("Input worker name: must be under 10 characters");
                String name = scan.next();
                lines.add(name);
            }
            for (int i = 0; i < workNum; i++) {
                logger.info("Input customer name: must be under 10 characters");
                String name = scan.next();
                lines.add(name);
            }
        } else{
            logger.info("roger, using our names");
            try {
                File file = new File("E:\\programs\\InteliJ\\Projects\\Assignment2\\src\\main\\java\\names.txt");
                lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
                workNum = 10;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        AtomicInteger counter = new AtomicInteger();
        lines.forEach(n -> counter.addAndGet(StringUtils.countMatches(n, "Bob")));
        logger.info("The name Bob was in the selection of names "+counter+" times");
        int numPeople = lines.size();
        for (int i = 0; i < workNum; i++) {
            Task newTask = null;
            int prob = randNum.findNum(1, 5);
            trueOrFalse findBool = () -> rand.nextBoolean();
            switch (prob) {
                case 1:
                    int timing = randNum.findNum(1, 60);
                    newTask = new Update("2.0", timing, findBool.findTrueFalse());
                    break;
                case 2:
                    newTask = new HardwareMaintenance("old", "newest");
                    break;
                case 3:
                    newTask = new VirusRemoval("CPU", findBool.findTrueFalse());
                    break;
                case 4:
                    newTask = new InstallSoftware("C://", findBool.findTrueFalse());
                    break;
            }
            String name = lines.get(custNumber.custNumber(i, workNum));
            customers.add(new Customer(lines.get(custNumber.custNumber(i, workNum)), name + i + "@email.com", "1234567890", null));
            Machine newMachine = new Machine(newTask, null, "10", "Computer");
            customers.get(i).setMachine(newMachine);
        }
        for(int j = 0; j < workNum; j++){
            workers.add(new Worker(lines.get(j), Integer.toString(j), null));
        }
        scan.close();
        long bobCount = lines.stream().filter(item-> item.startsWith("Bob")).count();
        logger.info("total count of names that start with bob "+bobCount);
        CustomerService customerService = new CustomerService("CustomerServicePerson", Integer.toString(Main.numPeople +1), null, customers);
        Manager mangerMan = new Manager("ManagerMan", Integer.toString(Main.numPeople), null, workers);
        computerRepairService = new ComputerRepair(mangerMan, customerService);

        String[] workerNameArray = arrayGenWorker.arrayGenerator(computerRepairService.getManager().employees);
        String[] custNameArray = arrayGenCustomer.arrayGenerator(computerRepairService.getCustomerService().getCustomers());
        int workBobs = NameSearcher.search("Bob", workerNameArray);
        int custBobs = NameSearcher.search("Bob", custNameArray);
        logger.info("Number of Bobs in Workers: "+workBobs);
        logger.info("Number of Bobs in Customers: "+custBobs);
        ArrayList<WorkTicket> workTickets = new ArrayList<WorkTicket>();
        workTickets = generateWorkTickets(computerRepairService);
        assignTasks(computerRepairService, workTickets);
        Manager.employees.forEach(n -> {
            if(n.task != null){
                logger.info("Employee "+n.name+" is working on "+n.task.toString());
            }
        });
        solveTasks(computerRepairService, workTickets);
        ArrayList<WorkTicket> finalWorkTickets = workTickets;
        int billTotal = billTotalFinder.generateTotal(computerRepairService.getCustomerService().getCustomers());
        workTickets.forEach(n -> {
            if(n.getStatus().equals("solved")){
                logger.info("Task is solved, closing ticket");
                n = null;
                finalWorkTickets.remove(n);
            }

        });
        workTickets = finalWorkTickets;
        boolean allDone;
        if(workTickets.size() != 0){
            allDone = false;
            logger.info("Some work Tickets cannot be solved, some requests are impossible. "+workTickets.size()+" tickets remaining");
        }
        else{
            allDone = true;
        }
        logger.info("Completed Customer reactions:");
        List <Customer> completedCustomers = computerRepairService.getCustomerService().getCustomers().stream().filter(customer -> customer.getBill() > 0).collect(Collectors.toList());
        completedCustomers.forEach(n-> logger.info(n.reaction.getReaction(n.reaction)));
        if(!allDone) {
            logger.info("Uncompleted customers reactions: (Uh oh)");
            List<Customer> unCompletedCustomers = computerRepairService.getCustomerService().getCustomers().stream().filter(customer -> customer.getBill() == 0).collect(Collectors.toList());
            unCompletedCustomers.forEach(n -> logger.info(n.reaction.getReaction(n.reaction)));
            computerRepairService.getCustomerService().getCustomers().forEach(n -> n.processBill());
        }
        logger.info("Total bill (minus tips) is: "+billTotal);
        logger.info("Total paid bills: "+billpaied);
        logger.info("Total income (with tips) "+totalpaid);
    }

    public static ArrayList<WorkTicket> generateWorkTickets(ComputerRepair repairService){
        ArrayList<WorkTicket> returnList = new ArrayList<WorkTicket>();
        CustomerService customerService = repairService.getCustomerService();
        ArrayList<Customer> customers = customerService.getCustomers();
        WorkTicket newWorkticket;
        for(int i = 0; i < customers.size(); i++){
            Task currentTask = customers.get(i).getMachine().getProblem();
            newWorkticket = new WorkTicket(currentTask, "incomplete", 0);
            returnList.add(newWorkticket);
        }
        return returnList;
    }
    public static void assignTasks(ComputerRepair service, ArrayList<WorkTicket> tasks){
        for(int i = 0; i < Manager.employees.size(); i++){
            Manager.employees.get(i).setTask(tasks.get(i).taskType);
        }
    }
    interface actualNum{
        int custNumber(int x, int y);
    }
    interface StringFunction{
        String run(String str);
    }
    interface trueOrFalse{
        boolean findTrueFalse();
    }
    interface randomNum{
        int findNum(int x, int y);
    }
    public static void solveTasks(ComputerRepair service, ArrayList <WorkTicket> tasks){
        String status;
        StringFunction success = (str)-> str+" successfully!";
        StringFunction unsucess = (str)-> str+" unsucessfully!";
        Random rand = new Random();
        randomNum randNum = (x, y) -> (rand.nextInt(y - x) + x);
        recipts = new ArrayList<Recipt>();
        for(int i = 0; i < service.getManager().employees.size(); i++){
            Customer tempCust = service.getCustomerService().getCustomers().get(i);
            status = service.getManager().employees.get(i).task.solve();
            String returnString = "Task "+ i + " completed";
            if(status.equals("solved")){
                tasks.get(i).resolve();
                int bill = randNum.findNum(5, 200);
                int reactNum = randNum.findNum(1, 5);
                switch (reactNum){
                    case 1:
                        tempCust.reaction = Customer.Reaction.HAPPY;
                        break;
                    case 2:
                        tempCust.reaction = Customer.Reaction.APPRECIATIVE;
                        break;
                    case 3:
                        tempCust.reaction = Customer.Reaction.PLEASED;
                        break;
                    case 4:
                        tempCust.reaction = Customer.Reaction.DISAPPOINTED;
                        break;
                }
                tempCust.bill(bill);
                recipts.add(new Recipt(tempCust, bill));
                returnString = success.run(returnString);
            }
            else{
                int reactNum = randNum.findNum(1, 2);
                switch(reactNum){
                    case 1:
                        tempCust.reaction = Customer.Reaction.DISAPPOINTED;
                        break;
                    case 2:
                        tempCust.reaction = Customer.Reaction.IRATE;
                        break;
                }
                returnString = unsucess.run(returnString);
            }
            service.getCustomerService().getCustomers().set(i, tempCust);
            logger.info(returnString);

        }
    }
}
//static block
//Using reflection, extract information (modifiers return types params)
//About fields constuctors methods
//create object call method using only refelction