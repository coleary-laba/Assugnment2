package main;

import Errors.*;
import interfaces.IBillTotal;
import interfaces.ICounterOfThings;
import interfaces.IGenerator;
import interfaces.IQueueGen;
import issues.*;
import items.Machine;
import items.Recipt;
import items.Status;
import items.WorkTicket;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import people.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    public static Vector<Worker> workers;
    public static Queue<Customer> customers;
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static ComputerRepairService computerRepairService;
    public static final int numPeople = 10;
    public static HashSet<Recipt> recipts;
    public static int billpaied = 0;
    public static int totalpaid = 0;

    public static void main(String[] args) {
        ICounterOfThings<String> nameSearcher;
        IGenerator<Worker> arrayGenWorker;
        IQueueGen<Customer> arrayGenCustomer;
        IBillTotal<Customer> iBillTotal;
        nameSearcher = (single, collection) -> {
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
            int iter = 0;
            for (Customer cust : collection) {
                retArray[iter] = cust.getName();
                iter++;
            }
            return retArray;
        };
        iBillTotal = (collection) -> {
            int total = collection.stream().mapToInt(Recipt::getAmount).sum();
            return total;
        };
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        boolean workInit = false;
        boolean custInit = false;
        boolean initChoiceMade = false;
        int workNum = 0;
        int custNum = 0;
        int choice = 3;
        workers = new Vector<>();
        customers = new java.util.LinkedList<>();
        List<String> lines = new ArrayList<String>();
        randomNum randNum = (x, y) -> (rand.nextInt(y - x) + x);
        actualNum custNumber = (a, b) -> a + b;
        LOGGER.info("use own names for charecters or use prexisting ones? 1 for prexisting, 0 for create own");
        while (!initChoiceMade) {
            try {
                choice = Integer.parseInt(scan.next());
            } catch (InputMismatchException e) {
                LOGGER.info("Improper input");
            }
            if (choice == 1 || choice == 0) {
                initChoiceMade = true;
            }
        }
        if (choice == 0) {
            LOGGER.info("Roger, using names you make");
            while (!workInit) {
                LOGGER.info("Input the number of workers (will also be number of customers");
                try {
                    workNum = Integer.parseInt(scan.next());
                } catch (InputMismatchException e) {
                    LOGGER.info("Invalid Input");
                }
                try {
                    if (workNum == 0) {
                        throw new NoPeople("No Workers");
                    }
                } catch (NoPeople ex) {
                    LOGGER.info("No Workers inputed");
                }
            }
            while (!custInit) {
                LOGGER.info("Input the number of workers (will also be number of customers");
                try {
                    custNum = Integer.parseInt(scan.next());
                } catch (InputMismatchException e) {
                    LOGGER.info("Invalid Input");
                }
                try {
                    if (custNum == 0) {
                        throw new NoPeople("No Workers");
                    }
                } catch (NoPeople ex) {
                    LOGGER.info("No Customers inputed");
                }
            }
            for (int i = 0; i < workNum; i++) {
                LOGGER.info("Input worker name: must be under 10 characters");
                String name = scan.next();
                try {
                    if (name.length() > 10) {
                        throw new InvalidLength("Invalid length");
                    }
                } catch (InvalidLength ex) {
                    LOGGER.info("Invalid name length");
                    name = "Bob";
                }
                lines.add(name);
            }
            for (int i = 0; i < custNum; i++) {
                LOGGER.info("Input customer name: must be under 10 characters");
                String name = scan.next();
                try {
                    if (name.length() > 10) {
                        throw new InvalidLength("Invalid length");
                    }
                } catch (InvalidLength ex) {
                    LOGGER.info("Invalid name length");
                    name = "Bob";
                }
                lines.add(name);
            }
        } else {
            LOGGER.info("roger, using our names");
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
        LOGGER.info("The name Bob was in the selection of names " + counter + " times");
        int numPeople = lines.size();
        for (int i = 0; i < custNum; i++) {
            Task newTask = null;
            int prob = randNum.findNum(1, 5);
            trueOrFalse findBool = () -> rand.nextBoolean();
            switch (prob) {
                case 1:
                    int timing = randNum.findNum(1, 60);
                    newTask = new UpdateTask(Version.OLDER, timing, findBool.findTrueFalse());
                    break;
                case 2:
                    newTask = new HardwareMaintenanceTask(Hardware.CPU, Version.OLDER);
                    break;
                case 3:
                    newTask = new VirusRemovalTask("CPU", findBool.findTrueFalse());
                    break;
                case 4:
                    newTask = new InstallSoftwareTask("C://", findBool.findTrueFalse());
                    break;
            }
            String name = lines.get(custNumber.custNumber(i, workNum));
            Customer newCust = new Customer(lines.get(custNumber.custNumber(i, workNum)), name + i + "@email.com", "1234567890", null);
            Machine newMachine = new Machine(newTask, null, "10", "Computer");
            newCust.setMachine(newMachine);
            customers.add(newCust);
        }
        for (int j = 0; j < workNum; j++) {
            workers.add(new Worker(lines.get(j), Integer.toString(j), null));
        }
        scan.close();
        long bobCount = lines.stream().filter(item -> item.startsWith("Bob")).count();
        LOGGER.info("");
        LOGGER.info("total count of names that start with bob " + bobCount);
        CustomerService customerService = new CustomerService("CustomerServicePerson", Integer.toString(Main.numPeople + 1), null, customers);
        Manager mangerMan = new Manager("ManagerMan", Integer.toString(Main.numPeople), null, workers);
        computerRepairService = new ComputerRepairService(mangerMan, customerService);

        Queue<Customer> tempCust = computerRepairService.getCustomerService().getCustomers();
        String[] workerNameArray = arrayGenWorker.arrayGeneratorWork(Manager.getEmployees());
        String[] custNameArray = arrayGenCustomer.arrayGeneratorCust(tempCust);
        int workBobs = nameSearcher.search("Bob", workerNameArray);
        int custBobs = nameSearcher.search("Bob", custNameArray);
        LOGGER.info("Number of Bobs in Workers: " + workBobs);
        LOGGER.info("Number of Bobs in Customers: " + custBobs);
        LOGGER.info("");

        LinkedList<WorkTicket> workTickets = new LinkedList<>();
        workTickets = generateWorkTickets(computerRepairService);
        LOGGER.info("");
        assignTasks(computerRepairService, workTickets);
        LOGGER.info("");
        Manager.getEmployees().forEach(n -> {
            if (n.getTask() != null) {
                LOGGER.info("Employee " + n.getName() + " is working on " + n.getTask());
            }
        });
        solveTasks(computerRepairService, workTickets);
        LOGGER.info("");
        int billTotal = iBillTotal.generateTotal(recipts);
        for (int i = 0; i < workTickets.size(); i++) {
            if (workTickets.get(i).getStatus().equals(Status.SOLVED)) {
                workTickets.remove(i);
            }
        }
        boolean allDone;
        if (workTickets.size() != 0) {
            allDone = false;
            LOGGER.info("Some work Tickets cannot be solved, some requests are impossible. " + workTickets.size() + " tickets remaining");
        } else {
            allDone = true;
        }
        LOGGER.info("Completed People.Customer reactions:");
        List<Customer> completedCustomers = computerRepairService.getCustomerService().getCustomers().stream().filter(customer -> customer.getBill() > 0).collect(Collectors.toList());
        completedCustomers.forEach(n -> LOGGER.info(n.getReaction().getReaction(n.getReaction())));
        if (!allDone) {
            LOGGER.info("Uncompleted customers reactions: (Uh oh)");
            List<Customer> unCompletedCustomers = computerRepairService.getCustomerService().getCustomers().stream().filter(customer -> customer.getBill() == 0).collect(Collectors.toList());
            unCompletedCustomers.forEach(n -> LOGGER.info(n.getReaction().getReaction(n.getReaction())));
            computerRepairService.getCustomerService().getCustomers().forEach(n -> {
                try {
                    n.processBill();
                } catch (NotEnoughCash e) {
                    LOGGER.info(n.getName() + " could not pay their bill and has fled");
                }
            });
        }
        LOGGER.info("");
        LOGGER.info("Total bill (minus tips) is: " + billTotal);
        LOGGER.info("Total paid bills: " + billpaied);
        LOGGER.info("Total income (with tips) " + totalpaid);
        LOGGER.info("");
        LOGGER.info("");
        Queue<Customer> customerPrint = customerService.getCustomers();
        try {
            customerPrint.poll().printer();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static LinkedList<WorkTicket> generateWorkTickets(ComputerRepairService repairService) {
        LinkedList<WorkTicket> returnList = new LinkedList<>();
        CustomerService customerService = repairService.getCustomerService();
        Queue<Customer> customers = customerService.getCustomers();
        WorkTicket newWorkticket;
        int counter = 0;
        for (Customer cust : customers) {
            Task currentTask = cust.getMachine().getProblem();
            newWorkticket = new WorkTicket(currentTask, 0);
            newWorkticket.setStatus(Status.UNSOLVED);
            returnList.add(newWorkticket);
        }
        return returnList;
    }

    public static void assignTasks(ComputerRepairService service, LinkedList<WorkTicket> tasks) {
        int numCount = Manager.getEmployees().size();
        try {
            if (service.getManager().getEmployees().size() > tasks.size()) {
                throw new ToManyWorkers("More Workers then tasks");
            } else if (service.getManager().getEmployees().size() < tasks.size()) {
                throw new NotEnoughWorkers("More Tasks then workers");
            }
        } catch (ToManyWorkers toManyWorkers) {
            LOGGER.info("To many workers vs tasks! some workers will be unused");
            numCount = tasks.size();
        } catch (NotEnoughWorkers notEnoughWorkers) {
            LOGGER.info("Not all tasks will be solved");
        }
        for (int i = 0; i < numCount; i++) {
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

    public static void solveTasks(ComputerRepairService service, LinkedList<WorkTicket> tasks) {
        String status;
        Boolean tempBool;
        StringFunction success = (str) -> str + " successfully!";
        StringFunction unsucess = (str) -> str + " unsucessfully!";
        Random rand = new Random();
        randomNum randNum = (x, y) -> (rand.nextInt(y - x) + x);
        recipts = new HashSet<>();
        Queue<Customer> tempCustomers = service.getCustomerService().getCustomers();
        for (int i = 0; i < Manager.getEmployees().size(); i++) {
            Customer tempCust = tempCustomers.poll();
            tempBool = Manager.getEmployees().get(i).getTask().solve();
            if (tempBool) {
                status = "solved";
            } else {
                status = "unsolved";
            }
            String returnString = "Issues.Task " + i + " completed";
            if (status.equals("solved")) {
                tasks.get(i).resolve();
                int bill = randNum.findNum(5, 200);
                int reactNum = randNum.findNum(1, 5);
                switch (reactNum) {
                    case 1:
                        tempCust.setReaction(Reaction.HAPPY);
                        break;
                    case 2:
                        tempCust.setReaction(Reaction.APPRECIATIVE);
                        break;
                    case 3:
                        tempCust.setReaction(Reaction.PLEASED);
                        break;
                    case 4:
                        tempCust.setReaction(Reaction.DISAPPOINTED);
                        break;
                }
                tempCust.bill(bill);
                recipts.add(new Recipt(tempCust, bill));
                returnString = success.run(returnString);
            } else {
                int reactNum = randNum.findNum(1, 2);
                switch (reactNum) {
                    case 1:
                        tempCust.setReaction(Reaction.DISAPPOINTED);
                        break;
                    case 2:
                        tempCust.setReaction(Reaction.IRATE);
                        break;
                }
                returnString = unsucess.run(returnString);
            }
            service.getCustomerService().getCustomers().add(tempCust);
            LOGGER.info(returnString);
            recipts.forEach(n -> {
                LOGGER.info("Recipt Issued to: " + n.getCustomer());
                n.printItem();
            });
        }
    }

    public static void printInformation() throws ClassNotFoundException {
        Class examp = Customer.class;
        Constructor[] constructors = examp.getDeclaredConstructors();
        LOGGER.info("Class info");
        for (Constructor con : constructors) {
            LOGGER.info("Name of constructor : " + con);
            LOGGER.info("Count of params : " + con.getParameterCount());
            Parameter[] params = con.getParameters();
            for (Parameter param : params) {
                LOGGER.info("Param : " + param);
            }
        }
        Method[] methods = examp.getMethods();
        LOGGER.info("Class methods:");
        for (Method meth : methods) {
            LOGGER.info("Method name : " + meth.getName() + " returns : " + meth.getReturnType());
            LOGGER.info(meth.getName() + " has params: ");
            Parameter[] methParams = meth.getParameters();
            for (Parameter methparam : methParams) {
                LOGGER.info(methparam);
            }
        }
    }
}
//static block
//create object call method using only refelction