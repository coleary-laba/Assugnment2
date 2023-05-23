package main;

import interfaces.ICounterOfThings;
import interfaces.IGenerator;
import issues.HardwareMaintenanceTask;
import interfaces.IBillTotal;
import issues.InstallSoftwareTask;
import issues.Task;
import issues.UpdateTask;
import issues.VirusRemovalTask;
import items.Machine;
import items.Recipt;
import items.WorkTicket;
import people.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class Main {
    public static ArrayList<Worker> workers;
    public static ArrayList<Customer> customers;
    public static Logger logger;

    public static ComputerRepairService computerRepairService;
    public static final int numPeople = 10;
    public static ArrayList<Recipt> recipts;
    public static int billpaied = 0;
    public static int totalpaid = 0;


    public static void main(String[] args) {

        ICounterOfThings<String> NameSearcher;
        IGenerator<Worker> arrayGenWorker;
        IGenerator<Customer> arrayGenCustomer;
        IBillTotal<Customer> IBillTotalFinder;
        NameSearcher = (single, collection) -> {
            int count = 0;
            for (int i = 0; i < collection.length; i++) {
                if (single.equals(collection[i])) {
                    count++;
                }
            }
            return count;
        };
        arrayGenWorker = (collection) -> {
            String[] retArray = new String[collection.size()];
            for (int i = 0; i < collection.size(); i++) {
                retArray[i] = collection.get(i).getName();
            }
            return retArray;
        };
        arrayGenCustomer = (collection) -> {
            String[] retArray = new String[collection.size()];
            for (int i = 0; i < collection.size(); i++) {
                retArray[i] = collection.get(i).getName();
            }
            return retArray;
        };
        IBillTotalFinder = (collection) -> {
            int total = 0;
            for (int i = 0; i < collection.size(); i++) {
                total += collection.get(i).getBill();
            }
            return total;
        };
        logger = LogManager.getLogger(ComputerRepairService.class);
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
        } else {
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
        logger.info("The name Bob was in the selection of names " + counter + " times");
        int numPeople = lines.size();
        for (int i = 0; i < workNum; i++) {
            Task newTask = null;
            int prob = randNum.findNum(1, 5);
            trueOrFalse findBool = () -> rand.nextBoolean();
            switch (prob) {
                case 1:
                    int timing = randNum.findNum(1, 60);
                    newTask = new UpdateTask("2.0", timing, findBool.findTrueFalse());
                    break;
                case 2:
                    newTask = new HardwareMaintenanceTask("old", "newest");
                    break;
                case 3:
                    newTask = new VirusRemovalTask("CPU", findBool.findTrueFalse());
                    break;
                case 4:
                    newTask = new InstallSoftwareTask("C://", findBool.findTrueFalse());
                    break;
            }
            String name = lines.get(custNumber.custNumber(i, workNum));
            customers.add(new Customer(lines.get(custNumber.custNumber(i, workNum)), name + i + "@email.com", "1234567890", null));
            Machine newMachine = new Machine(newTask, null, "10", "Computer");
            customers.get(i).setMachine(newMachine);
        }
        for (int j = 0; j < workNum; j++) {
            workers.add(new Worker(lines.get(j), Integer.toString(j), null));
        }
        scan.close();
        long bobCount = lines.stream().filter(item -> item.startsWith("Bob")).count();
        logger.info("total count of names that start with bob " + bobCount);
        CustomerService customerService = new CustomerService("CustomerServicePerson", Integer.toString(Main.numPeople + 1), null, customers);
        Manager mangerMan = new Manager("ManagerMan", Integer.toString(Main.numPeople), null, workers);
        computerRepairService = new ComputerRepairService(mangerMan, customerService);

        String[] workerNameArray = arrayGenWorker.arrayGenerator(Manager.getEmployees());
        String[] custNameArray = arrayGenCustomer.arrayGenerator(computerRepairService.getCustomerService().getCustomers());
        int workBobs = NameSearcher.search("Bob", workerNameArray);
        int custBobs = NameSearcher.search("Bob", custNameArray);
        logger.info("Number of Bobs in Workers: " + workBobs);
        logger.info("Number of Bobs in Customers: " + custBobs);
        ArrayList<WorkTicket> workTickets = new ArrayList<WorkTicket>();
        workTickets = generateWorkTickets(computerRepairService);
        assignTasks(computerRepairService, workTickets);
        Manager.getEmployees().forEach(n -> {
            if (n.getTask() != null) {
                logger.info("Employee " + n.getName() + " is working on " + n.getTask());
            }
        });
        solveTasks(computerRepairService, workTickets);
        ArrayList<WorkTicket> finalWorkTickets = workTickets;
        int billTotal = IBillTotalFinder.generateTotal(computerRepairService.getCustomerService().getCustomers());
        workTickets.forEach(n -> {
            if (n.getStatus().equals(WorkTicket.Status.SOLVED)) {
                logger.info("Issues.Task is solved, closing ticket");
                n = null;
                finalWorkTickets.remove(n);
            }

        });
        workTickets = finalWorkTickets;
        boolean allDone;
        if (workTickets.size() != 0) {
            allDone = false;
            logger.info("Some work Tickets cannot be solved, some requests are impossible. " + workTickets.size() + " tickets remaining");
        } else {
            allDone = true;
        }
        logger.info("Completed People.Customer reactions:");
        List<Customer> completedCustomers = computerRepairService.getCustomerService().getCustomers().stream().filter(customer -> customer.getBill() > 0).collect(Collectors.toList());
        completedCustomers.forEach(n -> logger.info(n.reaction.getReaction(n.reaction)));
        if (!allDone) {
            logger.info("Uncompleted customers reactions: (Uh oh)");
            List<Customer> unCompletedCustomers = computerRepairService.getCustomerService().getCustomers().stream().filter(customer -> customer.getBill() == 0).collect(Collectors.toList());
            unCompletedCustomers.forEach(n -> logger.info(n.reaction.getReaction(n.reaction)));
            computerRepairService.getCustomerService().getCustomers().forEach(n -> n.processBill());
        }
        logger.info("Total bill (minus tips) is: " + billTotal);
        logger.info("Total paid bills: " + billpaied);
        logger.info("Total income (with tips) " + totalpaid);
        try {
            customerService.getCustomers().get(1).printer();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<WorkTicket> generateWorkTickets(ComputerRepairService repairService) {
        ArrayList<WorkTicket> returnList = new ArrayList<WorkTicket>();
        CustomerService customerService = repairService.getCustomerService();
        ArrayList<Customer> customers = customerService.getCustomers();
        WorkTicket newWorkticket;
        for (int i = 0; i < customers.size(); i++) {
            Task currentTask = customers.get(i).getMachine().getProblem();
            newWorkticket = new WorkTicket(currentTask, 0);
            newWorkticket.setStatus(WorkTicket.Status.UNSOLVED);
            returnList.add(newWorkticket);
        }
        return returnList;
    }

    public static void assignTasks(ComputerRepairService service, ArrayList<WorkTicket> tasks) {
        for (int i = 0; i < Manager.getEmployees().size(); i++) {
            Manager.getEmployees().get(i).setTask(tasks.get(i).getTask());
        }
    }

    interface actualNum {
        int custNumber(int x, int y);
    }

    interface StringFunction {
        String run(String str);
    }

    interface trueOrFalse {
        boolean findTrueFalse();
    }

    interface randomNum {
        int findNum(int x, int y);
    }

    public static void solveTasks(ComputerRepairService service, ArrayList<WorkTicket> tasks) {
        String status;
        Boolean tempBool;
        StringFunction success = (str) -> str + " successfully!";
        StringFunction unsucess = (str) -> str + " unsucessfully!";
        Random rand = new Random();
        randomNum randNum = (x, y) -> (rand.nextInt(y - x) + x);
        recipts = new ArrayList<Recipt>();
        for (int i = 0; i < Manager.getEmployees().size(); i++) {
            Customer tempCust = service.getCustomerService().getCustomers().get(i);
            tempBool = Manager.getEmployees().get(i).getTask().solve();
            if(tempBool){
                status = "solved";
            }
            else{
                status = "unsolved";
            }
            String returnString = "Issues.Task " + i + " completed";
            if (status.equals("solved")) {
                tasks.get(i).resolve();
                int bill = randNum.findNum(5, 200);
                int reactNum = randNum.findNum(1, 5);
                switch (reactNum) {
                    case 1:
                        tempCust.reaction = Reaction.HAPPY;
                        break;
                    case 2:
                        tempCust.reaction = Reaction.APPRECIATIVE;
                        break;
                    case 3:
                        tempCust.reaction = Reaction.PLEASED;
                        break;
                    case 4:
                        tempCust.reaction = Reaction.DISAPPOINTED;
                        break;
                }
                tempCust.bill(bill);
                recipts.add(new Recipt(tempCust, bill));
                returnString = success.run(returnString);
            } else {
                int reactNum = randNum.findNum(1, 2);
                switch (reactNum) {
                    case 1:
                        tempCust.reaction = Reaction.DISAPPOINTED;
                        break;
                    case 2:
                        tempCust.reaction = Reaction.IRATE;
                        break;
                }
                returnString = unsucess.run(returnString);
            }
            service.getCustomerService().getCustomers().set(i, tempCust);
            logger.info(returnString);

        }
    }

    public static void printInformation() throws ClassNotFoundException {
        Class examp = Customer.class;
        Constructor[] constructors = examp.getDeclaredConstructors();
        logger.info("Class info");
        for (Constructor con : constructors) {
            logger.info("Name of constructor : " + con);
            logger.info("Count of params : " + con.getParameterCount());
            Parameter[] params = con.getParameters();
            for (Parameter param : params) {
                logger.info("Param : " + param);
            }
        }
        Method[] methods = examp.getMethods();
        logger.info("Class methods:");
        for (Method meth : methods) {
            logger.info("Method name : " + meth.getName() + " returns : " + meth.getReturnType());
            logger.info(meth.getName() + " has params: ");
            Parameter[] methParams = meth.getParameters();
            for (Parameter methparam : methParams) {
                logger.info(methparam);
            }
        }
    }
}
//static block
//create object call method using only refelction